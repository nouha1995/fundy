package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue(value="charityP")
public class CharityProject extends Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Organization organization;
	
	private String confirm;
	
	private String picture;
	
	@OneToMany(mappedBy="charityProject")
	private List<Article> articles=new ArrayList<>();

	public CharityProject() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CharityProject( String title, Date dateDebut, Date dateFin, String description) {
		super( title,dateDebut,dateFin, description);
		// TODO Auto-generated constructor stub
	}
	public CharityProject( String title, Date dateDebut, Date dateFin, String description,String confirm) {
		super( title,dateDebut,dateFin, description);
		this.confirm=confirm;
		// TODO Auto-generated constructor stub
	}


	public CharityProject(String confirm) {
		super();
		this.confirm = confirm;
	}



	public CharityProject(String title, Date dateDebut, Date dateFin, String description,
			Utilisateur utilisateur) {
		super( title, dateDebut, dateFin, description, utilisateur);
		// TODO Auto-generated constructor stub
	}

	public CharityProject(String title, Date dateDebut, Date dateFin, String description,
			Utilisateur utilisateur,String picture) {
		super( title, dateDebut, dateFin, description, utilisateur);
		this.picture=picture;
		// TODO Auto-generated constructor stub
	}


	public CharityProject( String title, Date dateDebut, Date dateFin, String description,
			BusinessPlan businessPlan, Utilisateur utilisateur, List<Wishlist> wishlists) {
		super( title, dateDebut, dateFin, description, businessPlan, utilisateur, wishlists);
		// TODO Auto-generated constructor stub
	}



	public CharityProject(int idProject, String title, Date dateDebut, Date dateFin, String description,
			Utilisateur utilisateur) {
		super(idProject, title, dateDebut, dateFin, description, utilisateur);
		// TODO Auto-generated constructor stub
	}
	public CharityProject(int idProject, String title, Date dateDebut, Date dateFin, String description,
			Utilisateur utilisateur,Organization organization) {
		super(idProject, title, dateDebut, dateFin, description, utilisateur);
		this.organization=organization;
		// TODO Auto-generated constructor stub
	}



	


	public List<Article> getArticles() {
		return articles;
	}



	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public String getConfirm() {
		return confirm;
	}



	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}



	public Organization getOrganization() {
		return organization;
	}



	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
	
	

}
