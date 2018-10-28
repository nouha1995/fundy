package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value="Investor")
public class CompteInvestor extends Compte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
private Investor investor;

public Investor getInvestor() {
	return investor;
}

public void setInvestor(Investor investor) {
	this.investor = investor;
}

}
