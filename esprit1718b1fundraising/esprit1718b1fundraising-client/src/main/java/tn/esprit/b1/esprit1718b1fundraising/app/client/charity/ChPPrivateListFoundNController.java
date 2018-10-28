package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

import com.itextpdf.text.pdf.TextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1fundraising.app.client.gui.MainNouhaController;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Message;
import tn.esprit.b1.esprit1718b1fundraising.entities.Organization;
import tn.esprit.b1.esprit1718b1fundraising.entities.Project;
import tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote;
//confirm 3=project has been saved and can be modified
//confirm 0 =project has been sent to admin
//confirm 1 project has been confirmed by amdin
//confirm 2 project has been refused by admin
public class ChPPrivateListFoundNController implements Initializable{
	
	@FXML
    private StackPane rootPane;

    @FXML
    private Pane popUpHolder;

    @FXML
    private JFXButton btnLogoff;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnAccount;
    
   

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private JFXButton eyeBtn;

    CharityServiceRemote chService;
  	OrganizationServiceRemote orgService;
  	static CharityProject chProject;
  	List<CharityProject> chPList;
    static Organization org;
    String jndiname2;
    String jndiname;
    Context ctx;
    int chProjectId;
    int orgId;
    int i;
  		

    @FXML
    void getBack(ActionEvent event) {
    	StackPane pane=null;
		try {
			pane = FXMLLoader.load(getClass().getResource("/tn/esprit/b1/esprit1718b1fundraising/app/client/main/FounderMain.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	rootPane.getChildren().setAll(pane);
    }
    @FXML
    void reload(ActionEvent event) {
    	StackPane pane=null;
		try {
			pane = FXMLLoader.load(getClass().getResource("/tn/esprit/b1/esprit1718b1fundraising/app/client/charity/ChProjectPrivateListFoudN.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	rootPane.getChildren().setAll(pane);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		jndiname2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CharityService!tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote";
		  jndiname="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/OrganizationService!tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote";
		  
			try {
				ctx = new InitialContext();
				chService=(CharityServiceRemote) ctx.lookup(jndiname2);
				 orgService=(OrganizationServiceRemote) ctx.lookup(jndiname);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			chPList=chService.getChPsByUserId(LoginController.userLogedIn.getId());
			System.out.println(chPList.size());
			table(chService.getChPsByUserId(LoginController.userLogedIn.getId()).size());
		
		
		
		
		
	}
	
	public void table(int rows) {
		
		GridPane table = new GridPane();
           
        for(i=0; i<rows; i++){
            for(int j=0;j<1;j++){
            	 AnchorPane chPContent=new AnchorPane();
            	 List<Integer> list=findRow(chPContent, j, i);
        	     HBox hBox=new HBox();
        	     VBox vBox=new VBox();
        	
        	    Image img=null;
        	 if(chPList.get(i).getPicture()==null)
        		  img=new Image(getClass().getResourceAsStream("../gui/tools/children.jpg"),200, 200,false,false);
        	 else
        	    img=new Image(getClass().getResourceAsStream(chPList.get(i).getPicture()),350,200,false,false);
        	 
        	   ImageView imgV=new ImageView();
        	HBox.setHgrow(imgV, Priority.ALWAYS);
        	 imgV.prefHeight(100);
        	 imgV.prefWidth(100);
        	 imgV.setImage(img);
        	 
        	JFXButton buttonModify=new JFXButton("modify");
        	 JFXButton buttonDel=new JFXButton("delete");
        	 buttonDel.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:  #06E6A1;");
        	 buttonModify.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:  #06E6A1;");
        	 buttonModify.setButtonType(ButtonType.RAISED);
        	 buttonDel.setButtonType(ButtonType.RAISED);
        	 buttonModify.setPrefHeight(100);
        	 buttonModify.setPrefWidth(200);
        	 buttonDel.setPrefHeight(100);
        	 buttonDel.setPrefWidth(200);
   			
        	 hBox.getChildren().add(imgV);
        	vBox.getChildren().add(buttonModify);
        	vBox.getChildren().add(buttonDel);
        	hBox.getChildren().add(vBox);
           
             chPContent.setPrefSize(350, 200);
             chPContent.getStyleClass().add("menus");
             imgV.fitHeightProperty().bind(chPContent.heightProperty());
           
             chPContent.getChildren().add(hBox);
            //add them to the GridPane
            addF( j, i,chPContent,table); //  (child, columnIndex, rowIndex)
           
            

            // margins are up to your preference
          //  GridPane.setMargin(chPContent2, new Insets(5));
            GridPane.setMargin(chPContent, new Insets(5));
           buttonDel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(chPList.get(list.get(0)).getConfirm().compareTo("2")==0 ||chPList.get(list.get(0)).getConfirm().compareTo("3")==0){
					System.out.println(list.get(0)+"deletechp"+chPList.get(list.get(0)).getIdProject());

					chService.deleteChPNouha(chPList.get(list.get(0)).getIdProject());
				orgService.deleteOrgNouha(chPList.get(list.get(0)).getOrganization().getId());
				StackPane pane=null;
				try {
					pane = FXMLLoader.load(getClass().getResource("/tn/esprit/b1/esprit1718b1fundraising/app/client/charity/ChProjectPrivateListFoudN.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	rootPane.getChildren().setAll(pane);
				
			         }
				else{
					Notifications notificationBuilder=Notifications.create()
								.title("Warning !!")
								.text("this project can not be deleted ")
								.hideAfter(Duration.seconds(5))
								.position(Pos.CENTER);
						
						notificationBuilder.show();
				}
				}
			
		});
            buttonModify.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					System.out.println(chPList.get(list.get(0)).getConfirm());
					if(chPList.get(list.get(0)).getConfirm().compareTo("2")==0){
						Notifications notificationBuilder=Notifications.create()
	  							.title("Not Confirmed")
	  							.text("project has been refused u need to delete it")
	  							.hideAfter(Duration.seconds(5))
	  							.position(Pos.CENTER);
	  					
	  					notificationBuilder.show();
					}
					else if(chPList.get(list.get(0)).getConfirm().compareTo("0")==0){
						
							Notifications notificationBuilder=Notifications.create()
		  							.title("Still Cheking it !!")
		  							.text("your project is still under analyse")
		  							.hideAfter(Duration.seconds(5))
		  							.position(Pos.CENTER);
		  					
		  					notificationBuilder.show();
					}
					else {
						chProject=chPList.get(list.get(0));
						org=orgService.find(chPList.get(list.get(0)).getOrganization().getId());
						setStage("/tn/esprit/b1/esprit1718b1fundraising/app/client/charity/UpdateChProjectN.fxml");
					}
					
				}
			});
         }
        }
         ScrollPane sp = new ScrollPane();
		  VBox box = new VBox();
		  box.getChildren().add(sp);
		  VBox.setVgrow(sp, Priority.ALWAYS);
        table.setAlignment(Pos.CENTER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setVmax(1000);
        sp.setPrefHeight(391);
        sp.setContent(table);
       stackPane.getChildren().add(box);
         
	}
	
public void addF(int colIndex, int rowIndex,AnchorPane pane,GridPane table){
	pane.setOnMouseEntered(e -> {
        System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
    });
    table.add(pane, colIndex, rowIndex);
}
	
public List<Integer> findRow(AnchorPane pane,int colIndex, int rowIndex){
	pane.setOnMouseEntered(e -> {
        System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
    });
	List<Integer> list=new ArrayList<>();
	list.add(rowIndex);
	list.add(colIndex);
	return list;
}
private void setStage(String fxml) {
    try {
        //dim overlay on new stage opening
        Region veil = new Region();
        veil.setPrefSize(768, 684);
        veil.setStyle("-fx-background-color:rgba(0,0,0,0.3)");
        Stage newStage = new Stage();
       //Parent parent = FXMLLoader.load(getClass().getResource(fxml));
        FXMLLoader parent=new FXMLLoader(getClass().getResource(fxml));
        Parent root= (Parent)parent.load();
      
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.getScene().getRoot().setEffect(new DropShadow());
        newStage.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                rootPane.getChildren().add(veil);
            } else if (rootPane.getChildren().contains(veil)) {
                rootPane.getChildren().removeAll(veil);
            }
            
        });
        newStage.centerOnScreen();
        newStage.show();
    } catch (IOException ex) {
        Logger.getLogger(MainNouhaController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}
