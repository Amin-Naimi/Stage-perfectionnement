package stage.stage.service;

import org.springframework.http.ResponseEntity;
import stage.stage.entity.Stagaire;

import java.util.Map;

public interface stagaireService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);
    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<String>checkTokenStag();

    ResponseEntity<Stagaire>chercherStagaire(String email);


}
