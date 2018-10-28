package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reclamation implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String body;
	private String subject;

	private String date;

	private Boolean RespAdded;
	private String resolved;
	private Boolean onUpdateClicked; 
	private int nbrMesg ;
	private Boolean persist ; 
	@ManyToOne
	private Utilisateur utilisateur;

	@ManyToOne
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
 
	public Boolean getPersist() {
		return persist;
	}

	public void setPersist(Boolean persist) {
		this.persist = persist;
	}

	public int getId() {
		return id;
	}


	public Boolean getOnUpdateClicked() {
		return onUpdateClicked;
	}

	public void setOnUpdateClicked(Boolean onUpdateClicked) {
		this.onUpdateClicked = onUpdateClicked;
	}

	public Reclamation(String body) {
		super();
		this.body = body;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Reclamation() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	

	public Reclamation(String body, String subject) {
		super();
		this.body = body;
		this.subject = subject;
	}

	

	

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public Boolean getRespAdded() {
		return RespAdded;
	}

	public void setRespAdded(Boolean respAdded) {
		RespAdded = respAdded;
	}

	public int getNbrMesg() {
		return nbrMesg;
	}

	public void setNbrMesg(int nbrMesg) {
		this.nbrMesg = nbrMesg;
	}


}
