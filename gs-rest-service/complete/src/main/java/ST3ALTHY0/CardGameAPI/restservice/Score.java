package ST3ALTHY0.CardGameAPI.restservice;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Scores")//Because this class name does not match the Table name in the DB we use this to tell JPA what the Table name is
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//lets the DB auto assign id
    private int id;

    @JsonBackReference//prob not needed, prevents recursively getting a User who has a Score which has the same User which has the same Score, and so on
    @ManyToOne(fetch = FetchType.LAZY)//specific way of loading userId only loading it when its needed
    @JoinColumn(name = "userId", nullable = false)//userId is the name of the column in the DB schema 
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId", nullable = false) 
    private Game gameId;//could have named this 'game' and added annotation @Column(name = "gameId")

    private int score;
    private long timeStamp;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game game) {
        this.gameId = game;
    }

    public int getScoreId() {
        return score;
    }

    public void setScoreId(int score) {
        this.score = score;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}