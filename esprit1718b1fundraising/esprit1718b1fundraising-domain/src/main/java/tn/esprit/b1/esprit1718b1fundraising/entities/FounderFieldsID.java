package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FounderFieldsID
 *
 */
@Embeddable

public class FounderFieldsID implements Serializable {

	private int idFieldsPK;
	private int idFounderPK;
	private static final long serialVersionUID = 1L;

	public FounderFieldsID() {
		super();
	}
	
	public FounderFieldsID(int idFieldsPK, int idFounderPK) {
		super();
		this.idFieldsPK = idFieldsPK;
		this.idFounderPK = idFounderPK;
	}



	public int getIdFieldsPK() {
		return idFieldsPK;
	}

	public void setIdFieldsPK(int idFieldsPK) {
		this.idFieldsPK = idFieldsPK;
	}

	public int getIdFounderPK() {
		return idFounderPK;
	}

	public void setIdFounderPK(int idFounderPK) {
		this.idFounderPK = idFounderPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFounderPK;
		result = prime * result + idFieldsPK;
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
		FounderFieldsID other = (FounderFieldsID) obj;
		if (idFounderPK != other.idFounderPK)
			return false;
		if (idFieldsPK != other.idFieldsPK)
			return false;
		return true;
	}
	
	
	
   
}
