package stage.stage.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import stage.stage.Jwt.JwtFiltre;
import stage.stage.Jwt.JwtUtil;
import stage.stage.Jwt.CustumorUserDetailService;
import stage.stage.entity.Stagaire;
import stage.stage.service.stagaireService;
import stage.stage.Repository.stagaireRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class stagaireServiceImpl implements stagaireService {

    @Autowired
    stagaireRepository stagaireRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustumorUserDetailService getCustumorUserDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFiltre jwtFiltre;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside singUp {}", requestMap);
        try {
            if (validateSingUp(requestMap)) {
                String cin = requestMap.get("stagaireCIN");
                log.info(cin);
                Optional<Stagaire> optionalStagaire = stagaireRepository.findByStagaireCIN(Integer.parseInt(cin));
                if (optionalStagaire.isPresent()) {
                    return new ResponseEntity<String>("{\"message\":\"CIN déjà utilisé \"}", HttpStatus.BAD_REQUEST);
                }
                Stagaire stagaire = stagaireRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(stagaire) ) {
                    stagaire = getUserFromMap(requestMap);
                    stagaireRepository.save(stagaire);
                    return new ResponseEntity<String>("{\"message\":\"Inscription réussie\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"Adresse email inavalide\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<String>("{\"message\":\"Données non valides\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Une erreur s'est produite.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSingUp(Map<String, String> requestMap) {

        if (requestMap.containsKey("stagaireCIN") && requestMap.containsKey("stagaireFirstName")
                && requestMap.containsKey("stagaireLastName") && requestMap.containsKey("email")
                && requestMap.containsKey("password") && requestMap.containsKey("specialite")
                && requestMap.containsKey("typeStage") && requestMap.containsKey("date_Debut_Stage")
                && requestMap.containsKey("date_Fin_Stage")) {
            return true;
        }
        return false;
    }

    public Stagaire getUserFromMap(Map<String, String> requestMap) {
        Stagaire stagaire = new Stagaire();
        stagaire.setStagaireCIN(Integer.valueOf(requestMap.get("stagaireCIN")));
        stagaire.setStagaireFirstName(requestMap.get("stagaireFirstName"));
        stagaire.setStagaireLastName(requestMap.get("stagaireLastName"));
        stagaire.setEmail(requestMap.get("email"));
        stagaire.setPassword(requestMap.get("password"));
        stagaire.setUniversity(requestMap.get("university"));
        stagaire.setSpecialite(requestMap.get("specialite"));
        stagaire.setPhoneNumber(requestMap.get("phoneNumber"));
        stagaire.setTypeStage(requestMap.get("typeStage"));
        stagaire.setDate_Debut_Stage(requestMap.get("date_Debut_Stage"));
        stagaire.setDate_Fin_Stage(requestMap.get("date_Fin_Stage"));
        stagaire.setEtat("ENVOYER");
        stagaire.setRole("Stagaire");
        Date date_aujour = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(date_aujour);
        stagaire.setDate_demande_stage(date);

        return stagaire;

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login Stagaire {}",requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                return new ResponseEntity<String>("{\"Token\": \"" +
                        jwtUtil.generateToken(getCustumorUserDetailService.getStagaireDetail().getEmail(),
                                getCustumorUserDetailService.getStagaireDetail().getRole()) + "\"}", HttpStatus.OK);
            }//else {
               // return new ResponseEntity<String>("{\"message\":\"Données non valides\"}", HttpStatus.BAD_REQUEST);
            //}

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"Données non valides.\"}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> checkTokenStag() {
        return new ResponseEntity<String>("{\"message\":\"true\"} + ", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Stagaire> chercherStagaire(String email) {
        log.info("Inside stagaire detail { }", email);
        Stagaire stagaire = new Stagaire();
        try {
            if(jwtFiltre.isStagaire()){
                stagaire = stagaireRepository.findByEmailId(email);
                log.info(stagaire.toString());
                return new ResponseEntity<Stagaire>(stagaire, HttpStatus.OK);
            }else {
                return new ResponseEntity<Stagaire>(new Stagaire(), HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Stagaire(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
