package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ShowProjectFounderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView imageprojet;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
		Context ctx2;
		try {
			ctx2 = new InitialContext();
			BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx2.lookup(jndiName2);
		    BusinessProject b1=proxy2.getfounder(LoginController.userLogedIn.getId());
	    	if(b1.isValide()==true){
	    		label1.setText(b1.getTitle());
	    		label2.setText(b1.getDescription());
	    		label3.setText(b1.getCategorie());
	    		label4.setText(Float.toString(b1.getFinancement()));
	    	    File file = new File("C:/wamp64/www/Photos/"+b1.getPhoto());
		         Image img = new Image(file.toURI().toString());
             	    imageprojet.setImage(img);
	    		label5.setText(Float.toString(b1.getCA()));
	    		  TrayNotification tray = new TrayNotification();
		           tray.setTitle("Your Project is Accepted");
		           tray.setMessage("Congratulations ,Best of luck");
		           File file1 = new File("C:/wamp64/www/Photos/valide.jpg");
		           Image img1 = new Image(file.toURI().toString());
		           tray.setImage(img);
		           tray.showAndWait();
		           tray.dismiss();
	    	}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }    
    
}
