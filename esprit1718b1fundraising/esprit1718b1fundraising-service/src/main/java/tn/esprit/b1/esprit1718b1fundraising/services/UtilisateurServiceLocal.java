package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Local
public interface UtilisateurServiceLocal extends IGenericDAO<Utilisateur> {

	Utilisateur login(String login, String password);

	boolean loginUser(String username, String password);

	Utilisateur findByUsername(String username);

	int RedirectUser(Utilisateur userLogedIn);
	
	public boolean verifyMail(String mail);
	
	public boolean checkMailExistance(String email);
	
}
