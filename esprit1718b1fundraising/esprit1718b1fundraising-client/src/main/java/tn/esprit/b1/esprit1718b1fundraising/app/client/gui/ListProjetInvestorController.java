package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.Attributes.Name;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteInvestor;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.entities.Interests;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Transaction;
import tn.esprit.b1.esprit1718b1fundraising.entities.TransactionPK;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CompteInvestorServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.InterestServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.InvestorServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ListProjetInvestorController implements Initializable {
	

    @FXML
    private ToggleGroup cash;
    @FXML
    private JFXListView<BusinessProject> listviewProjet;
    @FXML
    private AnchorPane recordpayment;

    @FXML
    private AnchorPane affichpane;

    @FXML
    private AnchorPane Bilanpane;
    @FXML
    private Text titre;

    @FXML
    private Text cathegory;

    @FXML
    private JFXDatePicker lastdate;

    @FXML
    private Text Adress;
    @FXML
    private Text fixe;

    @FXML
    private Text variable;

    @FXML
    private JFXTextField marge;

    @FXML
    private JFXTextField resultat;

    @FXML
    private JFXTextField seuil;
    @FXML
    private JFXTextField PointMort;

    @FXML
    private JFXTextField pourcentagefixe;

    @FXML
    private JFXTextField pourcentagevar;

    @FXML
    private Text financement;

    @FXML
    private Text devise;
    
    @FXML
    private JFXButton generate;

    @FXML
    private JFXComboBox<String> currency;
    @FXML
    private PieChart piechartbilan;

    @FXML
    private VBox contrat;

    @FXML
    private JFXTextField invammount;

    @FXML
    private Label Project;

    @FXML
    private Label Namelastname;

    @FXML
    private Label Montant;

    @FXML
    private Label Currency;

    @FXML
    private JFXComboBox<String> equity;
    @FXML
    private TextField cat;
    @FXML
    private VBox hboxxx;

    @FXML
    private JFXTextField name1;

    @FXML
    private JFXTextField name2;
	float ammount;
    BusinessProject project;
    float montantverse;
	boolean msg;
	List<String> lista=new ArrayList<>();
    private ObservableList data;

	List<BusinessProject> finallist=new ArrayList<>();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	currency.getItems().addAll("TND","EURO","DOLLAR");
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
	
			Context ctx;
			Collection<BusinessProject> list ;


			try {
				ctx = new InitialContext();
				BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);
				list=proxy2.FindValidProjects();
				System.out.println(list);
				for(BusinessProject b:list){
					//  Label l = new Label(b.getCategorie());
					 // System.out.println(b.getCategorie());
		              //  l.setFont(Font.font(null, FontWeight.BOLD, 14));
					listviewProjet.getItems().add(b);
				
	    }
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listviewProjet.setCellFactory(new Callback<ListView<BusinessProject>, ListCell<BusinessProject>>() {
				
				@Override
				public ListCell<BusinessProject> call(ListView<BusinessProject> param) {
					// TODO Auto-generated method stub
					return new ListCell<BusinessProject>(){
						@Override
						 protected void updateItem(BusinessProject item, boolean bln){
							 
							super.updateItem(item, bln);
			                   
			                   if (item != null) {

			                	   File file = new File("C:/wamp64/www/Photos/" + item.getPhoto());
			                       Image img = new Image(file.toURI().toString());
			                     //  ImageView im=new ImageView();
			                      // im.setImage(img);
                               
			                       VBox Box = new VBox(new ImageView(img)); 
											System.out.println();
										
                              Double a=  Double.parseDouble(Float.toString(item.getCompteprojet().getSom()));
                              Double b=Double.parseDouble(Float.toString(item.getFinancement()));
		                               Label l=new Label(item.getTitle());
		                               l.setFont(Font.font(null, FontWeight.BOLD, 14));
		                               VBox sbox=new VBox(l);
			                            VBox vBox = new VBox(new Label(item.getDescription()));
			                            //System.out.println((a/b));
			                            VBox tBox=new VBox(new ProgressBar((a/b)));
			                            HBox hBox = new HBox(Box,sbox,vBox,tBox);
			                             
			                            hBox.setSpacing(10);
			                            setGraphic(hBox);
			                         
			                        } else{
			                            setText(null);
			                            setGraphic(null);}	 
							 
							 
							 
							 
						 }
						
						
						
					};
				}
			} );
			String jndiName3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/InterestService!tn.esprit.b1.esprit1718b1fundraising.services.InterestServiceRemote";

			Context ctx21;
			try {
				ctx21 = new InitialContext();
				InterestServiceRemote proxy3 = (InterestServiceRemote) ctx21.lookup(jndiName3);
			
			  	List<Interests> inte=proxy3.getAll();
			  	for(Interests i:inte){
			  		for(Investor k:i.getInvestors()){
			  			if(k.getId()==LoginController.userLogedIn.getId()){
			  				lista.add(i.getInterest());
			  			}
			  		}
			  	}
			  	for(String b:lista){
			  		System.out.println(b);
			  	}
			  	
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				Collection<BusinessProject> liste ;
	    	String jndiName5="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
			Context ctx1;

			BusinessProjectRemote proxy2;
			try {
				ctx1 = new InitialContext();

				proxy2 = (BusinessProjectRemote) ctx1.lookup(jndiName5);
				list=proxy2.FindValidProjects();
				for(BusinessProject b:list){
					for(String l:lista){
						if(b.getCategorie().equals(l)){
							finallist.add(b);
						}
					}
		             //   hboxxx.getChildren().add(img);
				
	    }
				for(BusinessProject c:finallist){
					  File file = new File("C:/wamp64/www/Photos/" + c.getPhoto());
                    Image img = new Image(file.toURI().toString());
                  System.out.println(c.getTitle());
					  Label l = new Label(c.getTitle());
		                l.setFont(Font.font(null, FontWeight.BOLD, 16));
		                hboxxx.getChildren().add(new ImageView(img));

		                hboxxx.getChildren().add(new Text(l.getText()));}


			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	

	    }
    @FXML
    void generate(ActionEvent event) {
      project=listviewProjet.getSelectionModel().getSelectedItem();
      float MCV=project.getCA()-project.getCh_var();
      float result=MCV-project.getCh_fixe();
      float pMCV=(MCV/project.getCA())*100;
      float pchvar=(project.getCh_var()/project.getCA())*100;
      float pchfixe=(project.getCh_fixe()/project.getCA())*100;
      float SR = project.getCh_fixe()/(pMCV/100);
      float PointMor=(SR/project.getCA())*12;
     String mois="";
      int j = Math.round(PointMor);
      if(j==1){
    	  mois="Jannuary";  
      }
      if(j==2){mois="February";}
      if(j==3){mois="March";}
      if(j==4){mois="April";}
      if(j==5){mois="May";}
      if(j==6){mois="June";}
      if(j==7){mois="July";}
      if(j==8){mois="August";}
      if(j==9){mois="September";}
      if(j==10){mois="October";}
      if(j==11){mois="November";}
      if(j==12){mois="December";}
      cathegory.setText(project.getCategorie());
      titre.setText(project.getTitle());
      devise.setText("euro");
     Bilanpane.setVisible(true); 
     affichpane.setVisible(false);
     Adress.setText(project.getLocalisation());
     fixe.setText( Float.toString(project.getCh_fixe()));
     variable.setText( Float.toString(project.getCh_var()));
     marge.setText( Float.toString(MCV));
     resultat.setText( Float.toString(result));
     seuil.setText( Float.toString(SR));
     PointMort.setText( mois);
     Instant instant = project.getDateFin().toInstant();
     ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
     LocalDate date = zdt.toLocalDate();
     lastdate.setValue(date);

     pourcentagefixe.setText(Float.toString(pchfixe));
     pourcentagevar.setText(Float.toString(pchvar));
     financement.setText(Float.toString(project.getFinancement()));
     data = FXCollections.observableArrayList();

     data.add(new PieChart.Data("fixed charges",project.getCh_fixe()));
     data.add(new PieChart.Data("Variable charges",project.getCh_var()));
     data.add(new PieChart.Data("Ammount Needed",project.getFinancement()));
piechartbilan.getData().addAll(data);

     
    }

    @FXML
    void Investigate(ActionEvent event) {
    	recordpayment.setVisible(true);
    	Bilanpane.setVisible(false);
    	equity.getItems().addAll(project.getEquity1(),project.getEquity2(),project.getEquity3());

    }

    @FXML
    void backPojects(ActionEvent event) {
    	affichpane.setVisible(true);
        Bilanpane.setVisible(false); 
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
    	
		Context ctx;

		try {
			ctx = new InitialContext();
			BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);
			Collection<BusinessProject> list ;
			list=proxy2.FindValidProjects();
			System.out.println(list);
			for(BusinessProject b:list){
	
				listviewProjet.getItems().add(b);
			
    }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listviewProjet.setCellFactory(new Callback<ListView<BusinessProject>, ListCell<BusinessProject>>() {
			
			@Override
			public ListCell<BusinessProject> call(ListView<BusinessProject> param) {
				// TODO Auto-generated method stub
				return new ListCell<BusinessProject>(){
					@Override
					 protected void updateItem(BusinessProject item, boolean bln){
						 
						super.updateItem(item, bln);
		                   
		                   if (item != null) {
		                             
		                	   File file = new File("C:/wamp64/www/Photos/" + item.getPhoto());
		                       Image img = new Image(file.toURI().toString());
		                     //  ImageView im=new ImageView();
		                      // im.setImage(img);
                           
		                       VBox Box = new VBox(new ImageView(img)); 
										System.out.println();
									
                          Double a=  Double.parseDouble(Float.toString(item.getCompteprojet().getSom()));
                          Double b=Double.parseDouble(Float.toString(item.getFinancement()));
	                               Label l=new Label(item.getTitle());
	                               l.setFont(Font.font(null, FontWeight.BOLD, 14));
	                               VBox sbox=new VBox(l);
		                            VBox vBox = new VBox(new Label(item.getDescription()));
		                            //System.out.println((a/b));
		                            VBox tBox=new VBox(new ProgressBar((a/b)));
		                            HBox hBox = new HBox(Box,sbox,vBox,tBox);
		                             
		                            hBox.setSpacing(10);
		                            setGraphic(hBox);
		                         
		                         
		                        } else{
		                            setText(null);
		                            setGraphic(null);}	 
						 
						 
						 
						 
					 }
					
					
					
				};
			}
		} );
		String jndiName3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/InterestService!tn.esprit.b1.esprit1718b1fundraising.services.InterestServiceRemote";

		Context ctx21;
		try {
			ctx21 = new InitialContext();
			InterestServiceRemote proxy3 = (InterestServiceRemote) ctx21.lookup(jndiName3);
		
		  	List<Interests> inte=proxy3.getAll();
		  	for(Interests i:inte){
		  		for(Investor k:i.getInvestors()){
		  			if(k.getId()==LoginController.userLogedIn.getId()){
		  				lista.add(i.getInterest());
		  			}
		  		}
		  	}
		  	for(String b:lista){
		  		System.out.println(b);
		  	}
		  	
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			Collection<BusinessProject> liste ;
    	String jndiName5="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
		Context ctx1;
		Collection<BusinessProject> list;
		BusinessProjectRemote proxy2;
		try {
			ctx1 = new InitialContext();

			proxy2 = (BusinessProjectRemote) ctx1.lookup(jndiName5);
			list=proxy2.FindValidProjects();
			for(BusinessProject b:list){
				for(String l:lista){
					if(b.getCategorie().equals(l)){
						finallist.add(b);
					}
				}
	             //   hboxxx.getChildren().add(img);
			
    }
			for(BusinessProject c:finallist){
				  File file = new File("C:/wamp64/www/Photos/" + c.getPhoto());
                Image img = new Image(file.toURI().toString());
              System.out.println(c.getTitle());
				  Label l = new Label(c.getTitle());
	                l.setFont(Font.font(null, FontWeight.BOLD, 16));
	                hboxxx.getChildren().add(new ImageView(img));

	                hboxxx.getChildren().add(new Text(l.getText()));}


		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void payementaction(ActionEvent event) throws NamingException, ParseException {
   
    	//esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote
    	Investor investor;
    	Context ctx5= new InitialContext();
    	String jndiName6="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/TransactionService!tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote";
     	 TransactinServiceRemote proxy6 =(TransactinServiceRemote) ctx5.lookup(jndiName6);
     	//esprit1718b1fundraising-ear/esprit1718b1fundraising-service/InvestorService!tn.esprit.b1.esprit1718b1fundraising.services.InvestorServiceRemote
     	 String jndiName7="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/InvestorService!tn.esprit.b1.esprit1718b1fundraising.services.InvestorServiceRemote";
     	InvestorServiceRemote proxy7 =(InvestorServiceRemote) ctx5.lookup(jndiName7);
     	investor=proxy7.find(LoginController.userLogedIn.getId());
    	String jndiName8="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CompteInvestorService!tn.esprit.b1.esprit1718b1fundraising.services.CompteInvestorServiceRemote";
    	CompteInvestorServiceRemote proxy8 =(CompteInvestorServiceRemote) ctx5.lookup(jndiName8);

     	float comptesom=investor.getCompteinvestor().getSom();
     	System.out.println(comptesom);
    	String s=equity.getSelectionModel().getSelectedItem();
    String ns="";
    	char[] cs = s.toCharArray();

       	for ( int i = 0; i< cs.length;i++){

        	if (  Character.isDigit(cs[i])) {
        		ns=ns+cs[i];
    };
    	}
       	int pourcentage= Integer.parseInt(ns);
       	String devise=project.getDevise();
       
       	switch(devise){
       	case "TND": 
    	if(currency.getSelectionModel().getSelectedItem().equals("TND")){ammount=project.getFinancement()*(pourcentage/100f);
    	System.out.println(project.getFinancement());
          }
    	if(currency.getSelectionModel().getSelectedItem().equals("DOLLAR")){ammount=(project.getFinancement()*(pourcentage/100f))/0.41f; System.out.println(ammount);}
    	if(currency.getSelectionModel().getSelectedItem().equals("EURO")){ammount=(project.getFinancement()*(pourcentage/100f))/0.33f; System.out.println(ammount);}
    	break;
       	case "DOLLAR":
       		if(currency.getSelectionModel().getSelectedItem().equals("TND")){ammount=(project.getFinancement()*(pourcentage/100f))*0.41f; System.out.println(ammount);}
        	if(currency.getSelectionModel().getSelectedItem().equals("DOLLAR")){ammount=(project.getFinancement()*(pourcentage/100f)); System.out.println(ammount);}
        	if(currency.getSelectionModel().getSelectedItem().equals("EURO")){ammount=(project.getFinancement()*(pourcentage/100f))*1.24f; System.out.println(ammount);}
        	break;
       	case "EURO":
       		if(currency.getSelectionModel().getSelectedItem().equals("TND")){ammount=(project.getFinancement()*(pourcentage/100f))*0.33f; System.out.println(ammount);}
        	if(currency.getSelectionModel().getSelectedItem().equals("DOLLAR")){ammount=(project.getFinancement()*(pourcentage/100f))*0.8f; System.out.println(ammount);}
        	if(currency.getSelectionModel().getSelectedItem().equals("EURO")){ammount=(project.getFinancement()*(pourcentage/100f)); System.out.println(ammount);}
        	break;
       	} 
     	if(!proxy6.verifsomme(comptesom, ammount)){System.out.println("somme insuffisante");
        Alert warge = new Alert(AlertType.WARNING);
        warge.setTitle("Warning Dialog");
        warge.setHeaderText("Look, a Warning Dialog");
        warge.setContentText("Not enough ammount");

        warge.showAndWait();}

       	String str=Float.toString(ammount);
       	System.out.println(str);
       	invammount.setText(str);
     	String jndiName5="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CompteProjetService!tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote";
    	CompteProjetServiceRemote proxy5 =(CompteProjetServiceRemote) ctx5.lookup(jndiName5);
    	
       	float somme=project.getCompteprojet().getSom();
       	float newsom=somme+ammount;
       	System.out.println("acuel"+somme);

       	System.out.println("if"+newsom);
       	
       	if(proxy6.verifsomme(project.getFinancement(),newsom)){
       	CompteProjet p=project.getCompteprojet();
       	p.setSom(newsom);
      	proxy5.update(p);
		contrat.setVisible(true);
		Currency.setText("Currency :"+devise);
		Project.setText("Project Title :"+project.getTitle());
		Namelastname.setText("Name :"+name1.getText()+""+name2.getText());
		Montant.setText("Ammount To pay"+String.valueOf(ammount ));
		
		LocalDateTime d=LocalDateTime.now();
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d.toString());
int id=project.getCompteprojet().getId();
TransactionPK pk=new TransactionPK(LoginController.userLogedIn.getId(),id);
	 Transaction tr =new Transaction(pk, date, ammount);
	 proxy6.save(tr);
	float restcompteinvestor= proxy7.retraitCompteInvestor(comptesom, ammount);
	
CompteInvestor inv= investor.getCompteinvestor();
inv.setSom(restcompteinvestor);
proxy8.update(inv);

	 }
       	else{
           	CompteProjet p=project.getCompteprojet();
           	

       		float difference=somme+ammount-project.getFinancement();
       		float newammount=ammount-difference;
       		System.out.println(difference);
       		float restcompteinvestor= proxy7.retraitCompteInvestor(comptesom, newammount);
       		CompteInvestor inv= investor.getCompteinvestor();
       		inv.setSom(restcompteinvestor);
       		proxy8.update(inv);
       		//System.out.println("else"+somme+newsome);
       		p.setSom(project.getFinancement());
       		proxy5.update(p);
       		contrat.setVisible(true);
       		Currency.setText("Currency :"+devise);
    		Project.setText("Project Title :"+project.getTitle());
    		Namelastname.setText("Name :"+name1.getText()+""+name2.getText());
    		Montant.setText("Ammount To Pay"+String.valueOf(ammount ));
    		LocalDateTime d=LocalDateTime.now();
    		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d.toString());
    int id=project.getCompteprojet().getId();
    TransactionPK pk=new TransactionPK(LoginController.userLogedIn.getId(),id);
    	 Transaction tr =new Transaction(pk, date, difference);
    	 System.out.println(tr);
    	 proxy6.save(tr);
       	}

       	
    }

    @FXML
    void SearchBytype(ActionEvent event) throws NamingException {
    	listviewProjet.getItems().clear();
//	java:jboss/exported/
         
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
    	String jndiName3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/InterestService!tn.esprit.b1.esprit1718b1fundraising.services.InterestServiceRemote";

		Context ctx;

		try {
			ctx = new InitialContext();
			BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);
			Collection<BusinessProject> list ;
			list=proxy2.SearchProjectByCath(cat.getText());
			System.out.println(list);
			for(BusinessProject b:list){
				//  Label l = new Label(b.getCategorie());
				 // System.out.println(b.getCategorie());
	              //  l.setFont(Font.font(null, FontWeight.BOLD, 14));
				listviewProjet.getItems().add(b);
			
    }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listviewProjet.setCellFactory(new Callback<ListView<BusinessProject>, ListCell<BusinessProject>>() {
			
			@Override
			public ListCell<BusinessProject> call(ListView<BusinessProject> param) {
				// TODO Auto-generated method stub
				return new ListCell<BusinessProject>(){
					@Override
					 protected void updateItem(BusinessProject item, boolean bln){
						 
						super.updateItem(item, bln);
		                   
		                   if (item != null) {

		                	   File file = new File("C:/wamp64/www/Photos/" + item.getPhoto());
		                       Image img = new Image(file.toURI().toString());
		                     //  ImageView im=new ImageView();
		                      // im.setImage(img);
                           
		                       VBox Box = new VBox(new ImageView(img)); 
										System.out.println();
									
                         Double a=  Double.parseDouble(Float.toString(item.getCompteprojet().getSom()));
                        
                        Double b=Double.parseDouble(Float.toString(item.getFinancement()));
	                               Label l=new Label(item.getTitle());
	                               l.setFont(Font.font(null, FontWeight.BOLD, 14));
	                               VBox sbox=new VBox(l);
		                            VBox vBox = new VBox(new Label(item.getDescription()));
		                            System.out.println((0.3));
		                            VBox tBox=new VBox(new ProgressBar((a/b)));
		                            HBox hBox = new HBox(Box,sbox,vBox,tBox);
		                             
		                            hBox.setSpacing(10);
		                            setGraphic(hBox);
		                         
		                        } else{
		                            setText(null);
		                            setGraphic(null);}	 
						 
						 
						 
						 
					 }
					
					
					
				};
			}
		} );
		ctx  = new InitialContext();

		InterestServiceRemote proxy3 = (InterestServiceRemote) ctx.lookup(jndiName3);
		Interests inter=new Interests(cat.getText());
	
		if(proxy3.InteretHere(cat.getText())==true){
			Interests c=proxy3.VerifInvInterest(LoginController.userLogedIn.getId(), cat.getText());
			System.out.println(c);
			System.out.println("helloooo");
			List<Investor> invlist=c.getInvestors();
			if(invlist.isEmpty()==true){
				proxy3.AffecterInvestor(LoginController.userLogedIn.getId(), cat.getText());
				System.out.println("belehi affectih");
			}
		
			else{
			for(Investor b:invlist){
				System.out.println(b.getId());
				if(b.getId()==LoginController.userLogedIn.getId())
				{System.out.println("deja affecte");
			 msg =true;
			 break;
				}
			
				
				}
			if(!msg){
				proxy3.AffecterInvestor(LoginController.userLogedIn.getId(), cat.getText());
			System.out.println("affecte investor");}
			}
		
    }
		else{		proxy3.Addinterst(LoginController.userLogedIn.getId(), inter);}

    
}
}