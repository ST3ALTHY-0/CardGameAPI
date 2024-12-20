package ST3ALTHY0.CardGameAPI.restservice;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * DTO class that holds all data of score and user and gameType.
 * 
 * This data will need to be split into smaller chunks that will go
 * into separate DB tables as needed.
 * 
 * We have this grouped data class to simplify the endpoints needed,
 * making it so we only need one to update
 * 
 * @author Luke Monroe
 */
public class Data {
    private int score;
    private String gameType;
    private String name;

    @JsonIgnore
    private long time;
    

    public Data(){

    }

    //for sending data to db
    public Data(int score, String gameType, String name) {
        this.score = score;
        this.gameType = gameType;
        this.name = name;
        time = System.currentTimeMillis();

    }

    //for retriving data from db
    public Data(String name, String gameTypeString, int score, long timeStampMS) {
        this.name = name;
        this.gameType = gameTypeString;
        this.score = score;
        time = (timeStampMS);
    }
    public Data(ScoreData scoreData){
        
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameType() {
        return gameType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long timestamp) {
        time = timestamp;
    }

}