package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import tn.esprit.b1.esprit1718b1fundraising.entities.Admin;
import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Organization;
import tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;

public class CharityTest {

	public static void main(String[] args) throws ParseException, NamingException {
		String jndiname="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote";
		Context ctx = new InitialContext();
		UtilisateurServiceRemote service=(UtilisateurServiceRemote) ctx.lookup(jndiname);
		
		String jndiname2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CharityService!tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote";
	 CharityServiceRemote chService=(CharityServiceRemote) ctx.lookup(jndiname2);
	 
	 String jndiname3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/OrganizationService!tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote";
	 OrganizationServiceRemote orgService=(OrganizationServiceRemote) ctx.lookup(jndiname3);
	 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		  Date d1 = sdf.parse("2017/02/29");
		  Date d2 = sdf.parse("2018/12/9");
		  Date d3 = sdf.parse("2019/01/29");
		  Date d4 = sdf.parse("2011/01/29");
		Founder f=new Founder("narjes","asfour", "narjes1995","64739502", "narjesasfour@hotmail.fr", d1);
		Founder f1=new Founder("nouha","asfour", "founder","founder", "nouhaasfour@hotmail.fr", d1);
		Investor i=new Investor("achraf","achraf","investor","investor","test@test.com",d1);
		Admin a=new Admin("admin","admin","admin","admin","test@test.com",d1);
		//CharityProject c=new CharityProject( "child's labour", d1, d2, "awesome parties to fund those child",f);
		CharityProject c1=new CharityProject( "orphans", d1, d3, "awesome parties to fund those orphans");
		CharityProject c3=new CharityProject();

		System.out.println("ok");
		//f.addCharityProject(c);
		//f.addCharityProject(c1);
		//f.addCharityProject(c2);
		//service.save(f);
		//service.save(f1);
		//service.save(i);
		//service.save(a);
        //chService.save(c3);
        //chService.save(c2);
		System.out.println("ok");
		int UserId=service.saveNouha(f1);
		//int chProjectId=chService.saveNouha(c2);
		//int orgId=orgService.saveNouha(org);
		//service.affecterChProjectAUserNouha(chProjectId, UserId);
		//c3.setDescription("coucou");
	}

}
