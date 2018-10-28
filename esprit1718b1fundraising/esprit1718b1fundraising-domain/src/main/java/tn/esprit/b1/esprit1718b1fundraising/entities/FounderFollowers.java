package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import javax.persistence.*;





/**
 * Entity implementation class for Entity: ArtistFollowers
 *
 */
@Entity

public class FounderFollowers implements Serializable {

	@EmbeddedId
	private FounderFollowersID founderFollowersId;
	@ManyToOne
	@JoinColumn(name="idUserPK",insertable=false,updatable=false)
	private Utilisateur user;
	@ManyToOne
	@JoinColumn(name="idFounderPK",insertable=false,updatable=false)
	private Founder founder;
	private static final long serialVersionUID = 1L;

	public FounderFollowers() {
		super();
	}

	public FounderFollowersID getFounderId() {
		return founderFollowersId;
	}

	public void setFounderId(FounderFollowersID founderFollowersId) {
		this.founderFollowersId = founderFollowersId;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Founder getFounder() {
		return founder;
	}

	public void setFounder(Founder founder) {
		this.founder = founder;
	}

}
