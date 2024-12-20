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

    //need to be able to update score with (int id(auto-generated),User user, int score, long time)
    // Custom query to update score
    // @Modifying
    // @Query("UPDATE Score s SET s.user = :user, s.score = :score, s.time = :time WHERE s.id = :id")
    // void updateScore(@Param("id") int id, @Param("user") User user, @Param("score") int score, @Param("time") long time);

    // Method to select the top 10 highest scores
    @Modifying
    @Transactional
    @Query(value = "SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id ORDER BY s.score DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10HighScores();
} 


    

