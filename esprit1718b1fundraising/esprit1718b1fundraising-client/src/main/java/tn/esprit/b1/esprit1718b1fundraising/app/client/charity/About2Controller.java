package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;

public class About2Controller implements Initializable {

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
	    	 n=UpdateChProjectNController.organization.getName();
	    	nameOrgLabel.setText(n);
	    	
	    	if(UpdateChProjectNController.organization.getCountry()!=null)
	    	countryOrgLabel.setText(UpdateChProjectNController.organization.getCountry());
	    	else if(ChPPrivateListFoundNController.org.getCountry()!=null)countryOrgLabel.setText(ChPPrivateListFoundNController.org.getCountry());
	    	
	    	if(UpdateChProjectNController.organization.getProgram()!=null)
	    	programOrgLabel.setText(UpdateChProjectNController.organization.getProgram());
	    	else if(ChPPrivateListFoundNController.org.getProgram()!=null) programOrgLabel.setText(ChPPrivateListFoundNController.org.getProgram());
	    	
	    	
	    	if(UpdateChProjectNController.organization.getFoundingYear()!=null)
	    	yearOrgLabel.setText(UpdateChProjectNController.organization.getFoundingYear().toString());
	    	else if(ChPPrivateListFoundNController.org.getFoundingYear() !=null) yearOrgLabel.setText(ChPPrivateListFoundNController.org.getFoundingYear().toString());
	    	
	    	//Charity Project Part
	    	nameChPLabel.setText(UpdateChProjectNController.charityProject.getTitle());
	    	
	    	if(UpdateChProjectNController.charityProject.getDateDebut()!=null)
	    	startDateChPLabel.setText(UpdateChProjectNController.charityProject.getDateDebut().toString());
	    	else if(ChPPrivateListFoundNController.chProject.getDateDebut()!=null)startDateChPLabel.setText(ChPPrivateListFoundNController.chProject.getDateDebut().toString());
	    
	    	if(UpdateChProjectNController.charityProject.getDateFin()!=null)
	    	endDateChPLabel.setText(UpdateChProjectNController.charityProject.getDateFin().toString());
	    	else if(ChPPrivateListFoundNController.chProject.getDateFin()!=null)endDateChPLabel.setText(ChPPrivateListFoundNController.chProject.getDateFin().toString());
	    	
	    	
	    	aboutChPLabel.setText(UpdateChProjectNController.charityProject.getDescription());
	    	
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
