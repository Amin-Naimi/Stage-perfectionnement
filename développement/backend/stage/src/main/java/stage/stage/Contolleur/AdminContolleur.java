package stage.stage.Contolleur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stage.stage.entity.Stagaire;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/admin")
public interface AdminContolleur {

    @PostMapping(path = "/signUp")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<Stagaire>> getAlluser();

    @GetMapping(path = "/checkToken")
    public ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePass")
    public ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap);

    @DeleteMapping(path = "/deleteEncadreur/{id}")
    public ResponseEntity <String> deleteEncadreur(@PathVariable Integer id);

    @PostMapping(path = "/deleteStagaire/{id}")
    public ResponseEntity<String> deleteStagaire(@PathVariable Integer id);

    @PostMapping(path = "/updateByadmin/{id}")
    public ResponseEntity<String> updateByAdmin(@PathVariable Integer id);





}
