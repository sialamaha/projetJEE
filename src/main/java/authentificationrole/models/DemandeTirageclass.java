package authentificationrole.models;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;

import authentificationrole.models.Enseignant;

public class DemandeTirageclass {
	private int idDemande;
	private int idUser;
	private int idMatiere;
	private int  idGroupe;
    private Date date_reception;
    private Date date_emprunte;
    private int nombre_copies;
    private InputStream document_PDF;
	 private String nom;  
		private String nomMatiere;
		 private String nomGroupe;
	 public String getNomMatiere() {
		return nomMatiere;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}

	 public String getNom() {
	        return nom;
	    }
	    public void setNom(String nom) {
	        this.nom = nom;
	    }
	    public void   setNomMatiere(String nomMatiere) {
	    	this.nomMatiere=nomMatiere;
	    }
	    public void   setNomGroupe(String nomGroupe) {
	    	this.nomGroupe=nomGroupe;
	    }
	public int getIdDemande() {
		return idDemande;
	}
	public void setIdDemande(int idDemande) {
		this.idDemande = idDemande;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	public Date getDate_reception() {
		return date_reception;
	}
	public void setDate_reception(Date date_reception) {
		this.date_reception = date_reception;
	}
	public Date getDate_emprunte() {
		return date_emprunte;
	}
	public void setDate_emprunte(Date date_emprunte) {
		this.date_emprunte = date_emprunte;
	}
	public int getNombre_copies() {
		return nombre_copies;
	}
	public void setNombre_copies(int nombre_copies) {
		this.nombre_copies = nombre_copies;
	}
	public InputStream getDocument_PDF() {
		return document_PDF;
	}
	public void setDocument_PDF(InputStream document_PDF) {
		this.document_PDF = document_PDF;
	}	
}