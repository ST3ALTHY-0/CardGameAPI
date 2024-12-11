
package ST3ALTHY0.CardGameAPI.restservice;

import io.github.cdimascio.dotenv.Dotenv;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection connection;
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 5000;

    
    //set up db class, reuse CardGame DB code.
    public Database() {
        getRemoteDBConnection();
    }

    public static void main(String[] args){
        Database db = new Database();
        System.out.println("Hello");
    }



    private void getRemoteDBConnection() {
        Dotenv dotenv = Dotenv.configure()
                .directory(Paths.get("./").toString())
                .filename("cred.env")
                .load();

        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                // Close existing connection if any
                closeConnection();

                connection = DriverManager.getConnection(dotenv.get("DB_URL"), dotenv.get("DB_USER"),
                        dotenv.get("DB_PASSWORD"));
                connection.setAutoCommit(false); // Had problems with autoCommit so turned it off
                System.out.println("Remote Database connection established.");
                return;
            } catch (SQLException e) {
                retries++;
                System.out.println("Remote Database connection failed to establish. Attempt " + retries + " of " + MAX_RETRIES);
                e.printStackTrace();
                if (retries < MAX_RETRIES) {
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        System.out.println("Retry interrupted.");
                        return;
                    }
                }
            }
        }
        System.out.println("Failed to establish remote database connection after " + MAX_RETRIES + " attempts.");
    }

public void closeConnection() {
    try {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            // System.out.println("Database connection closed.");
        }
    } catch (SQLException e) {
        // e.printStackTrace();
        // System.out.println("Failed to close database connection.");
    }
}

private void rollbackTransaction() {
    try {
        connection.rollback();
        // System.out.println("Transaction rolled back.");
    } catch (Exception e) {
        // e.printStackTrace();
        // System.out.println("Failed to rollback transaction.");
    }
}

public void insertDataIntoUsers(Data data) {
    String sql = MySQLStatements.INSERT_INTO_USERS.getSql();

    try (PreparedStatement sqlStatement = connection.prepareStatement(sql)) {
        sqlStatement.setString(1, data.getName());
        sqlStatement.executeUpdate();
        connection.commit();
        System.out.println("User inserted.");
    } catch (Exception e) {
        // e.printStackTrace();
        // System.out.println("Error inserting user: " + e.getMessage());
    }
}

// private void insertDataIntoGames() {
//     String[] gameTypes = { "Easy", "Medium", "Hard" };
//     try (PreparedStatement pstmt = connection.prepareStatement(SQLiteStatements.INSERT_INTO_GAMES.getSql())) {
//     try (PreparedStatement pstmt = connection.prepareStatement(MySQLStatements.INSERT_INTO_GAMES.getSql())) {
//             pstmt.setString(1, gameType);
//             pstmt.addBatch();
//         }
//         pstmt.executeBatch();
//         // System.out.println("Games inserted.");
//     } catch (SQLException e) {
//         // System.out.println("Error inserting gameTypes: " + e.getMessage());
//     }
// }

public void insertDataIntoScores(Data data) {

    String sql = MySQLStatements.INSERT_INTO_SCORES.getSql();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, getId(data.getName(), "Users", "username"));
        pstmt.setInt(2, getId(data.getGameType(), "Games", "gameType"));
        pstmt.setInt(3, data.getScore());
        // pstmt.setTimestamp(4, data.getTime());
        long timeStamp = data.getTime().getTime();
        pstmt.setLong(4, timeStamp);
        pstmt.executeUpdate();
        connection.commit();
        // System.out.println("Score inserted.");
    } catch (Exception e) {
        // System.out.println("Error inserting data into Scores: " + e.getMessage());
    }
}

private int getId(String query, String table, String column) {
    String SELECT_ID_SQL_STATEMENT = "SELECT id FROM " + table + " WHERE " + column + " = ?"; // have to use variables in this statement, which is why its not in Enum
    try (PreparedStatement pstmt = connection.prepareStatement(SELECT_ID_SQL_STATEMENT)) {
        pstmt.setString(1, query);
        ResultSet result = pstmt.executeQuery();
        if (result.next()) {
            return result.getInt("id");
        }
    } catch (SQLException e) {
        // e.printStackTrace();
        // System.out.println("Error selecting Id from table: " + e.getMessage());
    }
    return -1;
}

public ArrayList<Data> selectDataFromScores(int numOfRows) {
    ArrayList<Data> dataList = new ArrayList<>();
    String sql = MySQLStatements.SELECT_DATA_FROM_SCORES.getSql();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, numOfRows);
        ResultSet rs = pstmt.executeQuery();
        connection.commit();

        while (rs.next()) {
            try {
                String username = rs.getString("username");
                String gameType = rs.getString("gameType");
                int score = rs.getInt("score");
                long timeStamp = rs.getLong("timeStamp"); 
                Data data = new Data(username, gameType, score, timeStamp);
                dataList.add(data);
            } catch (SQLException e) {
                 System.out.println("Error parsing data row: " + e.getMessage());
            }
        }
    } catch (SQLException e) {
         System.out.println("Error selecting data: " + e.getMessage());
    }

    return dataList;
}

}