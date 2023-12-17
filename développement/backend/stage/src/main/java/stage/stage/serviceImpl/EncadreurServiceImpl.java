package stage.stage.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import stage.stage.Jwt.CustumorUserDetailService;
import stage.stage.Jwt.JwtFiltre;
import stage.stage.Jwt.JwtUtil;
import stage.stage.Repository.AdminRepository;
import stage.stage.Repository.EncadreurRepository;
import stage.stage.Repository.stagaireRepository;
import stage.stage.entity.Admin;
import stage.stage.entity.Stagaire;
import stage.stage.entity.encadreur;
import stage.stage.service.EncadreurService;
import stage.stage.utiles.EmailUtils;

import java.util.*;

@Slf4j
@Service
public class EncadreurServiceImpl implements EncadreurService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustumorUserDetailService getCustumorUserDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFiltre jwtFiltre;

    @Autowired
    EncadreurRepository encadreurRepository;

    @Autowired
    stagaireRepository stagaireRepository;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside singUp Encadreur{}", requestMap);
        try {
            if (validateSingUp(requestMap)) {
                String cin = requestMap.get("EncadreurCIN");
                log.info(cin);
                Optional<encadreur> encadreurOptional = encadreurRepository.findByEncadreurCin(Integer.parseInt(cin));
                if (encadreurOptional.isPresent()) {
                    return new ResponseEntity<String>("{\"message\":\"FALSE CIN \"}", HttpStatus.BAD_REQUEST);
                }
                encadreur encadreur = encadreurRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(encadreur)) {
                    encadreur = getUserFromMap(requestMap);
                    encadreurRepository.save(encadreur);
                    return new ResponseEntity<String>(encadreur.toString() + "{\"message\":\"Successfulyy Registred\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"Email or password already exits\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<String>("{\"message\":\"Invalide Data\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong3\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSingUp(Map<String, String> requestMap) {

        if (requestMap.containsKey("EncadreurCIN") && requestMap.containsKey("EncadreurFirstName")
                && requestMap.containsKey("EncadreurLastName") && requestMap.containsKey("email")
                && requestMap.containsKey("password") && requestMap.containsKey("department") && requestMap.containsKey("posteDeTravail")) {
            return true;
        }
        return false;
    }
    private encadreur getUserFromMap(Map<String, String> requestMap) {
        encadreur encadreur = new encadreur();
        encadreur.setEncadreurCIN(Integer.valueOf(requestMap.get("EncadreurCIN")));
        encadreur.setEncadreurFirstName(requestMap.get("EncadreurFirstName"));
        encadreur.setEncadreurLastName(requestMap.get("EncadreurLastName"));
        encadreur.setEmail(requestMap.get("email"));
        encadreur.setPassword(requestMap.get("password"));
        encadreur.setDepartment(requestMap.get("department"));
        encadreur.setPosteDeTravail(requestMap.get("posteDeTravail"));
        encadreur.setRole("Encadreur");
        return encadreur;
    }


    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login Encadreur {}",requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                return new ResponseEntity<String>("{\"Token\": \"" +
                        jwtUtil.generateToken(getCustumorUserDetailService.getEncadreurDetail().getEmail(),
                                getCustumorUserDetailService.getEncadreurDetail().getRole()) + "\"}", HttpStatus.OK);
            }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\" Données non valides \"}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> accepter(Map<String, String> requestMap) {

        log.info("Inside accepter encadreur { }", requestMap);
        Stagaire stagaire = new Stagaire();
        try {
            if(jwtFiltre.isEncadreur()){
                String cin = requestMap.get("stagaireCIN");
                log.info(cin);
                Optional<Stagaire> optionalStagaire = stagaireRepository.findByStagaireCIN(Integer.parseInt(cin));
                stagaire = optionalStagaire.get();
                log.info(stagaire.toString());
                if(!optionalStagaire.isEmpty())
                {
                   if(stagaire.getEtat().equals("DEMANDE EN COURS"))
                   {
                       log.info("valide");
                    stagaire.setEtat("ACCEPTER");
                    stagaire = stagaireRepository.save(stagaire);
                       log.info("Fuuuuuuuuu"+stagaire.getEmail());
                       String subject = "Acceptation de votre demande de stage";
                       String text = "Cher(e) " + stagaire.getStagaireLastName() + " " + stagaire.getStagaireLastName() + ",\n\n" +
                               "Je vous écris pour vous informer que votre demande de stage a été acceptée avec succès. Nous sommes ravis de vous accueillir dans notre entreprise et nous croyons que cette expérience sera bénéfique pour votre développement professionnel.\n\n" +
                               "Vous commencerez votre stage le " + stagaire.getDate_Debut_Stage() + ". Veuillez arriver à [heure d'arrivée] pour une brève séance d'accueil. Nous vous fournirons toutes les informations nécessaires pour vous familiariser avec nos processus et nos activités.\n\n" +
                               "N'hésitez pas à nous contacter si vous avez des questions ou des préoccupations. Nous sommes impatients de travailler avec vous et de vous accueillir dans notre équipe.\n\n" +
                               "Cordialement,\n" +
                               "[Les Ciments de Bizerte]";
                    sendMailToStagaire(stagaire.getEtat(),stagaire.getEmail(),text,subject);

                    return new ResponseEntity<>(stagaire.toString()+"{\"message\":\" ACCEPTED DONE\"}",HttpStatus.OK);
                   }
                }
                else {
                    return new ResponseEntity<>("{\"message\":\"Stagaire CIN dosen not exist\"}",HttpStatus.OK);
                }
            }else {
                return new ResponseEntity<>("{\"message\":\"UNAUTHORIZED ACCESS\"}",HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in ACCEPTER METHODE\"}  ", HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> refuser(Map<String, String> requestMap) {
        log.info("Inside refuser encadreur { }", requestMap);
        Stagaire stagaire = new Stagaire();
        try {
            if(jwtFiltre.isEncadreur()){
                String cin = requestMap.get("stagaireCIN");
                log.info(cin);
                Optional<Stagaire> optionalStagaire = stagaireRepository.findByStagaireCIN(Integer.parseInt(cin));
                stagaire = optionalStagaire.get();
                log.info(stagaire.toString());
                if(!optionalStagaire.isEmpty())
                {
                    if(stagaire.getEtat().equals("DEMANDE EN COURS"))
                    {
                        log.info("valide");
                        stagaire.setEtat("REFUSER");
                        stagaire = stagaireRepository.save(stagaire);

                        String subject = "Réponse à votre demande de stage";
                        String text = "Cher(e) " + stagaire.getStagaireLastName() + " " + stagaire.getStagaireLastName() + ",\n" +
                                "Je vous écris pour vous informer que nous avons examiné votre demande de stage et que nous sommes désolés de" +
                                " vous informer que nous ne sommes pas en mesure de l'accepter pour le moment."+ "\n" +
                                "Veuillez nous excuser pour tout inconvénient que cela peut occasionner"+ "\n" +
                                "[Les Ciments de Bizerte]";

                        sendMailToStagaire(stagaire.getEtat(),stagaire.getEmail(),text,subject);
                        return new ResponseEntity<>(stagaire.toString()+"{\"message\":\" REFUSED DEMANDE\"}",HttpStatus.OK);
                    }
                }
                else {
                    return new ResponseEntity<>("{\"message\":\"Stagaire CIN dosen not exist\"}",HttpStatus.OK);
                }
            }else {
                return new ResponseEntity<>("{\"message\":\"UNAUTHORIZED ACCESS\"}",HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Somthing went wrong in REFUSER METHODE\"} + ", HttpStatus.BAD_REQUEST);
    }

    private void sendMailToStagaire(String etat, String toEmail,String body ,String subject) {
        if(etat!= null){
            emailUtils.sendSimpleMessage(toEmail,body,subject);
        }
    }


    @Override
    public ResponseEntity<String> checkTokenE() {
        return new ResponseEntity<String>("{\"message\":\"true\"} + ", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Stagaire>> getRecived() {
        List<Stagaire> stagaireList;
        try {
            if (jwtFiltre.isEncadreur()) {
                stagaireList = stagaireRepository.consulterNewDemande("DEMANDE EN COURS");
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


}
