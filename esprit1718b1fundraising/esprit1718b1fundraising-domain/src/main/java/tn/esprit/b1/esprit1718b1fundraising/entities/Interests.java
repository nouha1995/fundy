package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Interests implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4699142159613359499L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String interest;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Investor> Investors;
	@Override
	public String toString() {
		return "Interests [interest=" + interest + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public List<Investor> getInvestors() {
		return Investors;
	}
	public void setInvestors(List<Investor> investors) {
		Investors = investors;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Interests() {
		super();
	}
	public Interests( String interest) {
		
		this.interest = interest;
	}
	
	

}
