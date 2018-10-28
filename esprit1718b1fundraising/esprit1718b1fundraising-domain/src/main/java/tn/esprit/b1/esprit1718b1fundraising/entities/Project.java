package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="project_type")
public class Project  implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idProject;
	
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;
	
	private String description;
	
	@OneToOne
	private BusinessPlan businessPlan;
	
	@ManyToOne
	private Utilisateur utilisateur ;
	

	@OneToMany(mappedBy="project")
	private List<Wishlist> wishlists;
	@OneToMany(mappedBy = "project")
	private List<Reclamation> reclamation;

	public Project() {
		super();
	}

	public Project(String title, Date dateDebut, Date dateFin, String description) {
		super();

		this.title = title;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		
	}

	public Project(String title, Date dateDebut, Date dateFin, String description, BusinessPlan businessPlan,
			Utilisateur utilisateur, List<Wishlist> wishlists) {
		super();

		this.title = title;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.businessPlan = businessPlan;
		this.utilisateur = utilisateur;
		this.wishlists = wishlists;
	}

	public Project(int idProject, String title, Date dateDebut, Date dateFin, String description,
			Utilisateur utilisateur) {
		super();
		this.idProject = idProject;
		this.title = title;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@OneToMany(mappedBy = "project")
	private List<Wishlist> wishlist;
	public BusinessPlan getBusinessPlan() {
		return businessPlan;
	}
	public void setBusinessPlan(BusinessPlan businessPlan) {
		this.businessPlan = businessPlan;
	}
	
	public List<Wishlist> getWishlists() {
		return wishlists;
	}
	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}
	public int getIdProject() {
		return idProject;
	}
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Project(String title, Date dateDebut, Date dateFin, String description, Utilisateur utilisateur) {
		super();
		this.title = title;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.utilisateur = utilisateur;
	}
	
	public Project(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	public Project(String title, Date dateFin) {
		super();
		this.title = title;
		this.dateFin = dateFin;
	}
	
	
	
}
