package tn.esprit.b1.esprit1718b1fundraising.services;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessPlan;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface BuisnessPlanServiceRemote extends IGenericDAO<BusinessPlan>{
BusinessPlan findByProject(EntityManager em, BusinessProject p);
public int AddBuiPlan(BusinessPlan b);
public void Affecter(int a,int b);
}
