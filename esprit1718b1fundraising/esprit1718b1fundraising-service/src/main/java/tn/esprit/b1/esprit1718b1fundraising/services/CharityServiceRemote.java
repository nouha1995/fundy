package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Project;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface CharityServiceRemote extends IGenericDAO<CharityProject>{

	int saveNouha(CharityProject project);
	void updateCharityProjectNouha(String title,Date dateDebut,Date dateFin,String description,int chPId);
	void updateCharityProjectAdminNouha(String confirm,int chPId);
	List<CharityProject> getChPsByUserId(int idUser);
	List<CharityProject> getChPsByUserComfirm();
	void updatePictureChPNouha(String picture,int chPId);
	List<CharityProject> getChPsComfirmed();
	public void deleteChPNouha(int chPId);
	public String updateCharityProjectConfirmNouha(String confirm, int chPId);

}
