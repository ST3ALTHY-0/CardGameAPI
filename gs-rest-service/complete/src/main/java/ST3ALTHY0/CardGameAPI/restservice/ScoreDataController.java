package ST3ALTHY0.CardGameAPI.restservice;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import Data class rather than make a new one


@RestController
public class ScoreDataController {

    private Database db = new Database();

    
    @GetMapping("/scoreData")
    public ArrayList<Data> getScoreData(){ //add this to param if we want people to be able to choose limit amount (@RequestParam(defaultValue = "10") int limit)
        return db.selectDataFromScores(10);
    }

    //get String name, int score, String gameType
    @PutMapping("/scoreData")
    public boolean updateScore(@RequestBody Data newScoreData) {
        newScoreData.setTime(new Timestamp(System.currentTimeMillis()));

        try{
        db.insertDataIntoUsers(newScoreData);
        db.insertDataIntoScores(newScoreData);
        //confirm transaction maybe
        return true;
        }catch(Exception e){
            System.out.println("error PutMapping");
            return false;
        }
    }


}
