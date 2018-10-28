package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Reclamation;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface ClaimServiceRemote extends IGenericDAO<Reclamation>{
	Reclamation findByBody(String body);


	List<String> findMessagesByuser(String resolved);

	Reclamation findBySubject(String subject);

	void deleteSpec(Reclamation entity);
}
