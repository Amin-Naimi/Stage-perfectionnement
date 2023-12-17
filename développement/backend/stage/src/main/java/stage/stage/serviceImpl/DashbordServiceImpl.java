package stage.stage.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import stage.stage.Jwt.JwtFiltre;
import stage.stage.Repository.AdminRepository;
import stage.stage.Repository.EncadreurRepository;
import stage.stage.Repository.stagaireRepository;
import stage.stage.service.DashborService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DashbordServiceImpl implements DashborService {

    @Autowired
    stagaireRepository stagaireRepository;
    @Autowired
    EncadreurRepository encadreurRepository;
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    JwtFiltre jwtFiltre;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();

        if (jwtFiltre.isAdmin()) {
            map.put("encadreur", encadreurRepository.count());
            map.put("Admin", adminRepository.count());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("Erreur d'authentification", 1);
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getNumber() {
        Map<String, Object> map = new HashMap<>();
        try {
            int nbDemandesend = stagaireRepository.countByEtat("ENVOYER");
            String testValue = String.valueOf(nbDemandesend);
            log.info("NB Demande  : " + testValue);
            if (jwtFiltre.isAdmin()) {
                map.put("NewDemande", nbDemandesend);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }else {
                map.put("Erreur d'authentification", 1);
                return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        map.put("Erreur", 1);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAccepted() {
        Map<String, Object> map = new HashMap<>();
        try {
            int nbSatagaireAccepted = stagaireRepository.countByEtat("ACCEPTER");
            String testValue = String.valueOf(nbSatagaireAccepted);
            log.info("NB stagaire Accepted : " + testValue);
            if (jwtFiltre.isAdmin()) {
                map.put("StagaireAccepter", nbSatagaireAccepted);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }else {
                map.put("Erreur d'authentification", 1);
                return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        map.put("Erreur", 1);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getRefused() {

        Map<String, Object> map = new HashMap<>();
        try {
            int nbSatagaireRefused = stagaireRepository.countByEtat("REFUSER");
            String testValue = String.valueOf(nbSatagaireRefused);
            log.info("NB stagaire Refuser : " + testValue);
            if (jwtFiltre.isAdmin()) {
                map.put("StagaireRefuser", nbSatagaireRefused);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }else {
                map.put("Erreur d'authentification", 1);
                return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        map.put("Erreur", 1);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getRecived() {
        Map<String, Object> map = new HashMap<>();
        try {
            int nbSatagaireRecived = stagaireRepository.countByEtat("DEMANDE EN COURS");
            String testValue = String.valueOf(nbSatagaireRecived);
            log.info("NB stagaire recived : " + testValue);
            if (jwtFiltre.isEncadreur()) {
                map.put("StagaireRecived", nbSatagaireRecived);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }else {
                map.put("Erreur d'authentification", 1);
                return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        map.put("Erreur", 1);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


}
