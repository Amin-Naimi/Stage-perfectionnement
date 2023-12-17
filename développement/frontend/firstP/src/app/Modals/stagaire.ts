export class stagaire {
    stagaireId: number;
    stagaireCIN: number;
    stagaireFirstName: string;
    stagaireLastName: string;
    email: string;
    password: string;
    university: string;
    specialite: string;
    phoneNumber: number;
    etat: string;
    role: string;
    typeStage: string;
    date_Debut_Stage: any;
    date_Fin_Stage: any;
    date_demande_stage: any;

    constructor() {
        this.stagaireId =0
        this.stagaireCIN = 0;
        this.stagaireFirstName = "";
        this.stagaireLastName = "";
        this.email = "";
        this.password = "";
        this.university = "";
        this.specialite = "";
        this.phoneNumber = 0;
        this.etat = "ENVOYER";
        this.role = "Stagaire";
        this.typeStage = "";
        this.date_Debut_Stage = null;
        this.date_Fin_Stage = null;
        this.date_demande_stage = null;
    }
}
