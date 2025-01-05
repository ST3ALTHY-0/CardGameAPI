package ST3ALTHY0.CardGameAPI.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }


    public List<Data> findTop10HighScores(){
        List<Object[]> rawResults = scoreRepository.findTop10HighScores();
        List<Data> dataList = new ArrayList<>();

        //we parse the rawResults and create Data obj out of them to add to dataList
        for (Object[] row : rawResults) {
            String username = (String) row[0];
            String gameType = (String) row[1];
            int score = (int) row[2];
            long timeStamp = Long.parseLong((String) row[3]);
            Data data = new Data(username, gameType, score, timeStamp);
            dataList.add(data);
        }
        return dataList;
}

public Score addScore(Score score) {
    return scoreRepository.saveAndFlush(score);
}


}