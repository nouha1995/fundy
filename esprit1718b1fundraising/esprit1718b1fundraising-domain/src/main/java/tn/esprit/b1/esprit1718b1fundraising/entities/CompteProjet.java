package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue(value="project")
public class CompteProjet extends Compte implements Serializable {
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	private static final long serialVersionUID = 1L;
	

	@OneToOne
	private BusinessProject Bus_Project;
	@OneToMany(mappedBy="compteprojet")
	private List<Transaction> transaction;

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BusinessProject getBus_Project() {
		return Bus_Project;
	}

	public void setBus_Project(BusinessProject bus_Project) {
		Bus_Project = bus_Project;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CompteProjet() {
		
	}

	public CompteProjet(float som) {
		super(som);
		// TODO Auto-generated constructor stub
	}

	public CompteProjet(float som, BusinessProject bus_Project) {
		super(som);
		Bus_Project = bus_Project;
	}

	
	

}
