package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
@DiscriminatorValue(value="founder")
public class Founder extends Utilisateur implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "founder")
	private Set<FounderFields> Lfields;
	@OneToOne(mappedBy="founder")
	private CompteFounder comptefounder;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "founder")
	private Set<FounderFollowers> Followers;
	@OneToOne(mappedBy="founder")
	private BusinessProject Business;
	private String bio;

	public Set<FounderFollowers> getFollowers() {
		return Followers;
	}
	public void setFollowers(Set<FounderFollowers> followers) {
		Followers = followers;
	}
	public CompteFounder getComptefounder() {
		return comptefounder;
	}
	public void setComptefounder(CompteFounder comptefounder) {
		this.comptefounder = comptefounder;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Set<FounderFields> getLfields() {
		return Lfields;
	}
	public void setLfields(Set<FounderFields> lfields) {
		Lfields = lfields;
	}
	
	public Founder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Founder(int id, String firstName, String lastName, String login, String password, String email,
			Date birthdate, List<Project> projects, List<Event> events, List<Reclamation> reclamations,
			List<Message> messageSender, List<Message> messageReceiver) {
		super(id, firstName, lastName, login, password, email, birthdate, projects, events, reclamations, messageSender,
				messageReceiver);
		// TODO Auto-generated constructor stub
	}
	
	
	public Founder(String firstName, String lastName, String login, String password, String email, Date birthdate) {
		super(firstName, lastName, login, password, email, birthdate);
		// TODO Auto-generated constructor stub
	}
	
	
	public Founder(Utilisateur newUser) {
		// TODO Auto-generated constructor stub
	}

	
	
	public void addCharityProject(CharityProject chp){
		chp.setUtilisateur(this);
		this.getProjects().add(chp);
	}

}