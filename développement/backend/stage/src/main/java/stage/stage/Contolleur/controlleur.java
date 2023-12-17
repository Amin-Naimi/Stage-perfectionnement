package stage.stage.Contolleur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stage.stage.entity.Stagaire;

import java.util.Map;

@RequestMapping(path = "/stagaire")
public interface controlleur {
        @PostMapping(path = "/signUp")
    public ResponseEntity<String> signUp(@RequestBody(required = true)  Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/checkToken")
    public ResponseEntity<String> checkTokenStag();



    @GetMapping(path = "/detail/{email}")
    public ResponseEntity<Stagaire> getStagiaireDetail(@PathVariable String email);
}
