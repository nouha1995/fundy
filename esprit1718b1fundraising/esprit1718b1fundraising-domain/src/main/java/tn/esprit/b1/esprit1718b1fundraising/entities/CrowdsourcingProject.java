package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="CrowdsourcingP")
public class CrowdsourcingProject extends Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
