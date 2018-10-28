package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Entity;

import tn.esprit.b1.esprit1718b1fundraising.entities.Interests;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;

@Remote
public interface InterestServiceRemote {
	public void Addinterst(int a,Interests Enti);
	public Interests VerifInvInterest(int a,String str);
	public boolean InteretHere( String str);
	public void AffecterInvestor(int a,String b);
	public Investor findbyID(int a);
	public List<Interests>getAll();

}
