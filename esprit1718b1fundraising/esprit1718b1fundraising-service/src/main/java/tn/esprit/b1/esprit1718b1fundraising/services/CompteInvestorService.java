package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;

import tn.esprit.b1.esprit1718b1fundraising.entities.CompteInvestor;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;
@Stateful
public class CompteInvestorService extends GenericDAO<CompteInvestor>implements CompteInvestorServiceRemote,CompteInvestorServiceLocal{

	public CompteInvestorService() {
		super(CompteInvestor.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(CompteInvestor entity) {
 super.save(entity);		
	}

	@Override
	public void delete(CompteInvestor entity) {
		super.delete(entity);
		
	}

	@Override
	public CompteInvestor update(CompteInvestor entity) {
		// TODO Auto-generated method stub
		return super.update(entity) ;
	}

	@Override
	public CompteInvestor find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<CompteInvestor> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}



}
