package stage.stage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stage.stage.entity.Stagaire;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface stagaireRepository extends JpaRepository<Stagaire,Long> {
    @Query(value = "SELECT * FROM Stagaire WHERE Stagaire.email=:email", nativeQuery = true)
    Stagaire findByEmailId(@Param("email") String email);


   @Query(value = "SELECT * FROM Stagaire  WHERE Stagaire.password IS NOT NULL",nativeQuery = true)
   List<Stagaire> consulterAllStagaireInfo();

    @Query(value = "SELECT * FROM Stagaire  WHERE Stagaire.stagaireCIN=:cin",nativeQuery = true)
    Optional<Stagaire> findByStagaireCIN(Integer cin );

    @Query(value = "SELECT * FROM Stagaire  WHERE Stagaire.stagaire_id=:Id",nativeQuery = true)
    Stagaire findByStagaireId(Integer Id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Stagaire WHERE Stagaire.stagaire_id=:Id", nativeQuery = true)
    void deleteStagaireById( Integer Id);

    @Query(value = "SELECT COUNT(*) FROM Stagaire WHERE Stagaire.etat=:etat", nativeQuery = true)
    int countByEtat(String etat);

    @Query(value = "SELECT * FROM Stagaire  WHERE Stagaire.etat=:etat",nativeQuery = true)
    List<Stagaire> consulterNewDemande(String etat);



}
