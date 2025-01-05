package ST3ALTHY0.CardGameAPI.restservice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/*
 * ScoreDataController is responsible for creating an endpoint for a client to use to PUT Score/User data
 * into the db.
 * 
 * we use a single ScoreDataController Class here because Score and User and Game are already pretty tightly coupled,
 * and we want it to be simple for the client to insert data, so we just have one complex endpoint instead of 2 or 3 simple ones.
 */
@RestController
public class ScoreDataController {

    private final ScoreService scoreService;
    private final UserService userService;
    private final GameService gameService;

    public ScoreDataController(ScoreService scoreService, UserService userService, GameService gameService){
        this.scoreService = scoreService;
        this.userService = userService;
        this.gameService = gameService;
    }


    /*
     * endpoint for Getting a list of Data Obj containing the 10 highest scores and their associated data
     */
    @GetMapping("/scoreData") 
    public List<Data> getScoreData(){ //add this to param if we want people to be able to choose limit amount (@RequestParam(defaultValue = "10") int limit)
        return scoreService.findTop10HighScores();
    }

     /*
     * endpoint for Putting a User in the DB
     */
    @PutMapping("/scoreData")
    public void updateScore(@RequestBody Data scoreRequest) {
        String username = scoreRequest.getName();
        String gameType = scoreRequest.getGameType();
        int scoreValue = scoreRequest.getScore();
        if (username == null || username.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty");
        }
        if (!gameType.equals("Easy") && !gameType.equals("Medium") && !gameType.equals("Hard")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game type " + gameType + " not allowed");
        }
        if (scoreValue < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Negative Score is not allowed");
        }
    try{
        userService.addUser(username);
        addScore(username, gameType, scoreValue);
    }catch(Exception e){
        System.out.println("Error: " + e.getMessage());
    }
    }

    /*
     * add score to the DB 'Scores' Table
     */
    private void addScore(String username, String gameType, int scoreValue){
        Score score = new Score();

        User user = userService.findByUsername(username);
        Game game = gameService.findByGameType(gameType);
        long time = System.currentTimeMillis();

        score.setScoreId(scoreValue);
        score.setUserId(user);
        score.setGameId(game);
        score.setTimeStamp(time);
        scoreService.addScore(score);
    }



}
