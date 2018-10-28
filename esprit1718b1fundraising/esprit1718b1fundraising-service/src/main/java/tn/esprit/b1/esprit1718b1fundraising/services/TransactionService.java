package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteInvestor;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Transaction;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class TransactionService extends GenericDAO<Transaction>implements TransactinServiceRemote,TransactionServiceLocal {
	
@PersistenceContext
EntityManager em;
	public TransactionService() {
		super(Transaction.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Transaction entity) {
		super.save(entity);
		
	}

	@Override
	public void delete(Transaction entity) {
super.delete(entity);		
	}

	@Override
	public Transaction update(Transaction entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction find(int entityID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifsomme(float a, float b) {
    if(a-b>0){
    	return true;
    }
		return false;
	}

	@Override
	public BusinessProject MaxversedProject(List<Transaction> Liste) {
		return null;
	}

	@Override
	public List<Object[]> FindByDate(Date date) {
		Query query =  em.createQuery("select sum(t.somversement) ,t.compteprojet  from Transaction t where t.dateversement=:date group by t.compteprojet");
	
		return query.setParameter("date", date).getResultList();
	}

	@Override
	public List<BusinessProject> FindProjetparInvestor(int idInvestor) {
	//	Query query =  em.createQuery("select ,t.compteprojet  from Transaction t where t.dateversement=:date group by t.compteprojet");

		return null;
	}

	@Override
	public List<Transaction> BrowseTransaction(int id) {
	Investor v=	em.find(Investor.class,id);
		Query query =  em.createQuery("select t  from Transaction t where t.investor=:investor");
		
		return query.setParameter("investor",v ).getResultList();
	}

	@Override
	public List<Date> getlistDate() {
		TypedQuery<Date> query=em.createQuery("select t.dateversement from Transaction t group by t.dateversement",Date.class);
		return query.getResultList();
	}

	@Override
	public Long getfoundersNumber() {
		TypedQuery<Long> query	=em.createQuery("select count(t) from Utilisateur t where user_type=:ustype",Long.class);
		return query.setParameter("ustype","founder").getSingleResult();
	}

	@Override
	public Long getInvestorsNumber() {
		TypedQuery<Long> query	=em.createQuery("select count(t) from Utilisateur t where user_type=:ustype",Long.class);
		return query.setParameter("ustype","investor").getSingleResult();

	}

	@Override
	public Long getProjectNumber() {
	
		TypedQuery<Long> query	=em.createQuery("select count(t) from Project t where project_type=:protype ",Long.class);
		return query.setParameter("protype","businessP").getSingleResult();
	}

	@Override
	public Long getUserNumber() {
		TypedQuery<Long> query	=em.createQuery("select count(t) from Utilisateur t ",Long.class);
		return query.getSingleResult();
	}

	@Override
	public CompteInvestor getCompteInvestor(int a) {
		Investor v =em.find(Investor.class,a);
		TypedQuery<CompteInvestor> query=em.createQuery("select t from CompteInvestor  t where t.investor=:inv ",CompteInvestor.class);
		return query.setParameter("inv", v).getSingleResult();
	
	}

}
