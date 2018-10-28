package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.CompteProjet;
import tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AdminStatistiqueController implements Initializable {
	

    /**
     * Initializes the controller class.
     */
	 @FXML
	    private PieChart mypiechart;

	    @FXML
	    private BarChart<String,Float> barchart;

	    @FXML
	    private JFXComboBox<Date> dates;
	    private ObservableList data;
	    @FXML
	    private Label foundernumbet;

	    @FXML
	    private Label investornuber;

	    @FXML
	    private Label projectnumber;

	    @FXML
	    private Label usernuber;


	   
    	XYChart.Series series = new XYChart.Series();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	String jndiName6="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/TransactionService!tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote";

		Context ctx6;
		String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
		
		Context ctx;
		Collection<BusinessProject> list ;
			
		try {
			ctx6 = new InitialContext();
	    	 TransactinServiceRemote proxy6 =(TransactinServiceRemote) ctx6.lookup(jndiName6);
List<Date> dattte= proxy6.getlistDate();
//List<Date> listdate=new ArrayList<>();

 
BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx6.lookup(jndiName2);
data = FXCollections.observableArrayList();
dates.getItems().addAll(dattte);

List<Object[]> def = new ArrayList<>();
def=proxy2.BrowseProjects();
System.out.println(def.isEmpty());
for(Object[] o : def){
    long count = (long) o[0] ;
    String s=(String)o[1];
    System.out.println(s);
    System.out.println(count);
 mypiechart.setTitle("Projects per Cathegory");
 double d = (double)count;
 Data x = new PieChart.Data(s, d);

data.add(new PieChart.Data(s,d));

}

mypiechart.getData().addAll(data);

Long a=proxy6.getfoundersNumber();
Long b=proxy6.getInvestorsNumber();
Long c=proxy6.getProjectNumber();
Long d=proxy6.getUserNumber();
foundernumbet.setText(Long.toString(a));
investornuber.setText(Long.toString(b));
projectnumber.setText(Long.toString(c));
usernuber.setText(Long.toString(d));


 
 

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }    

    @FXML
    void choosedate(ActionEvent event) throws NamingException {
    	String jndiName6="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/TransactionService!tn.esprit.b1.esprit1718b1fundraising.services.TransactinServiceRemote";

		Context ctx6;
    	ctx6 = new InitialContext();
   	 TransactinServiceRemote proxy6 =(TransactinServiceRemote) ctx6.lookup(jndiName6);

    	Date d=dates.getSelectionModel().getSelectedItem();
    	 List<Object[]> abc = new ArrayList<>();
    	 abc =proxy6.FindByDate(d);
    	barchart.setTitle("chart");
    	for(Object[] o : abc){
    	    double sum = (double) o[0] ;
    	    CompteProjet s=(CompteProjet)o[1];
    	    series.getData().add(new XYChart.Data(s.getBus_Project().getTitle(), sum));
    	}
    	barchart.getData().add(series);
    	

    	
    }
	

}
