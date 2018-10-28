package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArtistFollowersID
 *
 */
@Embeddable

public class FounderFollowersID implements Serializable {

	private int idUserPK;
	private int idFounderPK;
	private static final long serialVersionUID = 1L;

	public FounderFollowersID() {
		super();
	}

	public FounderFollowersID(int idUserPK, int idFounderPK) {
		super();
		this.idUserPK = idUserPK;
		this.idFounderPK = idFounderPK;
	}

	public int getIdUserPK() {
		return idUserPK;
	}

	public void setIdUserPK(int idUserPK) {
		this.idUserPK = idUserPK;
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
		result = prime * result + idUserPK;
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
		FounderFollowersID other = (FounderFollowersID) obj;
		if (idFounderPK != other.idFounderPK)
			return false;
		if (idUserPK != other.idUserPK)
			return false;
		return true;
	}	
	
}
