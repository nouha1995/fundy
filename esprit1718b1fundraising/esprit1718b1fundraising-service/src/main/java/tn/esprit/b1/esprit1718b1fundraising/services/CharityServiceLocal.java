package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Local
public interface CharityServiceLocal extends IGenericDAO<CharityProject> {

}
