package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessPlan;
import tn.esprit.b1.esprit1718b1fundraising.entities.BusinessProject;
import tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */
@Stateful
public class ContactsController implements Initializable {

    @FXML
    private AnchorPane paneaffiche;
    @FXML
    private JFXListView<BusinessProject> listProjects1;
    @FXML
    private AnchorPane paneanalyse;
    @FXML
    private JFXComboBox<String> risk;
    @FXML
    private JFXComboBox<String> riskdo;
    @FXML
    private JFXComboBox<String> risktrois;
    @FXML
    private JFXComboBox<String> riskkwatro;
    @FXML
    private JFXComboBox<String> riskchinko;
    @FXML
    private TextField immobilisation;
    @FXML
    private TextField cap_per;
    @FXML
    private JFXButton calcul;
    @FXML
    private Label riskiuno;

    @FXML
    private Label riskdeux;

    @FXML
    private Label risktrio;

    @FXML
    private Label riskquater;
    @FXML
    private Label riskcinq;
    @FXML
    private TextField result;
    @FXML
    private Label text;

    @FXML
    private PieChart pie;
    private ObservableList<PieChart.Data> data;
    int x=0;
    int y=0;
    float a,b;
    float fond;
	BusinessPlan P;
	BusinessProject project;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	risk.getItems().addAll("acceptable","unacceptable");
    	riskdo.getItems().addAll("acceptable","unacceptable");
    	risktrois.getItems().addAll("acceptable","unacceptable");
    	riskkwatro.getItems().addAll("acceptable","unacceptable");
    	riskchinko.getItems().addAll("acceptable","unacceptable");

    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
		try {
			Context ctx;

			ctx = new InitialContext();
			BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);
			Collection<BusinessProject> list ;
			list=proxy2.findAll();
			System.out.println(list);
			for(BusinessProject b:list){
				if(b.isValide()==false){
				//  Label l = new Label(b.getCategorie());
				 // System.out.println(b.getCategorie());
	              //  l.setFont(Font.font(null, FontWeight.BOLD, 14));
			listProjects1.getItems().add(b);}
			}

			//proxy2.findAll();

		} catch (NamingException e) {
			e.printStackTrace();
		}
		listProjects1.setCellFactory(new Callback<ListView<BusinessProject>, ListCell<BusinessProject>>() {
			
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
                           
		                     //  VBox Box = new VBox(new ImageView(img)); 
										System.out.println();
									
                          //Double a=  Double.parseDouble(Float.toString(item.getCompteprojet().getSom()));
                          //Double b=Double.parseDouble(Float.toString(item.getFinancement()));
	                               Label l=new Label(item.getTitle());
	                               l.setFont(Font.font(null, FontWeight.BOLD, 14));
	                               VBox sbox=new VBox(l);
		                            VBox vBox = new VBox(new Label(item.getDescription()));
		                            //System.out.println((a/b));
		                            VBox tBox=new VBox(new Label(item.getCategorie()));
		                            VBox mBox=new VBox(new Label(item.getLocalisation()));
		                            HBox hBox = new HBox(sbox,vBox,tBox,mBox);
		                             
		                            hBox.setSpacing(15);
		                            setGraphic(hBox);
		                         
		                        } else{
		                            setText(null);
		                            setGraphic(null);}	 
						 
						 
						 
						 
					 }
					
					
					
				};
			}
		} );
    }    

    @FXML
    private void analyser(ActionEvent event) {
     P= listProjects1.getSelectionModel().getSelectedItem().getBusinessPlan();
     System.out.println(P);
     project=listProjects1.getSelectionModel().getSelectedItem();
        paneaffiche.setVisible(false);
        paneanalyse.setVisible(true);
        riskiuno.setText(P.getRis_technique());
    	  riskdeux.setText(P.getRisque_clmarché());
    	  risktrio.setText(P.getRisque_financier());
    	  riskquater.setText(P.getRisque_juridique());
    	  riskcinq.setText(P.getRisque_politique());
    	  
    	  
    	  immobilisation.setText(String.valueOf(P.getImmobilisation()));
    	  cap_per.setText(String.valueOf(  P.getCap_per()));
    	
    }

    @FXML
    void calculer(ActionEvent event) throws NamingException {
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
    	Context ctx;

		ctx = new InitialContext();
		BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);
    	float mobilia = Float.parseFloat(immobilisation.getText());
    	float captilia =Float.parseFloat(cap_per.getText());
    fond=	proxy2.calculFondRoulement(captilia, mobilia);
    System.out.println(fond);
    String s = Float.toString(fond);
    result.setText(s);

    	


    }
    @FXML
    void showStatistics(ActionEvent event) {
    	  data = FXCollections.observableArrayList();
    	String s1=risk.getSelectionModel().getSelectedItem();
    	String s2=riskdo.getSelectionModel().getSelectedItem();
    	String s3=risktrois.getSelectionModel().getSelectedItem();
    	String s4=riskkwatro.getSelectionModel().getSelectedItem();
    	String s5=riskchinko.getSelectionModel().getSelectedItem();
    	if(s1.equals("acceptable")){x++;} else{y++;}
       	if(s2.equals("acceptable")){x++; }else{y++;}
    	if(s3.equals("acceptable")){x++;}else{y++;}
    	if(s4.equals("acceptable")){x++;}else{y++;}
    	if(s5.equals("acceptable")){x++;}else{y++;}
    	a=(x/5)*100;
    	b=(y/5)*100;
    	  data.addAll(new PieChart.Data("acceptable", x ),new PieChart.Data("unacceptable", y ));
          pie.setTitle("acceptance Creteria");
          pie.setData(data);

          x=0;
          y=0;
    	
    
    
}

    @FXML
    void showProjects(ActionEvent event) {

    }
    @FXML
    void validateProject(ActionEvent event) throws NamingException {
    	String jndiName2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/BusinessProjectService!tn.esprit.b1.esprit1718b1fundraising.services.BusinessProjectRemote";
    	Context ctx;

		ctx = new InitialContext();
		BusinessProjectRemote proxy2 = (BusinessProjectRemote) ctx.lookup(jndiName2);

if((b<30)&&(fond>0)){text.setText("This is a desirable situation for the company it ensures no bankruptcy circumstances project is valid."); 
System.out.println(project.isValide());
 project.setValide(true);
proxy2.update(project);

System.out.println(project.isValide());}
if((b>30)&&(fond<0)){text.setText("intolerable risk and a bankruptcy circumstances may happen the project is not valid");}
if((b<30)&&(fond<0)){text.setText("bankruptcy circumstances may happen the project is not valid");}
if((b>30)&&(fond>0)){text.setText("desirable situation for the company unless high perpercentage of unacceptable risks project is  not valid ");}
 
    }
}
