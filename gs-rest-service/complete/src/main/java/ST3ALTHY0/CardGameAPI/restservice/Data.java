package ST3ALTHY0.CardGameAPI.restservice;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class Data {
    private int score;
    private String gameType;
    private String name;

    @JsonIgnore
    private Timestamp time;
    

    public Data(){

    }

    //for sending data to db
    public Data(int score, String gameType, String name) {
        this.score = score;
        this.gameType = gameType;
        this.name = name;
        time = new Timestamp(System.currentTimeMillis());

    }

    //for retriving data from db
    public Data(String name, String gameTypeString, int score, long timeStampMS) {
        this.name = name;
        this.gameType = gameTypeString;
        this.score = score;
        time = new Timestamp(timeStampMS);
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        time = timestamp;
    }

}