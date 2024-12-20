package ST3ALTHY0.CardGameAPI.restservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class UserService {

    private final UserRepository userRepository;// we use the repository

    // could also make a Logger class which we could make a variable for and assign
    // to here.

    @Autowired //this annotation injects a instance of userRepository into the function so we don't need to deal with making a new obj ourselves
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        try {
            return userRepository.saveAndFlush(user); // This will insert or update the user
        } catch (DataIntegrityViolationException e) {
            // Log the exception and ignore it
            System.out.println("User already exists: " + user.getUsername());
            return null;
        }
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user != null){
            return user;
        }else{
            System.out.println("User " + username + " does not exist.");
            return null;
        }
    }

  
}
