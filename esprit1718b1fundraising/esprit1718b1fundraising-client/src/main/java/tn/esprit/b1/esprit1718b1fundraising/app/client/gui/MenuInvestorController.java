package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class MenuInvestorController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton doctorBtn;
    @FXML
    private JFXButton appointmentBtn;
    @FXML
    private JFXButton paymentBtn;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private JFXButton exitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logOut(ActionEvent event) {
        try {
            Stage window=(Stage) exitBtn.getScene().getWindow();
          //  HospitalFX hospitalFX=new HospitalFX();
            //hospitalFX.start(new Stage());
            window.close();
        } catch (Exception ex) {
            Logger.getLogger(MenuInvestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
    
}
