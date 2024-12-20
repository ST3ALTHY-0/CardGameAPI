package ST3ALTHY0.CardGameAPI.restservice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
    public boolean updateScore(@RequestParam String username, @RequestParam String gameType, @RequestParam int scoreValue) {
    try{
        addUser(username);
        addScore(username, gameType, scoreValue);
        return true;
    }catch(Exception e){
        System.out.println("Error: " + e.getMessage());
        return false;
    }
    }

    /*
     * add user to the DB 'Users' Table
     */
    private void addUser(String username) {
        User user = new User();
        user.setUsername(username);
        userService.addUser(user);
        
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
        score.setUserId(user);//this line is a little dumb seeing as we just created the user above in addUser() but not too bad.
        score.setGameId(game);
        score.setTimeStamp(time);
        scoreService.addScore(score);
    }



}
