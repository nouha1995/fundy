package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransactionPK implements Serializable{
	private int idInvestor;
	private int idCompteProjet;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCompteProjet;
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
		TransactionPK other = (TransactionPK) obj;
		if (idCompteProjet != other.idCompteProjet)
			return false;
		if (idInvestor != other.idInvestor)
			return false;
		return true;
	}
	public TransactionPK() {
		super();
	}
	public TransactionPK(int idInvestor, int idCompteProjet) {
		super();
		this.idInvestor = idInvestor;
		this.idCompteProjet = idCompteProjet;
	}
	

}
