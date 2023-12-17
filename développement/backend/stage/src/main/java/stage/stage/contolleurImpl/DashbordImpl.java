package stage.stage.contolleurImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import stage.stage.Contolleur.Dashbord;
import stage.stage.service.DashborService;

import java.util.Map;

@RestController
@CrossOrigin
public class DashbordImpl implements Dashbord {
    @Autowired
    DashborService dashborService;

    @Override
    public ResponseEntity<Map<String, Object>> getDetails() {
        return dashborService.getCount();
    }

    @Override
    public ResponseEntity<Map<String, Object>> getStagaireNumber() {
        return dashborService.getNumber();
    }

    @Override
    public ResponseEntity<Map<String, Object>> getStagaireRecived() {
        return dashborService.getRecived();
    }

    @Override
    public ResponseEntity<Map<String, Object>> getStagaireaccepted() {
        return dashborService.getAccepted();
    }

    @Override
    public ResponseEntity<Map<String, Object>> getStagairerefused() {

        return dashborService.getRefused();
    }


}
