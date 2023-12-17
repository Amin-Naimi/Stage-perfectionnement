package stage.stage.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import stage.stage.entity.Stagaire;

import java.util.List;
import java.util.Map;

public interface EncadreurService {
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    ResponseEntity<String>  accepter(Map<String,String> requestMap);

    ResponseEntity<String>  refuser(Map<String,String> requestMap);

    ResponseEntity<String> checkTokenE();

    ResponseEntity<List<Stagaire>> getRecived();



}
