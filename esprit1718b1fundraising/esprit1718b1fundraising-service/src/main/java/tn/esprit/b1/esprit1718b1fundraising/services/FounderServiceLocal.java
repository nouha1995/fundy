package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;



@Local
public interface FounderServiceLocal extends IGenericDAO<Founder> {

}
