package tn.esprit.fundraising1718fund;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.ListAttribute;

import org.hibernate.Hibernate;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import tn.esprit.b1.esprit1718b1fundraising.entities.Bilan;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteFounder;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Interests;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Transaction;
import tn.esprit.b1.esprit1718b1fundraising.entities.TransactionPK;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.BilanServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.BuisnessPlanServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CompteFounderServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.FounderServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.InterestServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;

public class Test {
	@PersistenceContext
	static
	EntityManager em ;

	public static void main(String[] args) throws NamingException, ParseException{
		/*String jndiName="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BilanService!tn.esprit.b1.esprit1718b1fundraising.services.BilanServiceRemote";
		Context ctx = new InitialContext();
		BilanServiceRemote proxy = (BilanServiceRemote) ctx.lookup(jndiName);
		//Bilan bilan=new Bilan(2f,2f,2f);
		//proxy.save(bilan);
		
		String jndiName1="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/FounderService!tn.esprit.b1.esprit1718b1fundraising.services.FounderServiceRemote";
		Context ctx1 = new InitialContext();
		FounderServiceRemote proxy1 = (FounderServiceRemote) ctx.lookup(jndiName1);
		//Bilan bilan=new Bilan(2f,2f,2f);
		//proxy.save(bilan);
		Founder f=new Founder("salut","lastname", "login", "gg", "hello");
		//proxy1.findAll();
		//proxy1.save(f);
	//	System.out.println(proxy1.find(1));

		String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
		Context ctx2 = new InitialContext();
		BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);
	BusinessProject  business =new BusinessProject("title","descreption", "categorie"," localisation", 3f);
	Collection<BusinessProject> list ;
	//list=proxy2.FindValidProjects();
	
	//System.out.println(list);
	//BusinessProject p=proxy2.find(1);
	//proxy2.findAll();
	//proxy2.save(business);
	//proxy2.AffecterUserProject(1, 1);
	//exported/
	String jndiName3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CompteFounderService!tn.esprit.b1.esprit1718b1fundraising.services.CompteFounderServiceRemote";
	Context ctx3 = new InitialContext();
	CompteFounderServiceRemote proxy3 =(CompteFounderServiceRemote) ctx.lookup(jndiName3);
   //CompteProjet projet= new CompteProjet(2f);
    CompteFounder cf=new CompteFounder(3f);
    //cf.setFounder(f);
  //  proxy3.findAll();

    //proxy3.save(projet);
    //proxy3.save(cf);
   // proxy3.AffecterFounderCompte(1,2);


    String jndiName4="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BuisnessPlanService!tn.esprit.b1.esprit1718b1fundraising.services.BuisnessPlanServiceRemote";
	Context ctx4 = new InitialContext();
	BuisnessPlanServiceRemote proxy4 =(BuisnessPlanServiceRemote) ctx3.lookup(jndiName4);
	//proxy4.findAll();
	//System.out.println(proxy4.findByProject(em, p));
	System.out.println("hello");
	//java:jboss/exported/esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CompteProjetService!tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote
	 String jndiName5="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CompteProjetService!tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote";
		Context ctx5= new InitialContext();
		CompteProjetServiceRemote proxy5 =(CompteProjetServiceRemote) ctx3.lookup(jndiName5);
		CompteProjet Compteproject = new CompteProjet(784f);
		//proxy5.save(Compteproject);
		String insaf ="insaf125";
		String ns="";
    	char[] cs = insaf.toCharArray();*/
    	//	java:jboss/exported/esprit1718b1fundraising-ear/esprit1718b1fundraising-service/TransactionService!tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote
   	 String jndiName6="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/TransactionService!tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote";
   	Context ctx6= new InitialContext();
   	 TransactinServiceRemote proxy6 =(TransactinServiceRemote) ctx6.lookup(jndiName6);
   /*	 TransactionPK pk=new TransactionPK(1,8);
   	
   	// proxy6.save(tr);
   	 System.out.println(proxy6.verifsomme(78,87));
   float ammount=(128888*(75f/100)); 
   System.out.println(ammount);*/
   	LocalDateTime d=LocalDateTime.now();
	java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d.toString());

   	 TransactionPK pk=new TransactionPK();
   	 Transaction tr =new Transaction(pk, date, 8f);
   	// System.out.println(tr.ge);
   	List<Transaction> h= proxy6.BrowseTransaction(7);
   	Long k=proxy6.getfoundersNumber();
 System.out.println(k);
   

   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
   Date d2 = sdf.parse("2018/03/29");
  //System.out.println(proxy6.FindByDate(d2));
  /*List<Object[]> abc = new ArrayList<>();
		abc =   proxy6.FindByDate(d2);
		double max = 0 ; 
		CompteProjet cp = new CompteProjet();
		for(Object[] o : abc){
    double sum = (double) o[0] ;
    CompteProjet s=(CompteProjet)o[1];
    if(sum>max){ 
    	max = sum ;
    	cp= s ; 
    	}
    }
		 System.out.println(cp.getId());
		    System.out.println(max);
	*/
	/*Collection<Transaction> list ;
	list=proxy6.BrowseTransaction(7);
	for(Transaction b:list){
		
		System.out.println(b);
		
	}
		
*/
	String jndiName21="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/InterestService!tn.esprit.b1.esprit1718b1fundraising.services.InterestServiceRemote";
  	Context ctx21= new InitialContext();
  	InterestServiceRemote proxy8 =(InterestServiceRemote) ctx21.lookup(jndiName21);
 	 //System.out.println(proxy8.VerifInvInterest(7, "Art"));
/*Interests c=proxy8.VerifInvInterest(7, "Art");
System.out.println(c);
List<Investor> invlist=c.getInvestors();
System.out.println("ho");

for(Investor b:invlist){
	//System.out.println("hello");
	System.out.println(b.getFirstName());}
	
}*/
/*Investor cb=proxy8.findbyID(10);
System.out.println(cb.getLastName());
List<Interests> c=cb.getInterests();
System.out.println(c.isEmpty());
System.out.println("hello");
for(Interests i:c){System.out.println("som");
	System.out.println(i.getInterest());}*/
/*  	List<String> lista=new ArrayList<>();
  	List<Interests> inte=proxy8.getAll();
  	for(Interests i:inte){
  		for(Investor k:i.getInvestors()){
  			k.getProjects();
  			
  			if(k.getId()==9){
  				lista.add(i.getInterest());
  			}
  		}
  	}
  	for(String b:lista){
  		System.out.println(b);
  	}*/
  	
	}
	

	}
	


