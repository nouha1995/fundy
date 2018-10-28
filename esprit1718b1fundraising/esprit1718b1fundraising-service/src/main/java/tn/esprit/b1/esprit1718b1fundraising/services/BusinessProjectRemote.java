package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface BusinessProjectRemote extends IGenericDAO<BusinessProject> {
	 public void AffecterUserProject(int iduser,int idprojet);
	 public float calculFondRoulement(float a,float b);
	 public List<BusinessProject> FindValidProjects();
	 public List<BusinessProject> SearchProjectByCath(String cat);
	 public int AddBP(BusinessProject b);
	 public List<Object[]> BrowseProjects();
	 public BusinessProject getfounder(int a);
	 public  void AffecterFounder(int a,int b);
}
