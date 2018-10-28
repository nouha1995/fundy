package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.registry.infomodel.EmailAddress;

import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Project;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class CharityService extends GenericDAO<CharityProject> implements CharityServiceLocal,CharityServiceRemote{
	@PersistenceContext
	private EntityManager entityManager;

	
	public CharityService() {
		super(CharityProject.class);
		
	}
	
	@Override
	public void delete(CharityProject entity) {
		
		super.delete(entity);
	}
	
	@Override
	public CharityProject find(int entityID) {
		
		return super.find(entityID);
	}
	@Override
	public List<CharityProject> findAll() {
		
		return super.findAll();
	}
	@Override
	public void save(CharityProject entity) {
		
		super.save(entity);
	}
	@Override
	public CharityProject update(CharityProject entity) {
		
		return super.update(entity);
	}
	
	@Override
	protected CharityProject findOneResult(String namedQuery, Map<String, Object> parameters) {
		
		return super.findOneResult(namedQuery, parameters);
	}
	@Override
	public int saveNouha(CharityProject project) {
		entityManager.persist(project);
		return project.getIdProject();
	}

	@Override
	public void updateCharityProjectNouha(String title, Date dateDebut, Date dateFin, String description, int chPId) {
		CharityProject chProject=entityManager.find(CharityProject.class, chPId);
		if(title!=null)
		chProject.setTitle(title);
		if(dateDebut!=null)
		chProject.setDateDebut(dateDebut);
		if(dateFin!=null)
		chProject.setDateFin(dateFin);
		if(description!=null)
		chProject.setDescription(description);
		
	}
	
	@Override
	public List<CharityProject> getChPsByUserId(int idUser) {
	TypedQuery<CharityProject> query=entityManager.createQuery("SELECT c FROM CharityProject AS c WHERE c.utilisateur.id=:id",CharityProject.class);
		
		return query.setParameter("id", idUser).getResultList();
	}

	@Override
	public List<CharityProject> getChPsByUserComfirm() {
		
		TypedQuery<CharityProject> query=entityManager.createQuery("SELECT c FROM CharityProject AS c WHERE c.confirm=0",CharityProject.class);

		return query.getResultList();
	}

	@Override
	public void updateCharityProjectAdminNouha(String confirm, int chPId) {
		CharityProject chProject=entityManager.find(CharityProject.class, chPId);
		chProject.setConfirm(confirm);
		
	}
	@Override
	public String updateCharityProjectConfirmNouha(String confirm, int chPId) {
		CharityProject chProject=entityManager.find(CharityProject.class, chPId);
		chProject.setConfirm(confirm);
		return chProject.getConfirm();
		
	}

	@Override
	public void updatePictureChPNouha(String picture, int chPId) {
		CharityProject chProject=entityManager.find(CharityProject.class, chPId);
		chProject.setPicture(picture);
	}

	@Override
	public List<CharityProject> getChPsComfirmed() {
		TypedQuery<CharityProject> query=entityManager.createQuery("SELECT c FROM CharityProject AS c WHERE c.confirm=1",CharityProject.class);

		return query.getResultList();
	}

	@Override
	public void deleteChPNouha(int chPId) {

		CharityProject chProject=entityManager.find(CharityProject.class, chPId);
		entityManager.remove(chProject);
		
	}
	
	
}
