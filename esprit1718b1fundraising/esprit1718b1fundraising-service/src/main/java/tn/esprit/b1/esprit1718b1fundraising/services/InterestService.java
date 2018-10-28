package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hamcrest.TypeSafeDiagnosingMatcher;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Interests;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;

@Stateful
public class InterestService implements InterestServiceLocal,InterestServiceRemote{
@PersistenceContext
EntityManager em;
	@Override
	public void Addinterst(int a,Interests Enti) {
		em.persist(Enti);
		Interests v=em.find(Interests.class, Enti.getId());

		em.find(Investor.class, a);
		List<Investor>  liste=new ArrayList<>();
	liste.add(em.find(Investor.class, a));
	v.setInvestors(liste);
		
		
	}
	@Override
	public Interests VerifInvInterest(int a, String str) {
		Investor v=em.find(Investor.class, a);
			TypedQuery<Interests> query=  em.createQuery("select c from Interests c where c.interest=:rest ",Interests.class);
	Interests c=query.setParameter("rest", str).getSingleResult();
	System.out.println(c);
/*	List<Investor> liste=c.getInvestors();
	for(Investor i:liste){
		System.out.println(i);
		if(i.getId()==a){
			return true;
		}
	}
		return false;
	}*/ return c;}
	@Override
	public boolean InteretHere(String str) {
		TypedQuery<Long> query=  em.createQuery("select count(c) from Interests c where c.interest=:rest ",Long.class);
		long c=query.setParameter("rest", str).getSingleResult();
if(c==0){return false;}
		return true;
	}
	@Override
	public void AffecterInvestor(int a, String b) {
		TypedQuery<Interests> query=  em.createQuery("select c from Interests c where c.interest=:rest ",Interests.class);
		Interests c=query.setParameter("rest", b).getSingleResult();
		List<Investor>  liste=c.getInvestors();
		liste.add(em.find(Investor.class, a));
		c.setInvestors(liste);
		
	}
	@Override
	public Investor findbyID(int a) {
		// TODO Auto-generated method stub
		return em.find(Investor.class, a);
	}
	@Override
	public List<Interests> getAll() {
		TypedQuery<Interests> query=  em.createQuery("select c from Interests c ",Interests.class);
		List<Interests> c=query.getResultList();
		
	
		return c ;
	}

}
