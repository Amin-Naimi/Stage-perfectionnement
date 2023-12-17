package stage.stage.contolleurImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import stage.stage.Contolleur.EncadreurControlleur;
import stage.stage.entity.Stagaire;
import stage.stage.service.EncadreurService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin
public class EncadreurControlleurImpl implements EncadreurControlleur {

    @Autowired
    EncadreurService encadreurService;


    @Override
    public ResponseEntity<String> signUp(Map<String,String> requestMap) {
        try {
            return encadreurService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return encadreurService.login(requestMap);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> accepterDemande(Map<String, String> requestMap) {
        try{

            return encadreurService.accepter(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> refuserDemande(Map<String, String> requestMap) {
        try{

            return encadreurService.refuser(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> checkTokenEnca() {
        try {
            return encadreurService.checkTokenE();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Stagaire>> getRecived() {
        try {
            return encadreurService.getRecived();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Stagaire>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
