package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.CompteFounder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface CompteFounderServiceRemote extends IGenericDAO<CompteFounder> {
	public void AffecterFounderCompte(int idf,int idC);

}
