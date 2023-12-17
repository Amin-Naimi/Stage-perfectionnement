package stage.stage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stage.stage.entity.Stagaire;
import stage.stage.entity.encadreur;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EncadreurRepository extends JpaRepository<encadreur,Integer> {

    @Query(value = "SELECT * FROM encadreur WHERE encadreur.email=:email", nativeQuery = true)
    encadreur findByEmailId(@Param("email") String email);

    @Query(value = "SELECT * FROM encadreur  WHERE encadreur.EncadreurCIN=:cin",nativeQuery = true)
    Optional<encadreur> findByEncadreurCin (Integer cin );

    @Query(value = "SELECT * FROM encadreur  WHERE encadreur.encadreur_id=:Id",nativeQuery = true)
    encadreur findByEncadreurId(Integer Id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM encadreur WHERE encadreur.encadreur_id=:Id", nativeQuery = true)
    void deleteEncadreurById( Integer Id);
}
