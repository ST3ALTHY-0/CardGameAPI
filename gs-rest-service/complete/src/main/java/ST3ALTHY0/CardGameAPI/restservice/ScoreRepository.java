package ST3ALTHY0.CardGameAPI.restservice;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

    // Method to select the top 10 highest scores
//    @Modifying
    @Transactional
    @Query(value = "SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id ORDER BY s.score DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10HighScores();
} 


    

