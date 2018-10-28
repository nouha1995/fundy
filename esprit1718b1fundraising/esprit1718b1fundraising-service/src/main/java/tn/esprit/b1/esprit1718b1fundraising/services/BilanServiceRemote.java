package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Bilan;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface BilanServiceRemote extends IGenericDAO<Bilan> {

}
