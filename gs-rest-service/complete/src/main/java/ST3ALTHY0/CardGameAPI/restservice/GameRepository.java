package ST3ALTHY0.CardGameAPI.restservice;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    //we need to find game by passing String GameType
    @Query("SELECT g FROM Game g Where g.gameType = :gameType")
    Optional<Game> findByGameType(String gameType);    
}
