package stage.stage.Contolleur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stage.stage.entity.Stagaire;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/encadreur")
public interface EncadreurControlleur {

    @PostMapping(path = "/signUp")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);


    @PutMapping(path = "/accepter")
    public ResponseEntity<String> accepterDemande(@RequestBody(required = true) Map<String, String> requestMap);

    @PutMapping(path = "/refuser")
    public ResponseEntity<String> refuserDemande(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/checkToken")
    public ResponseEntity<String> checkTokenEnca();

    @GetMapping(path = "/get")
    public ResponseEntity<List<Stagaire>> getRecived();
}
