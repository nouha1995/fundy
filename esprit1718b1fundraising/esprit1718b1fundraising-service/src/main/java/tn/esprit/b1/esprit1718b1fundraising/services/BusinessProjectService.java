package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteFounder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class BusinessProjectService extends GenericDAO<BusinessProject> implements BusinessProjectRemote,BusinessProjectLocal {
  
	@PersistenceContext
	EntityManager em;
	public BusinessProjectService() {
		super(BusinessProject.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(BusinessProject entity) {
		super.save(entity);
		
	}

	@Override
	public void delete(BusinessProject entity) {
		super.delete(entity);
		
	}

	@Override
	public BusinessProject update(BusinessProject entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public BusinessProject find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<BusinessProject> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void AffecterUserProject(int iduser, int idprojet) {
		Founder f=em.find(Founder.class, iduser);		
		BusinessProject c=em.find(BusinessProject.class, idprojet);
		c.setFounder(f);		
	}

	@Override
	public float calculFondRoulement(float a, float b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public List<BusinessProject> FindValidProjects() {
	TypedQuery<BusinessProject> query=em.createQuery("select c from BusinessProject c where c.isValide=:par",BusinessProject.class);
	List <BusinessProject> result=query.setParameter("par", true).getResultList();
	
	return result;
	}

	@Override
	public List<BusinessProject> SearchProjectByCath(String cat) {
		TypedQuery<BusinessProject> query=em.createQuery("select c from BusinessProject c where c.Categorie=:cat",BusinessProject.class);
		List <BusinessProject> result=query.setParameter("cat", cat).getResultList();
		return result;
	}

	@Override
	public int AddBP(BusinessProject b) {
em.persist(b);		return b.getIdProject();

	}

	@Override
	public List<Object[]> BrowseProjects() {
		Query query =  em.createQuery("select count(t) ,t.Categorie  from Project t  group by t.Categorie");
		
		return query.getResultList();
		
	}

	@Override
	public BusinessProject getfounder(int a) {
		Founder f=	em.find(Founder.class, a);

	TypedQuery<BusinessProject> query=em.createQuery("select p from BusinessProject p where p.founder=:f",BusinessProject.class);
			return	query.setParameter("f", f).getSingleResult();
	}

	@Override
	public void AffecterFounder(int a, int b) {
BusinessProject h=em.find(BusinessProject.class,a);
Founder m=em.find(Founder.class, b);
h.setFounder(m);
em.merge(h);

	}
	

}
