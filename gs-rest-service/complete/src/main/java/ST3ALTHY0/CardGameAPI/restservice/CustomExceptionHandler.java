package ST3ALTHY0.CardGameAPI.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class CustomExceptionHandler {
//This was an auto generated Class, with small changes I made
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", ex.getStatusCode().value());
        response.put("error", HttpStatus.valueOf(ex.getStatusCode().value()).getReasonPhrase());
        response.put("message", ex.getReason());
        //response.put("path", ex.get);

        return new ResponseEntity<>(response, ex.getStatusCode());
    }
}