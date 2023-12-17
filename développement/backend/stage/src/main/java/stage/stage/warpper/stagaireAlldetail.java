package stage.stage.warpper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class stagaireAlldetail {

    private Integer stagaireId;

    private Integer stagaireCIN;

    private String stagaireFirstName;

    private String stagaireLastName;

    private String email;

    private String university;

    private String specialite;

    private String phoneNumber;

    private String status ;

    private String etat ;

    private String typeStage;

    private String date_Debut_Stage;

    private String date_Fin_Stage;

    private String date_demande_stage;

}
