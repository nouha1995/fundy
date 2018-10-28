package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Organization;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
public class OrganizationService extends GenericDAO<Organization> implements OrganizationServiceRemote,OrganizationServiceLocal{
	@PersistenceContext
	private EntityManager entityManager;

	
	public OrganizationService() {
		super(Organization.class);
		
	}
	
	@Override
	public void delete(Organization entity) {
		
		super.delete(entity);
	}
	
	@Override
	public Organization find(int entityID) {
		
		return super.find(entityID);
	}
	@Override
	public List<Organization> findAll() {
		
		return super.findAll();
	}
	@Override
	public void save(Organization entity) {
		
		super.save(entity);
	}
	@Override
	public Organization update(Organization entity) {
		
		return super.update(entity);
	}
	
	@Override
	protected Organization findOneResult(String namedQuery, Map<String, Object> parameters) {
		
		return super.findOneResult(namedQuery, parameters);
	}
	@Override
	public int saveNouha(Organization organization) {
		entityManager.persist(organization);
		return organization.getId();
	}

	@Override
	public void affecterChProjectAOrganizationNouha(int chProjectId, int orgId) {
		
		Organization orgEntity=entityManager.find(Organization.class, orgId);
		CharityProject chProjectEntity=entityManager.find(CharityProject.class, chProjectId);
		
		chProjectEntity.setOrganization(orgEntity);
		
	}

	@Override
	public void updateOrganizationNouha(String name, String country, Date foundingYear, String program,
			String programMaterials,int orgId) {
		Organization org=entityManager.find(Organization.class, orgId);
		if(name != null)
		org.setName(name);
		if(country != null)
		org.setCountry(country);
		if(foundingYear != null)
		org.setFoundingYear(foundingYear);
		if(program != null)
		org.setProgram(program);
		if(programMaterials != null)
		org.setProgramMaterials(programMaterials);
		
	}

	@Override
	public void updateLogoOrgNouha(String logo, int orgId) {
		Organization org=entityManager.find(Organization.class, orgId);
		org.setLogo(logo);
		
	}
	
	@Override
	public void deleteOrgNouha(int orgId) {

		Organization org=entityManager.find(Organization.class, orgId);
		entityManager.remove(org);
		
	}
	

}
