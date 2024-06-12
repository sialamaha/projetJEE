package authentificationrole.models;
public class Matiere {
    private int idMatiere;
    private String nomMatiere;
	private int idUser;

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    public int getIdUser() {
  		return idUser;
  	}

  	public void setIdUser(int idUser) {
  		this.idUser = idUser;
  	}
}