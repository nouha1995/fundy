package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface InvestorServiceRemote extends IGenericDAO<Investor> {
	public float retraitCompteInvestor(float a ,float b) ;

}
