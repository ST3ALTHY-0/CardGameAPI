package ST3ALTHY0.CardGameAPI.restservice;

//define a record Data class to hold the data (String name, int score, String gameType, long time)

public record ScoreData(int id, String name, int score, String gameType, long time) { 
}