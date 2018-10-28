

package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.Reclamation;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

/**
 * Session Bean implementation class ReclamationService
 */
@Stateless
@LocalBean
public class ClaimService extends GenericDAO<Reclamation> implements ClaimServiceLocal,ClaimServiceRemote{
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ClaimService() {

		super(Reclamation.class);

	}

	@Override
	public Reclamation findByBody(String body) {
		try {
			TypedQuery<Reclamation> q = entityManager.createQuery("SELECT u FROM Reclamation u where u.body =:body",
					Reclamation.class);
			q.setParameter("body", body);
			Reclamation reclam = q.getSingleResult();
			return reclam;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	public Reclamation findBySubject(String subject) {
		try {
			TypedQuery<Reclamation> q = entityManager
					.createQuery("SELECT u FROM Reclamation u where u.subject =:subject", Reclamation.class);
			q.setParameter("subject", subject);
			Reclamation reclam = q.getSingleResult();
			return reclam;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	public List<String> findMessagesByuser(String resolved) {

		try {
			TypedQuery<String> q = entityManager.createQuery("SELECT u FROM Reclamation u ", String.class);
			// q.setParameter("resolved", resolved);

			List<String> entities = null;
			entities = q.getResultList();

			return entities;

		} catch (javax.persistence.NoResultException e) {
			System.out.println("pas de messages");
			return null;
		}

	}
	

	@Override
	public void deleteSpec(Reclamation entity) {
		entityManager.refresh(entity);
		entityManager.remove(entity);

	}
	


	/*
	 * @Override public Reclamation findBycl(String body) { try {
	 * TypedQuery<Reclamation> q = entityManager.
	 * createQuery("SELECT u FROM Reclamation u where u.body =:body",
	 * Reclamation.class); q.setParameter("body", body); Reclamation reclam =
	 * q.getSingleResult(); return reclam; } catch
	 * (javax.persistence.NoResultException e) { return null; } }
	 */
}

