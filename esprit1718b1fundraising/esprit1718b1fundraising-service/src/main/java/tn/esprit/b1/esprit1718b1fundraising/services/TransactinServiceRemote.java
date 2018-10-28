package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteInvestor;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.entities.Transaction;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface TransactinServiceRemote extends IGenericDAO<Transaction> {
public Boolean verifsomme(float a ,float b);
public BusinessProject MaxversedProject(List<Transaction> liste);
public List<Object[]> FindByDate(Date date);
public List<BusinessProject> FindProjetparInvestor(int idInvestor);
public List<Transaction> BrowseTransaction(int id);
public List <Date> getlistDate();
public Long getfoundersNumber();
public Long getInvestorsNumber();
public Long getProjectNumber();
public Long getUserNumber();
public CompteInvestor getCompteInvestor(int a);

}
