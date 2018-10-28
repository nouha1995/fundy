package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity

public class BusinessPlan implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	@OneToOne
	private BusinessProject project;
	private String name_creator;
	private String adress;
	private String typeprojet;
   private float cap_per;
   private float immobilisation;
   private float stock;
   private float creance;
   private float dettes;
   private String risque_juridique;
   private String ris_technique;
   private String risque_politique;
   private String risque_financier;
   private String risque_clmarché;
    
 
public static long getSerialversionuid() {
	return serialVersionUID;
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(BusinessProject project) {
		this.project = project;
	}
	public String getName_creator() {
		return name_creator;
	}
	public void setName_creator(String name_creator) {
		this.name_creator = name_creator;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getTypeprojet() {
		return typeprojet;
	}
	public void setTypeprojet(String typeprojet) {
		this.typeprojet = typeprojet;
	}
	public float getCap_per() {
		return cap_per;
	}
	public void setCap_per(float cap_per) {
		this.cap_per = cap_per;
	}
	public float getImmobilisation() {
		return immobilisation;
	}
	public void setImmobilisation(float immobilisation) {
		this.immobilisation = immobilisation;
	}
	public float getStock() {
		return stock;
	}
	public void setStock(float stock) {
		this.stock = stock;
	}
	public float getCreance() {
		return creance;
	}
	public void setCreance(float creance) {
		this.creance = creance;
	}
	public float getDettes() {
		return dettes;
	}
	public void setDettes(float dettes) {
		this.dettes = dettes;
	}
	public String getRisque_juridique() {
		return risque_juridique;
	}
	public void setRisque_juridique(String risque_juridique) {
		this.risque_juridique = risque_juridique;
	}
	public String getRis_technique() {
		return ris_technique;
	}
	public void setRis_technique(String ris_technique) {
		this.ris_technique = ris_technique;
	}
	public String getRisque_politique() {
		return risque_politique;
	}
	public void setRisque_politique(String risque_politique) {
		this.risque_politique = risque_politique;
	}
	public String getRisque_financier() {
		return risque_financier;
	}
	public void setRisque_financier(String risque_financier) {
		this.risque_financier = risque_financier;
	}
	public String getRisque_clmarché() {
		return risque_clmarché;
	}
	public void setRisque_clmarché(String risque_clmarché) {
		this.risque_clmarché = risque_clmarché;
	}
	public BusinessPlan(String name_creator, String adress, String typeprojet, float cap_per, float immobilisation,
			float stock, float creance, float dettes, String risque_juridique, String ris_technique,
			String risque_politique, String risque_financier, String risque_clmarché) {
		super();
		this.name_creator = name_creator;
		this.adress = adress;
		this.typeprojet = typeprojet;
		this.cap_per = cap_per;
		this.immobilisation = immobilisation;
		this.stock = stock;
		this.creance = creance;
		this.dettes = dettes;
		this.risque_juridique = risque_juridique;
		this.ris_technique = ris_technique;
		this.risque_politique = risque_politique;
		this.risque_financier = risque_financier;
		this.risque_clmarché = risque_clmarché;
	}
	public BusinessPlan() {
		super();
	}
	@Override
	public String toString() {
		return "BusinessPlan [project=" + project + ", name_creator=" + name_creator + ", adress=" + adress
				+ ", typeprojet=" + typeprojet + ", cap_per=" + cap_per + ", immobilisation=" + immobilisation
				+ ", stock=" + stock + ", creance=" + creance + ", dettes=" + dettes + ", risque_juridique="
				+ risque_juridique + ", ris_technique=" + ris_technique + ", risque_politique=" + risque_politique
				+ ", risque_financier=" + risque_financier + ", risque_clmarché=" + risque_clmarché + "]";
	}


	
	
    
}


