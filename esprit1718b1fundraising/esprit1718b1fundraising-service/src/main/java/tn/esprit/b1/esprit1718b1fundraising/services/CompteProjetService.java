package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class CompteProjetService extends GenericDAO<CompteProjet> implements CompteProjetServiceLocal,CompteProjetServiceRemote{

	
	
	@PersistenceContext
	EntityManager em;
	public CompteProjetService() {
		super(CompteProjet.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(CompteProjet entity) {
		super.save(entity);
		
	}

	@Override
	public void delete(CompteProjet entity) {
		super.delete(entity);
	}

	@Override
	public CompteProjet update(CompteProjet entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public CompteProjet find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<CompteProjet> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void AddCompteProjet(int a, CompteProjet p) {
	em.persist(p);
	BusinessProject P=em.find(BusinessProject.class,a);
	p.setBus_Project(P);
	em.merge(p);
		
	}

}
