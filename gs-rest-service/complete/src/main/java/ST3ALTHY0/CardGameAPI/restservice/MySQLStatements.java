package ST3ALTHY0.CardGameAPI.restservice;

public enum MySQLStatements{
    SELECT_DATA_FROM_SCORES("SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id ORDER BY s.score DESC LIMIT ?"),
    INSERT_INTO_SCORES("INSERT INTO Scores(userId, gameId, score, timeStamp) VALUES(?, ?, ?, ?)"),
    INSERT_INTO_USERS("INSERT IGNORE INTO Users(username) VALUES(?)");

    private final String sql;

    MySQLStatements(String sql){
        this.sql = sql;
    }
    public String getSql(){
        return sql;
    }
}
