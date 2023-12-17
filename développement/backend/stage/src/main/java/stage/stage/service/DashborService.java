package stage.stage.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DashborService {

    ResponseEntity<Map<String,Object>> getCount();
    ResponseEntity<Map<String,Object>> getNumber();

    ResponseEntity<Map<String, Object>> getAccepted();

    ResponseEntity<Map<String, Object>> getRefused();

    ResponseEntity<Map<String, Object>> getRecived();

}
