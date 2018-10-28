package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Wishlist implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@EmbeddedId	
private WishlistPk wishlistPk;

@ManyToOne
@JoinColumn(name="idProject",referencedColumnName="idProject",insertable=false,updatable=false)
private Project project;

@ManyToOne
@JoinColumn(name="idInvestor",referencedColumnName="id",insertable=false,updatable=false)
private Investor investor;




public Project getProject() {
	return project;
}

public void setProject(Project project) {
	this.project = project;
}



public Investor getInvestor() {
	return investor;
}

public void setInvestor(Investor investor) {
	this.investor = investor;
}

public WishlistPk getWishlistPk() {
	return wishlistPk;
}

public void setWishlistPk(WishlistPk wishlistPk) {
	this.wishlistPk = wishlistPk;
}



}
