package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bilan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
private float marge;
private float Benifice;
private float seuil;
private String pointMort;
	
	@OneToOne
	private BusinessProject buisness_projet;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public float getMarge() {
		return marge;
	}
	public void setMarge(float marge) {
		this.marge = marge;
	}
	public float getBenifice() {
		return Benifice;
	}
	public void setBenifice(float benifice) {
		Benifice = benifice;
	}
	public float getSeuil() {
		return seuil;
	}
	public void setSeuil(float seuil) {
		this.seuil = seuil;
	}
	public String getPointMort() {
		return pointMort;
	}
	public void setPointMort(String pointMort) {
		this.pointMort = pointMort;
	}
	public Bilan(float marge, float benifice, float seuil, String pointMort) {
		super();
		this.marge = marge;
		Benifice = benifice;
		this.seuil = seuil;
		this.pointMort = pointMort;
	}
	public BusinessProject getBuisness_projet() {
		return buisness_projet;
	}
	public void setBuisness_projet(BusinessProject buisness_projet) {
		this.buisness_projet = buisness_projet;
	}
	public Bilan() {
		super();
	}


	
}
