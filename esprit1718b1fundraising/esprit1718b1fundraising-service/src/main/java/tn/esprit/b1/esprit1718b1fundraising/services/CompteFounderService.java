package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1fundraising.entities.CompteFounder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class CompteFounderService extends GenericDAO<CompteFounder> implements CompteFounderServiceLocal,CompteFounderServiceRemote {

	@PersistenceContext
	EntityManager em;
	public CompteFounderService() {
		super(CompteFounder.class);
	}

	@Override
	public void save(CompteFounder entity) {
      	super.save(entity);	
	}

	@Override
	public void delete(CompteFounder entity) {
			super.delete(entity);
	}

	@Override
	public CompteFounder update(CompteFounder entity) {
		return super.update(entity);
	}

	@Override
	public CompteFounder find(int entityID) {
		return super.find(entityID);
	}

	@Override
	public List<CompteFounder> findAll() {
		return super.findAll();
	}

	@Override
	public void AffecterFounderCompte(int idf, int idC) {
		Founder f=em.find(Founder.class, idf);		
		CompteFounder c=em.find(CompteFounder.class, idC);
		c.setFounder(f);
	}

	
}
