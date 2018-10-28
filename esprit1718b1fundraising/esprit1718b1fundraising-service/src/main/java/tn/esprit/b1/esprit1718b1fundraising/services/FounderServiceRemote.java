package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface FounderServiceRemote extends IGenericDAO<Founder> {

}
