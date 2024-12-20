package ST3ALTHY0.CardGameAPI.restservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;


    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    //we only provide this one method because we only need to look up the Game that matches
    //String gameType
    public Game findByGameType(String gameType) {
        return gameRepository.findByGameType(gameType)
                .orElseThrow(() -> new RuntimeException("Game type not found"));
    }
}
