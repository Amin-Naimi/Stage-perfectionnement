package stage.stage.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import stage.stage.Jwt.*;
import stage.stage.Repository.EncadreurRepository;
import stage.stage.Repository.stagaireRepository;
import stage.stage.entity.Admin;
import stage.stage.entity.Stagaire;
import stage.stage.entity.encadreur;
import stage.stage.service.AdminService;
import stage.stage.Repository.AdminRepository;

import java.util.*;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustumorUserDetailService getCustumorUserDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFiltre jwtFiltre;

    @Autowired
    stagaireRepository stagaireRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EncadreurRepository encadreurRepository;

    @Autowired
    stagaireServiceImpl stagaireService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside singUp {}", requestMap);
        try {
            if (validateSingUp(requestMap)) {
                Admin admin = adminRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(admin)) {
                    admin = getUserFromMap(requestMap);
                    adminRepository.save(admin);
                    return new ResponseEntity<String>(admin.toString() + "{\"message\":\"Successfulyy Registred\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"Email or password already exits\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<String>("{\"message\":\"Invalide Data\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong2\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSingUp(Map<String, String> requestMap) {

        if (requestMap.containsKey("AdminCIN") && requestMap.containsKey("AdminFirstName")
                && requestMap.containsKey("AdminLastName") && requestMap.containsKey("email")
                && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private Admin getUserFromMap(Map<String, String> requestMap) {
        Admin admin = new Admin();
        admin.setAdminCIN(Integer.valueOf(requestMap.get("AdminCIN")));
        admin.setAdminFirstName(requestMap.get("AdminFirstName"));
        admin.setAdminLastName(requestMap.get("AdminLastName"));
        admin.setEmail(requestMap.get("email"));
        admin.setPassword(requestMap.get("password"));
        admin.setRole("Administrateur");
        return admin;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login Admin {}", requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                return new ResponseEntity<String>("{\"Token\": \"" +
                        jwtUtil.generateToken(getCustumorUserDetailService.getAdminDetail().getEmail(),
                                getCustumorUserDetailService.getAdminDetail().getRole()) + "\"}", HttpStatus.OK);
            }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"Données non valides \"}", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<Stagaire>> getAlluser() {
        List<Stagaire> stagaireList;
        try {
            if (jwtFiltre.isAdmin()) {
                stagaireList = stagaireRepository.consulterNewDemande("ENVOYER");
                for (Stagaire stagaire : stagaireList) {
                    stagaire.setPassword("***********");
                }
                return new ResponseEntity<>(stagaireList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> sendToEncadreur(Integer id) {
        log.info("Inside sendToEncadreur {}");
        try {
            if (jwtFiltre.isAdmin()) {
                Stagaire stagaire = stagaireRepository.findByStagaireId(id);
                log.info(stagaire.toString());
                if (stagaire!=null) {
                    stagaire.setEtat("DEMANDE EN COURS");
                    stagaire = stagaireRepository.save(stagaire);
                    return new ResponseEntity<>(stagaire.toString() + "{\"message\":\"Send Done Succssefuly\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Stagaire id dosen not exist\"}", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"UNAUTHORIZED ACCESS\"}", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in send to Encadreur\"} + ", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> checkToken() {

        return new ResponseEntity<String>("{\"message\":\"true\"} + ", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            Admin adminObject = adminRepository.findByEmail(jwtFiltre.getCurrentUser());
            if (!adminObject.equals(null)) {
                if (adminObject.getPassword().equals(requestMap.get("oldPassword"))) {
                    adminObject.setPassword(requestMap.get("newPassword"));
                    adminRepository.save(adminObject);
                    log.info(adminObject.getPassword());
                    return new ResponseEntity<String>("{\"message\":\"  Password updated succ \"} + ", HttpStatus.OK);
                }
                return new ResponseEntity<String>("{\"message\":\" Incorrect old Password\"} + ", HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in change Password\"} + ", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in change Password\"} + ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteStagaire(Integer identifiant) {
            try {
            if (jwtFiltre.isAdmin()) {
                Stagaire stagaire = stagaireRepository.findByStagaireId(identifiant);
                if(stagaire != null){
                    log.info(stagaire.toString());
                    Integer id = stagaire.getStagaireId();
                    Integer cinStagaire = stagaire.getStagaireCIN();
                    stagaireRepository.deleteStagaireById(id);
                    return new ResponseEntity<String>( "Stagaire With CIN:"+ cinStagaire  +" deleted" , HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\" Stagaire does not exist \"}", HttpStatus.OK);
                }

            } else {
                return new ResponseEntity<String>("{\"message\":\"Unauthorized Access \"} + ", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in delete stagaire \"} + ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteEncadreur(Integer id) {
        try {
            if (jwtFiltre.isAdmin()) {
                encadreur encadreur  = encadreurRepository.findByEncadreurId(id);
                if(encadreur != null){
                    log.info(encadreur.toString());
                    Integer identifiant = encadreur.getEncadreurId();
                    Integer cinEncadreur = encadreur.getEncadreurCIN();
                    encadreurRepository.deleteEncadreurById(identifiant);
                    return new ResponseEntity<String>( "Encadreur With CIN:"+ cinEncadreur  +" deleted" , HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\" Encadreur does not exist \"}", HttpStatus.OK);
                }

            } else {
                return new ResponseEntity<String>("{\"message\":\"Unauthorized Access \"} + ", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in delete Encadreur \"} + ", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
