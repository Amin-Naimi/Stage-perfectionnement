package stage.stage.contolleurImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import stage.stage.Contolleur.AdminContolleur;
import stage.stage.entity.Stagaire;
import stage.stage.service.AdminService;
import stage.stage.service.stagaireService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AdminContolleurImpl implements AdminContolleur {

    @Autowired
    AdminService adminService;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return adminService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return adminService.login(requestMap);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @Override
    public ResponseEntity<List<Stagaire>>  getAlluser() {
        try {
            return adminService.getAlluser();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Stagaire>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> checkToken() {
        try {
            return adminService.checkToken();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            return adminService.changePassword(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteStagaire(Integer id) {
        try {
            return adminService.deleteStagaire(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateByAdmin(Integer id) {
        try{

            return adminService.sendToEncadreur(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> deleteEncadreur(Integer id) {
        try {
            return adminService.deleteEncadreur(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
