package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;

import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class FounderService extends GenericDAO<Founder>implements FounderServiceLocal,FounderServiceRemote{

	public FounderService() {
		super(Founder.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Founder entity) {
	super.save(entity);
		
	}

	@Override
	public void delete(Founder entity) {
super.delete(entity);	
	}

	@Override
	public Founder update(Founder entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Founder find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<Founder> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
