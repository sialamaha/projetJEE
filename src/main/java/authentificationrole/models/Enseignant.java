package authentificationrole.models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Inheritance;
public class Enseignant extends User {
    private String specialite;
    private List<Matiere> matieresEnseignees;
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<Matiere> getMatieresEnseignees() {
        return matieresEnseignees;
    }

    public void setMatieresEnseignees(List<Matiere> matieresEnseignees) {
        this.matieresEnseignees = matieresEnseignees;
    }
}