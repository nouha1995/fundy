package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value="investor")
public class Investor extends Utilisateur implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="investor")
	private List<Wishlist> wishlists;
	
	@OneToMany
	private List<CrowdsourcingProject> crowdsourcingProjects;
	
	@OneToMany(mappedBy="investor")
	private List<Investisment> investisments;
	@OneToOne(mappedBy="investor")
	private CompteInvestor compteinvestor;
	@OneToMany(mappedBy="investor")
	private List<Transaction> transaction;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Interests> Interests;
	private String Description;
	private String Address;
	private float Money;
	
public List<Interests> getInterests() {
		return Interests;
	}

	public void setInterests(List<Interests> interests) {
		Interests = interests;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public float getMoney() {
		return Money;
	}

	public void setMoney(float money) {
		Money = money;
	}

	public CompteInvestor getCompteinvestor() {
		return compteinvestor;
	}

	public void setCompteinvestor(CompteInvestor compteinvestor) {
		this.compteinvestor = compteinvestor;
	}

	public List<Investisment> getInvestisments() {
		return investisments;
	}

	public void setInvestisments(List<Investisment> investisments) {
		this.investisments = investisments;
	}
	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public List<CrowdsourcingProject> getCrowdsourcingProjects() {
		return crowdsourcingProjects;
	}

	public void setCrowdsourcingProjects(List<CrowdsourcingProject> crowdsourcingProjects) {
		this.crowdsourcingProjects = crowdsourcingProjects;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Investor(int id, String firstName, String lastName, String login, String password, String email,
			boolean isActive, boolean isBlocked, Date birthdate, List<Project> projects) {
		super(id, firstName, lastName, login, password, email, isActive, isBlocked, birthdate, projects);
	}

	public Investor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Investor(int id, String firstName, String lastName, String login, String password, String email,
			Date birthdate, List<Project> projects, List<Event> events, List<Reclamation> reclamations,
			List<Message> messageSender, List<Message> messageReceiver) {
		super(id, firstName, lastName, login, password, email, birthdate, projects, events, reclamations, messageSender,
				messageReceiver);
		// TODO Auto-generated constructor stub
	}

	public Investor(String firstName, String lastName, String login, String password, String email, Date birthdate) {
		super(firstName, lastName, login, password, email, birthdate);
		// TODO Auto-generated constructor stub
	}

	public Investor(Utilisateur newUser) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Investor [Interests=" + Interests + ", Description=" + Description + ", Address=" + Address
			 + "]";
	}
	

}
