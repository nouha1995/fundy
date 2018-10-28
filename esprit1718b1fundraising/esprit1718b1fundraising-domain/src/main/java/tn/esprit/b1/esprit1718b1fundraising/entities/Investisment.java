package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Investisment  implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InvestismentPK investismentPk;
	private Float Amount;
	@Temporal(TemporalType.TIMESTAMP)

	private Date date;

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name="idInvestor",referencedColumnName="id",insertable=false,updatable=false)
	private Investor investor;
	
	@ManyToOne
	@JoinColumn(name="idBusinessProject",referencedColumnName="idProject",insertable=false,updatable=false)	
	private BusinessProject businessProject;

	public InvestismentPK getInvestismentPk() {
		return investismentPk;
	}

	public void setInvestismentPk(InvestismentPK investismentPk) {
		this.investismentPk = investismentPk;
	}

	public Float getAmount() {
		return Amount;
	}

	public void setAmount(Float amount) {
		Amount = amount;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public BusinessProject getBusinessProject() {
		return businessProject;
	}

	public void setBusinessProject(BusinessProject businessProject) {
		this.businessProject = businessProject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
