package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.Date;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Organization;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface OrganizationServiceRemote extends IGenericDAO<Organization>{

	int saveNouha(Organization project);
	void affecterChProjectAOrganizationNouha(int chProjectId,int orgId);
	void updateOrganizationNouha(String name,String country,Date foundingYear,String program,String programMaterials,int orgId);
	void updateLogoOrgNouha(String logo,int orgId);
	public void deleteOrgNouha(int orgId);
}
