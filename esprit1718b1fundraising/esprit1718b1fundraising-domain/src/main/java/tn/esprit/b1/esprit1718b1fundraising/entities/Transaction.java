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
public class Transaction implements Serializable {
	@EmbeddedId
    private TransactionPK transactionPK;
	@Temporal(TemporalType.TIMESTAMP)

	private Date dateversement;
	private float somversement;
	@ManyToOne
	@JoinColumn(name="idInvestor",referencedColumnName="id",insertable=false,updatable=false)
	private Investor investor;
	@ManyToOne
	@JoinColumn(name="idCompteProjet",referencedColumnName="id",insertable=false,updatable=false)
	private CompteProjet compteprojet;
	public TransactionPK getTransactionPK() {
		return transactionPK;
	}
	public void setTransactionPK(TransactionPK transactionPK) {
		this.transactionPK = transactionPK;
	}
	public Date getDateversement() {
		return dateversement;
	}
	public void setDateversement(Date dateversement) {
		this.dateversement = dateversement;
	}
	public float getSomversement() {
		return somversement;
	}
	public void setSomversement(float somversement) {
		this.somversement = somversement;
	}
	public Investor getInvestor() {
		return investor;
	}
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	public CompteProjet getCompteprojet() {
		return compteprojet;
	}
	public void setCompteprojet(CompteProjet compteprojet) {
		this.compteprojet = compteprojet;
	}
	
	public Transaction() {
		super();
	}
	public Transaction(int idinvestor,int idcompte, Date dateversement, float somversement) {
		super();
		this.dateversement = dateversement;
		this.somversement = somversement;
	}
	public Transaction(TransactionPK transactionPK, Date dateversement, float somversement) {
		super();
		this.transactionPK = transactionPK;
		this.dateversement = dateversement;
		this.somversement = somversement;
	}
	@Override
	public String toString() {
		return "Transaction [transactionPK=" + transactionPK + ", dateversement=" + dateversement + ", somversement="
				+ somversement + "]";
	}

	
	
	
 
}
