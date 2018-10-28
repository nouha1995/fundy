package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;



public class AboutController implements Initializable{


    @FXML
    private MaterialDesignIconView iconClose;

    @FXML
    private Label founderNameLabel;

    @FXML
    private Label nameOrgLabel;

    @FXML
    private Label countryOrgLabel;

    @FXML
    private Label yearOrgLabel;

    @FXML
    private Label programOrgLabel;

    @FXML
    private Label EmailFounLabel;

    @FXML
    private Label careerFounLabel;

    @FXML
    private Label addressFounLabel;

    @FXML
    private Label telFounLabel;

    @FXML
    private Label nameChPLabel;

    @FXML
    private Label startDateChPLabel;

    @FXML
    private Label endDateChPLabel;

    @FXML
    private Label aboutChPLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	String n="null";
    	//Oganization part
    	 n=CreateChProjectNController.organization.getName();
    	nameOrgLabel.setText(n);
    	countryOrgLabel.setText(CreateChProjectNController.organization.getCountry());
    	programOrgLabel.setText(CreateChProjectNController.organization.getProgram());
    	if(CreateChProjectNController.organization.getFoundingYear()!=null)
    	yearOrgLabel.setText(CreateChProjectNController.organization.getFoundingYear().toString());
    	//Charity Project Part
    	nameChPLabel.setText(CreateChProjectNController.charityProject.getTitle());
    	if(CreateChProjectNController.charityProject.getDateDebut()!=null)
    	startDateChPLabel.setText(CreateChProjectNController.charityProject.getDateDebut().toString());
    	if(CreateChProjectNController.charityProject.getDateFin()!=null)
    	endDateChPLabel.setText(CreateChProjectNController.charityProject.getDateFin().toString());
    	if(CreateChProjectNController.charityProject.getDateFin()!=null)
    	aboutChPLabel.setText(CreateChProjectNController.charityProject.getDescription());
    	//user part
    	founderNameLabel.setText(LoginController.userLogedIn.getFirstName()+" "+LoginController.userLogedIn.getLastName());
    	EmailFounLabel.setText(LoginController.userLogedIn.getEmail());
    	careerFounLabel.setText(LoginController.userLogedIn.getLogin());
    	
    	
    }

    @FXML
    private void closeStage(MouseEvent event) {
        iconClose.getScene().getWindow().hide();
    }
    
}
