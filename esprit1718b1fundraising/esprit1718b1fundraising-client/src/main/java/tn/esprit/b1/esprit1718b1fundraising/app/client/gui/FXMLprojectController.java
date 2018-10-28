package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;


import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.experimental.categories.Categories;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote;
//import tray.notification.TrayNotification;

public class FXMLprojectController implements Initializable{

	  @FXML
	    private JFXTextField txtregNo;


	    @FXML
	    private JFXComboBox<String> location;

	    @FXML
	    private JFXComboBox<String> cathegorie;

	    @FXML
	    private JFXTextField txtregNo1;

	 

	    @FXML
	    private JFXDatePicker dat;

	    @FXML
	    private JFXButton btnSaveTraining;

	    @FXML
	    private JFXTextField txtCompany;

	    @FXML
	    private JFXTextField txtAddress;

	    @FXML
	    private JFXTextField txtContact;

	    @FXML
	    private JFXTextField txtAddress1;
	    @FXML
	    private ImageView imageprojet;

	    @FXML
	    private Label path;

	    @FXML
	    private Label war1;

	    @FXML
	    private Label war3;

	    @FXML
	    private Label war4;

	    @FXML
	    private Label war5;

	    @FXML
	    private Label war6;

	    @FXML
	    private Label war2;

	    @FXML
	    private JFXTextField eq2;

	    @FXML
	    private JFXTextField eq3;

	    @FXML
	    private JFXComboBox<String> currency;
	    public static int idProject;
	    boolean valide,warage;

	    
	 
   

    @FXML
    void saveProject(ActionEvent event) throws ParseException, NamingException {
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
		Context ctx2 = new InitialContext();
		BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx2.lookup(jndiName2);
		 String jndiName5="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CompteProjetService!tn.esprit.b1.esprit1718b1fundraising.services.CompteProjetServiceRemote";

		Context ctx5= new InitialContext();
		CompteProjetServiceRemote proxy5 =(CompteProjetServiceRemote) ctx5.lookup(jndiName5);
		CompteProjet Compteproject = new CompteProjet(0f);
			
    	  valide = true;
          warage = true;
    	String title=txtregNo.getText();
    	String funding=txtregNo1.getText();
    	String CA=txtCompany.getText();
    	String chargef=txtAddress.getText();
    	String chargvar=txtContact.getText();
    	String equity=txtAddress1.getText();
    	LocalDate date =dat.getValue();
    	String loc=location.getValue();
    	String cathegory=cathegorie.getValue();
    	
    
    	 if (txtregNo.getText().equals("")) {
             valide = false;
         }
    	 System.out.println(valide);
    	 if (txtregNo1.getText().equals("")) {
             valide = false;
         }
    	 if (txtCompany.getText().equals("")) {
             valide = false;
         }
    	 System.out.println(valide);
    	 if (txtAddress.getText().equals("")) {
             valide = false;
         }
    	 if (txtAddress1.getText().equals("")) {
             valide = false;
         }
    	 if (txtContact.getText().equals("")) {
             valide = false;
         }
         LocalDate d2 = LocalDate.now();
         long age = ChronoUnit.YEARS.between(date, d2);
         if (age < 1) {
             System.out.println("your age must be more than 18");
             warage = false;
         }
      
    	 
    	
		//BusinessProject bi=new BusinessProject(Title, dateFin, categorie, localisation, financement, equity1, equity2, equity3, devise, photo, cA, ch_var, ch_fixe, isValide)
		
		System.out.println(78);
	
	System.out.println(54);
	  if ((valide == true) &&  (warage == true)){
	    	java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());

			float finan=Float.parseFloat(funding);
	    	float chiffreaff=Float.parseFloat(CA);
	    	float chargefix=Float.parseFloat(chargef);
	    	float chargevar=Float.parseFloat(chargvar);
	    	float part=Float.parseFloat(equity);
			BusinessProject b=new BusinessProject(title,d,cathegory,loc,finan,equity,eq2.getText(),eq3.getText(),currency.getValue(),path.getText(),chiffreaff,chargevar, chargefix,false);

	idProject=proxy2.AddBP(b);
	proxy5.AddCompteProjet(idProject, Compteproject);
proxy2.AffecterFounder(idProject,LoginController.userLogedIn.getId());
	  }
	  else if ((valide == false)) {
           Alert empty = new Alert(AlertType.WARNING);
           empty.setTitle("Warning Dialog");
           empty.setHeaderText("Look, a Warning Dialog");
           empty.setContentText("fileds can't be empty ");

           empty.showAndWait();
       } else if ((warage == false)) {
           Alert warge = new Alert(AlertType.WARNING);
           warge.setTitle("Warning Dialog");
           warge.setHeaderText("Look, a Warning Dialog");
           warge.setContentText("Date is wrong");

           warge.showAndWait();
       }
    }




	@Override
	public void initialize(URL url, ResourceBundle rb) {
		txtCompany.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!checkNumeric(character)) {
                    event.consume();
                    war1.setVisible(true);
                    war1.setText("field can't contains characters");
                }
			}}
				);
		txtAddress.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!checkNumeric(character)) {
                    event.consume();
                    war2.setVisible(true);
                    war2.setText("field can't contains characters");
                }
			}}
				);
		txtContact.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!checkNumeric(character)) {
                    event.consume();
                    war3.setVisible(true);
                    war3.setText("field can't contains characters");
                }
			}}
				);
	
		location.getItems().addAll("Ariana", "Beja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan",
		                "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Médenine",
		                "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
		                "Tatouine", "Tozeur", "Tunis", "Zaghouan");

		        cathegorie.getItems().addAll("Technology", "Industry", "Art", "Agronomy", "Fashion", "Cooking", "Health", "HealthyFood"
		               );
		    currency.getItems().addAll("TND","EURO","DOLLAR");
	}
    @FXML
    void uploadPhoto(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();

         File file = fileChooser.showOpenDialog(null);

         Image img = new Image(file.toURI().toString());
         //phath.setText(file.getPath());
         imageprojet.setImage(img);
         imageprojet.setFitHeight(150);
         imageprojet.setFitWidth(150);
         path.setText( file.getName());
         path.setText(file.getName());
    }
    boolean checkNumeric(String value)    {
        String number=value.replaceAll("\\s+","");
        for(int j = 0 ; j<number.length();j++){ 
                if(!(((int)number.charAt(j)>=47 && (int)number.charAt(j)<=57)))
                {
                    return false;
                }
        }     
        return true;
    }

}
