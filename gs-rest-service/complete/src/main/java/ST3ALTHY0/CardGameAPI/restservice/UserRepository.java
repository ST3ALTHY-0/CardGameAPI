package ST3ALTHY0.CardGameAPI.restservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);//might not this, i think we only need to find user by userID
//additionally these methods are likely redundant because JPA auto adds basic CRUD functions like these

    User saveAndFlush(User user);

    

}