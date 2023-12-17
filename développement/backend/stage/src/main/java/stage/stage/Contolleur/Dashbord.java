package stage.stage.Contolleur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/dashbord")
public interface Dashbord {

    @GetMapping(path = "/details")
    ResponseEntity<Map<String,Object>> getDetails();

    @GetMapping(path = "/number")
    ResponseEntity<Map<String,Object>> getStagaireNumber();

    @GetMapping(path = "/recived")
    ResponseEntity<Map<String,Object>> getStagaireRecived();

    @GetMapping(path = "/accepted")
    ResponseEntity<Map<String,Object>> getStagaireaccepted();

    @GetMapping(path = "/refused")
    ResponseEntity<Map<String,Object>> getStagairerefused();



}
