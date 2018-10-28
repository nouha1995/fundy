package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value="founder")
public class CompteFounder extends Compte  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Founder founder;

	public Founder getFounder() {
		return founder;
	}

	public void setFounder(Founder founder) {
		this.founder = founder;
	}

	
	public CompteFounder() {
		
	}

	public CompteFounder(float som) {
		super(som);
		
	}
	

}
