package ST3ALTHY0.CardGameAPI.restservice;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import Data class rather than make a new one


@RestController
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService){
        this.scoreService = scoreService;
    }
    /*
     * endpoint to return top 10 HighScores
     * @returns List of top 10 Scores
     */

    
}
