package stage.stage.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import stage.stage.entity.Stagaire;

import java.util.List;
import java.util.Map;

public interface AdminService {

     ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);
     ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    ResponseEntity<List<Stagaire>> getAlluser();

    ResponseEntity<String> checkToken();

    ResponseEntity<String>changePassword(Map<String,String> requestMap);

    ResponseEntity<String> deleteStagaire(Integer id);

    ResponseEntity<String> deleteEncadreur(Integer id);

    ResponseEntity<String> sendToEncadreur(Integer id);


}
