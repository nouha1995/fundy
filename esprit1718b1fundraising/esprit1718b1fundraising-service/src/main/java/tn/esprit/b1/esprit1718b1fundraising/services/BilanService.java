package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Stateful;

import tn.esprit.b1.esprit1718b1fundraising.entities.Bilan;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class BilanService extends GenericDAO<Bilan> implements BilanServiceRemote,BilanServiceLocal{

	public BilanService() {
		super(Bilan.class);
	}

	@Override
	public void save(Bilan entity) {
super.save(entity);		
	}

	@Override
	public void delete(Bilan entity) {
super.delete(entity);		
	}

	@Override
	public Bilan update(Bilan entity) {
		return super.update(entity);
	}

	@Override
	public Bilan find(int entityID) {

		return super.find(entityID);
	}

	@Override
	public List<Bilan> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
