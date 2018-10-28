package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXListView;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javassist.bytecode.analysis.ControlFlow.Node;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.entities.Transaction;
import tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class StatistiqueProjectsController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXListView<Transaction> listTransaction;
    @FXML
    private Label Descreption;

    @FXML
    private Label Cathegory;

    @FXML
    private Label title;

    @FXML
    private ImageView image;

    @FXML
    private JFXListView<BusinessProject> listprojetInvestor;
    @FXML
    private BorderPane calendar;

    @FXML
    private Label ammount;

    @FXML
    private Label financement;
    @FXML
    private Label localisation;

    @FXML
    private ProgressBar progress;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	
		try { 
		   	 String jndiName6="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/TransactionService!tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote";

			Context ctx6=new InitialContext();
	    	 TransactinServiceRemote proxy6 =(TransactinServiceRemote) ctx6.lookup(jndiName6);
	         LocalDate d2 = LocalDate.now();

			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
	    	   Date d5 = sdf.parse("2018/03/29");
	    	 List<Object[]> abc = new ArrayList<>();
	    	 Date date = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());

	 		abc =    proxy6.FindByDate(d5);
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
	 		BusinessProject business=cp.getBus_Project();
	 		
	 		System.out.println(cp.getId());
		    System.out.println(max);
		    title.setText(business.getTitle());
		    Descreption.setText(business.getDescription());
		    financement.setText(Float.toString(business.getFinancement()));
		    localisation.setText(business.getLocalisation());
		    File file = new File("C:/wamp64/www/Photos/" + business.getPhoto());
            Image img = new Image(file.toURI().toString());
            image.setImage(img);
            Cathegory.setText(business.getCategorie());
			
            Double a=  Double.parseDouble(Float.toString(business.getCompteprojet().getSom()));
            Double k=Double.parseDouble(Float.toString(business.getFinancement()));
            progress.setProgress(a/k);
            Collection<Transaction> list ;
           	list=proxy6.BrowseTransaction(LoginController.userLogedIn.getId());
           	float am=proxy6.getCompteInvestor(LoginController.userLogedIn.getId()).getSom();
           	ammount.setText(Float.toString(am));
        	for(Transaction b:list){
        		
        		listTransaction.getItems().add(b);
        		
        	}
        	listTransaction.setCellFactory(new Callback<ListView<Transaction>, ListCell<Transaction>>() {
    			
    			@Override
    			public ListCell<Transaction> call(ListView<Transaction> param) {
    				// TODO Auto-generated method stub
    				return new ListCell<Transaction>(){
    					@Override
    					 protected void updateItem(Transaction item, boolean bln){
    						 
    						super.updateItem(item, bln);
    		                   
    		                   if (item != null) {

                                      Label l=new Label(Float.toString(item.getSomversement()));
    	                               l.setFont(Font.font(null, FontWeight.BOLD, 16));
    	                               VBox sbox=new VBox(l);
    	                               DatePicker p;
    	                               Instant instant = item.getDateversement().toInstant();
    	                               ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    	                               LocalDate date = zdt.toLocalDate();

    		                            VBox vBox = new VBox(new DatePicker(date));
    		                            System.out.println((0.3));
    		                            HBox hBox = new HBox(sbox,vBox);
    		                             
    		                            hBox.setSpacing(15);
    		                            setGraphic(hBox);
    		                         
    		                        } else{
    		                            setText(null);
    		                            setGraphic(null);}	 
    						 
    						 
    						 
    						 
    					 }
    					
    					
    					
    				};
    			}
    		} );
          	List<Transaction> h= proxy6.BrowseTransaction(LoginController.userLogedIn.getId());
           	for(Transaction i:h){
           		System.out.println(i.getCompteprojet().getBus_Project());
           		listprojetInvestor.getItems().add(i.getCompteprojet().getBus_Project());
           	}
           	listprojetInvestor.setCellFactory(new Callback<ListView<BusinessProject>, ListCell<BusinessProject>>() {
    			
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
    									
                            // Double a=  Double.parseDouble(Float.toString(item.getCompteprojet().getSom()));
                            
                           //  Double b=Double.parseDouble(Float.toString(item.getFinancement()));
    	                               Label l=new Label(item.getTitle());
    	                               l.setFont(Font.font(null, FontWeight.BOLD, 14));
    	                               VBox sbox=new VBox(l);
    		                            VBox vBox = new VBox(new Label(item.getDescription()));
    		                            System.out.println((0.3));
    		                            VBox tBox=new VBox(new ProgressBar((0.3)));
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
           
        
        
		} catch (NamingException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		   DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
               javafx.scene.Node popupContent = datePickerSkin.getPopupContent();
           calendar.setCenter(popupContent);
     
       

    }    
    
}
