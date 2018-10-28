package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1fundraising.entities.User;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {

	User login(String login, String password);

}
