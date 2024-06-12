package authentificationrole.models;

public class Administrateur extends User{
	private int idAdministrateur;
	private String permission;
    public int getIdAdministrateur() {
		return idAdministrateur;
	}
	public void setIdAdministrateur(int idAdministrateur) {
		this.idAdministrateur = idAdministrateur;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
