package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1fundraising.entities.Message;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Local
public interface MessageServiceLocal  extends IGenericDAO<Message> {

}
