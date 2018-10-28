package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessPlan;
import tn.esprit.b1.esprit1718b1fundraising.services.BuisnessPlanServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CompteFounderServiceRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLBusinessPlanController implements Initializable {

    @FXML
    private JFXTextField creator;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextArea description;

   

    @FXML
    private JFXTextField capital;

    @FXML
    private JFXTextField asset;

    @FXML
    private JFXTextField stock;

    @FXML
    private JFXTextField liab;

    @FXML
    private JFXTextField debt;

    @FXML
    private JFXTextField legal;

    @FXML
    private JFXTextField technical;

    @FXML
    private JFXTextField political;

    @FXML
    private JFXTextField financial;
    @FXML
    private JFXTextField market;
    @FXML
    private Label war1;

    @FXML
    private Label war2;

    @FXML
    private Label war3;

    @FXML
    private Label war4;

    @FXML
    private Label war5;

    public static int idPlan;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	capital.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
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
    	asset.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
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
    	stock.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
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
    	liab.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!checkNumeric(character)) {
                    event.consume();
                    war4.setVisible(true);
                    war4.setText("field can't contains characters");
                }
			}}
				);
    	debt.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!checkNumeric(character)) {
                    event.consume();
                    war5.setVisible(true);
                    war5.setText("field can't contains characters");
                }
			}}
				);
    	
    	}    
    @FXML
    void save(ActionEvent event) throws NamingException {
    	String name=creator.getText();
    	String adresse=adress.getText();
    	//String num=phone.getText();
    	//String identite=cin.getText();
    	//String desc=description.getText();
    	float cap=  Float.parseFloat(capital.getText());
    	float Stock=Float.parseFloat(stock.getText());
    	float liability=Float.parseFloat(liab.getText());
    	float dette=Float.parseFloat(debt.getText());
    	String law=legal.getText();
    	String tech=technical.getText();
    	String politic=political.getText();
    	String finance=financial.getText();
    	String marche=market.getText();
    	//	java:jboss/exported/esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BuisnessPlanService!tn.esprit.b1.esprit1718b1fundraising.services.BuisnessPlanServiceRemote
    	String jndiName3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BuisnessPlanService!tn.esprit.b1.esprit1718b1fundraising.services.BuisnessPlanServiceRemote";
    	Context ctx3 = new InitialContext();
    	BuisnessPlanServiceRemote proxy3 =(BuisnessPlanServiceRemote) ctx3.lookup(jndiName3);
    	BusinessPlan BuisPlan=new BusinessPlan(name, adresse," typeprojet", cap, liability, Stock, liability, dette, law, tech,politic,finance, marche);
    	//proxy3.save(BuisPlan);
	 idPlan=proxy3.AddBuiPlan(BuisPlan);
	 int a =FXMLprojectController.idProject;
	 System.out.println(a);
	 proxy3.Affecter(idPlan, a);
	 

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
