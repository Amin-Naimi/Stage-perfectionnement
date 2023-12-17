package stage.stage.contolleurImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import stage.stage.Contolleur.controlleur;
import stage.stage.entity.Stagaire;
import stage.stage.service.stagaireService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class controlleurImpl implements controlleur {

    @Autowired
    stagaireService stagaireService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return stagaireService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return stagaireService.login(requestMap);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> checkTokenStag() {
        try {
            return stagaireService.checkTokenStag();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Stagaire> getStagiaireDetail(String email) {
        try {
            return stagaireService.chercherStagaire(email);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<Stagaire>(new Stagaire(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
