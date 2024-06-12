package authentificationrole.models;

public class Groupe {
	private int idGroupe ;
	 private String nomGroupe ;
	 private int Nb_Etudiants ;
    private int Id_Enseignant ;
	 private Matiere matieres;
	 public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public int getNb_Etudiants() {
		return Nb_Etudiants;
	}
	public void setNb_Etudiants(int nb_Etudiants) {
		Nb_Etudiants = nb_Etudiants;
	}
	public int getId_Enseignant() {
		return Id_Enseignant;
	}
	public void setId_Enseignant(int id_Enseignant) {
		Id_Enseignant = id_Enseignant;
	}
	public Matiere getMatieres() {
		return matieres;
	}
	public void setMatieres(Matiere matieres) {
		matieres = matieres;
	}
	
}
