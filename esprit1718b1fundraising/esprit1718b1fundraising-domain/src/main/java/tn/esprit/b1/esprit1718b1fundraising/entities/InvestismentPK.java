package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class InvestismentPK implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idInvestor;
	private int idBusinessProject;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBusinessProject;
		result = prime * result + idInvestor;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestismentPK other = (InvestismentPK) obj;
		if (idBusinessProject != other.idBusinessProject)
			return false;
		if (idInvestor != other.idInvestor)
			return false;
		return true;
	}
}
