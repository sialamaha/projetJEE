package authentificationrole.models;

public class AgentTirage extends User {
	private int idAgentTirage;
	private String zone;
    public int getIdAgentTirage() {
		return idAgentTirage;
	}
	public void setIdAgentTirage(int idAgentTirage) {
		this.idAgentTirage = idAgentTirage;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
}
