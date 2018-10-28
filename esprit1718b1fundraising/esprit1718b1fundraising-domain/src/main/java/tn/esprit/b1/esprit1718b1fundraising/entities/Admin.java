package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="admin")
public class Admin extends Utilisateur implements Serializable  {


	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	


	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String firstName, String lastName, String login, String password, String email,
			boolean isActive, boolean isBlocked, Date birthdate, List<Project> projects) {
		super(id, firstName, lastName, login, password, email, isActive, isBlocked, birthdate, projects);
		// TODO Auto-generated constructor stub
	}


	public Admin(String firstName, String lastName, String login, String password, String email, Date birthdate) {
		super(firstName, lastName, login, password, email, birthdate);
		// TODO Auto-generated constructor stub
	}


	
	
	
}
