package stage.stage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;




@Entity
@DynamicUpdate
@DynamicInsert
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stagaire implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stagaireId;

    private Integer stagaireCIN;
    private String stagaireFirstName;
    private String stagaireLastName;
    private String email;
    private String password;
    private String university;
    private String specialite;

    private String phoneNumber;
    private String etat ;

    private String role;

    private String typeStage;
    private String date_Debut_Stage;
    private String date_Fin_Stage;

    private String date_demande_stage;
}
