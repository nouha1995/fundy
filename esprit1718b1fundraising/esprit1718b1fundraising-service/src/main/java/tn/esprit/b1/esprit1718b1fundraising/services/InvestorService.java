package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;

import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class InvestorService extends GenericDAO<Investor>implements InvestorServiceRemote,InvestorServiceLocal {

	public InvestorService() {
		super(Investor.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Investor entity) {
super.save(entity);		
	}

	@Override
	public void delete(Investor entity) {
super.save(entity);		
	}

	@Override
	public Investor update(Investor entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Investor find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<Investor> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public float retraitCompteInvestor(float a, float b) {
		// TODO Auto-generated method stub
		return a-b;
	}

}
