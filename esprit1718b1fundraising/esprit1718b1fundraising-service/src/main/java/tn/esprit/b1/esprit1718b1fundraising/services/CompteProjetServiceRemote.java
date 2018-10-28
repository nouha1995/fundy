package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface CompteProjetServiceRemote extends IGenericDAO<CompteProjet>{
	public void AddCompteProjet(int a ,CompteProjet p);

}
