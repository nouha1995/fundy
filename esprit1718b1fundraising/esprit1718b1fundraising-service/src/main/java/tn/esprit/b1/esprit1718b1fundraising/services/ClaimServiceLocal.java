package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1fundraising.entities.Reclamation;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Local
public interface ClaimServiceLocal extends IGenericDAO<Reclamation> {

	Reclamation findByBody(String body);

	List<String> findMessagesByuser(String resolved);

	Reclamation findBySubject(String subject);

	void deleteSpec(Reclamation entity);
}
