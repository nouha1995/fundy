package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessPlan;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Stateful
public class BuisnessPlanService extends GenericDAO<BusinessPlan>implements BuisnessPlanServiceLocal ,BuisnessPlanServiceRemote {

	@PersistenceContext
	EntityManager em;
	public BuisnessPlanService() {
		super(BusinessPlan.class);
	}

	@Override
	public void save(BusinessPlan entity) {
	super.save(entity);
		
	}

	@Override
	public void delete(BusinessPlan entity) {
		super.delete(entity);
		
	}

	@Override
	public BusinessPlan update(BusinessPlan entity) {
		return super.update(entity);
	}

	@Override
	public BusinessPlan find(int entityID) {

		return super.find(entityID);
	}

	@Override
	public List<BusinessPlan> findAll() {
		return super.findAll();
		
	}

	@Override
	public BusinessPlan findByProject(EntityManager em, BusinessProject p) {
		TypedQuery<BusinessPlan> query=em.createQuery("select c from BusinessPlan c where c.project=:par",BusinessPlan.class);
		
		return query.setParameter("par", p).getSingleResult();
	}

	@Override
	public int AddBuiPlan( BusinessPlan b) {
em.persist(b);
return b.getId();
	}

	@Override
	public void Affecter(int a, int b) {
BusinessPlan d=em.find(BusinessPlan.class, a)	;
BusinessProject h=em.find(BusinessProject.class, b);
h.setBusinessPlan(d);
em.merge(h);
d.setProject(h);
em.merge(d);
	}
	


}
