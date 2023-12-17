package stage.stage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stage.stage.entity.Admin;
import stage.stage.entity.Stagaire;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query(value = "SELECT * FROM Admin WHERE Admin.email=:email", nativeQuery = true)
    Admin findByEmailId(@Param("email") String email);


    Admin findByEmail(String email);

}
