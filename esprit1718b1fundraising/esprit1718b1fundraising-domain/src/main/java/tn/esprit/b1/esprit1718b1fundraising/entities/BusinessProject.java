package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
@DiscriminatorValue(value="businessP")
public class BusinessProject extends Project implements Serializable {

	

	@Override
	public String toString() {
		return "BusinessProject [Categorie=" + Categorie + ", localisation=" + localisation + ", financement="
				+ financement + ", CA=" + CA + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	protected String Categorie;
	protected String localisation;
	protected float financement;
	protected String equity1;
	protected String equity2;
	protected String equity3;
	protected String devise;
	protected String photo;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getEquity1() {
		return equity1;
	}
	public void setEquity1(String equity1) {
		this.equity1 = equity1;
	}
	public String getEquity2() {
		return equity2;
	}
	public void setEquity2(String equity2) {
		this.equity2 = equity2;
	}
	public String getEquity3() {
		return equity3;
	}
	public void setEquity3(String equity3) {
		this.equity3 = equity3;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}

	protected float CA;
	protected float ch_var;
	protected float ch_fixe;
	protected boolean isValide;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="businessProject")
	private List<Investisment> investisments;
	
	    @OneToOne
	   private Founder founder;
	  @OneToOne (mappedBy="buisness_projet")
		private Bilan bilan;
		@OneToOne(mappedBy="Bus_Project")
		private CompteProjet compteprojet;
		@OneToOne(mappedBy="project")
		private BusinessPlan businessPlan;
		
		
	   
		public Bilan getBilan() {
		return bilan;
	}
	public void setBilan(Bilan bilan) {
		this.bilan = bilan;
	}

	public List<Investisment> getInvestisments() {
		return investisments;
	}
	
	public float getCA() {
		return CA;
	}
	public void setCA(float cA) {
		CA = cA;
	}
	public float getCh_var() {
		return ch_var;
	}
	public void setCh_var(float ch_var) {
		this.ch_var = ch_var;
	}
	public float getCh_fixe() {
		return ch_fixe;
	}
	public void setCh_fixe(float ch_fixe) {
		this.ch_fixe = ch_fixe;
	}
	public void setInvestisments(List<Investisment> investisments) {
		this.investisments = investisments;
	}
	public CompteProjet getCompteprojet() {
		return compteprojet;
	}
	public void setCompteprojet(CompteProjet compteprojet) {
		this.compteprojet = compteprojet;
	}
	public String getCategorie() {
		return Categorie;
	}
	public void setCategorie(String categorie) {
		Categorie = categorie;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public float getFinancement() {
		return financement;
	}
	public void setFinancement(float financement) {
		this.financement = financement;
	}
	
	public boolean isValide() {
		return isValide;
	}
	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}
	public BusinessProject() {
		super();
	}
	
	public BusinessProject(String title, String description,String categorie,String localisation,float financement) {
		super(title, description);
		this.Categorie=categorie;
		this.financement=financement;
		
		this.localisation=localisation;
		
		// TODO Auto-generated constructor stub
	}
	public Founder getFounder() {
		return founder;
	}
	public void setFounder(Founder founder) {
		this.founder = founder;
	}
	public BusinessPlan getBusinessPlan() {
		return businessPlan;
	}
	public void setBusinessPlan(BusinessPlan businessPlan) {
		this.businessPlan = businessPlan;
	}
	public BusinessProject(String title, Date dateDebut, Date dateFin, String description, Utilisateur utilisateur,
			String categorie, String localisation, float financement) {
		super(title, dateDebut, dateFin, description, utilisateur);
		Categorie = categorie;
		this.localisation = localisation;
		this.financement = financement;
	
	}
	public BusinessProject(String title, Date dateFin, String categorie, String localisation, float financement, float cA, float ch_var,
			float ch_fixe) {
		super(title, dateFin);
		Categorie = categorie;
		this.localisation = localisation;
		this.financement = financement;
		CA = cA;
		this.ch_var = ch_var;
		this.ch_fixe = ch_fixe;
	}

	public BusinessProject(String title, Date dateFin, float financement, float cA, float ch_var,float ch_fixe) {
		super(title, dateFin);
		
		this.financement = financement;
		this.CA = cA;
		this.ch_var = ch_var;
		this.ch_fixe = ch_fixe;
	}
	public BusinessProject(String title, Date dateDebut, Date dateFin, String description, Utilisateur utilisateur,
			String categorie, String localisation, float financement, float cA, float ch_var,
			float ch_fixe) {
		super(title, dateDebut, dateFin, description, utilisateur);
		Categorie = categorie;
		this.localisation = localisation;
		this.financement = financement;
		CA = cA;
		this.ch_var = ch_var;
		this.ch_fixe = ch_fixe;
	}
	public BusinessProject(String Title, Date dateFin,String categorie, String localisation, float financement, String equity1, String equity2,
			String equity3, String devise, String photo, float cA, float ch_var, float ch_fixe, boolean isValide) {
		super(Title,dateFin);
		Categorie = categorie;
		this.localisation = localisation;
		this.financement = financement;
		this.equity1 = equity1;
		this.equity2 = equity2;
		this.equity3 = equity3;
		this.devise = devise;
		this.photo = photo;
		CA = cA;
		this.ch_var = ch_var;
		this.ch_fixe = ch_fixe;
		this.isValide = isValide;
	}
	
	
	
}
