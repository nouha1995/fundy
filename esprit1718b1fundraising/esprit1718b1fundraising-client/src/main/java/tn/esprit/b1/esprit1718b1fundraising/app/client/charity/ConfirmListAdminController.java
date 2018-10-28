package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;

import org.controlsfx.control.Notifications;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.ByteArray;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXListView;
import com.sun.nio.sctp.Notification;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Message;
import tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.MessagesServiceRemote;

public class ConfirmListAdminController implements Initializable{

	
	  @FXML
	    private MaterialDesignIconView iconClose;

	    @FXML
	    private JFXButton eyeBtn;

	    @FXML
	    private StackPane stackPane;
	    @FXML
	    private JFXListView<HBox> listView;
	    
	   
	    String jndiname2;
	    String  jndiname;
	    Context ctx;
	    MessagesServiceRemote megService;
	    CharityServiceRemote chService;
	    CharityProject chProject;
	    int chProjectId;
	    Message message;
	  	List<Message> msgList=new ArrayList<Message>();
	  	int i;
	  	
	    @FXML
	    void closeStage(MouseEvent event) {

	    	iconClose.getScene().getWindow().hide();
	    }

	    private static byte[] readBytesFromFile(String filePath) throws IOException {
	        File inputFile = new File(filePath);
	        FileInputStream inputStream = new FileInputStream(inputFile);
	         
	        byte[] fileBytes = new byte[(int) inputFile.length()];
	        inputStream.read(fileBytes);
	        inputStream.close();
	         
	        return fileBytes;
	    }
	    
	    public byte[] fileThings(String string,int j){
	    	//pdf section******
			
	    	String[] data=string.split(",");
			Document document=new Document(PageSize.A3,50,50,50,50);
			
			try {
				PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\HP\\Downloads\\Confirmation-File"+j+".pdf"));
			} catch (FileNotFoundException | DocumentException e) {
				
				e.printStackTrace();
			}
			document.open();
			Paragraph title1 = new Paragraph("Charity Project Confirmation Demand", FontFactory.getFont(FontFactory.HELVETICA, 36, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
		  Chapter chapter1 = new Chapter(title1, 1);
		 
		chapter1.setNumberDepth(0);

		 try {
				document.add(chapter1);
			} catch (DocumentException e1) {
		
				e1.printStackTrace();
			}
		Paragraph title11 = new Paragraph("What is Your organization's registered name ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title12 = new Paragraph("Country of Registration ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title13 = new Paragraph("Year Founded ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title14 = new Paragraph("	What is Your organization's mission ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title15 = new Paragraph("What is Your Charity Project's Name ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title16 = new Paragraph("Availability's Date start in ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title17 = new Paragraph("Availability's Date ends in ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title18 = new Paragraph("describe your aim from this project ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title19 = new Paragraph("Founder's Identity ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph title22 = new Paragraph("Founder's email ?", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		
			    Section section1 = chapter1.addSection(title11);
			 Paragraph someSectionText = new Paragraph(data[0],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section1.add(someSectionText);
			 try {
					document.add(section1);
				} catch (DocumentException e1) {
					
					e1.printStackTrace();
				}
				
			 
			
			 Section section2 = chapter1.addSection(title12);
			 Paragraph someSectionText2 = new Paragraph(data[1],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section2.add(someSectionText2);
			 try {
					document.add(section2);
				} catch (DocumentException e1) {
					
					e1.printStackTrace();
				}
				
			 
				
			 Section section3 = chapter1.addSection(title13);
			// Date date=Date.from(orgYear.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			 Paragraph someSectionText3 = new Paragraph(data[2],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section3.add(someSectionText3);
			  try {
					document.add(section3);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			  Section section4 = chapter1.addSection(title14);
			 Paragraph someSectionText4 = new Paragraph(data[3],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section4.add(someSectionText4);
			 try {
					document.add(section4);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			  Section section5 = chapter1.addSection(title15);
			 Paragraph someSectionText5 = new Paragraph(data[4],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section5.add(someSectionText5);
			 try {
					document.add(section5);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			  Section section6 = chapter1.addSection(title16);
			 // Date date1=Date.from(startChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			 Paragraph someSectionText6 = new Paragraph(data[5],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section6.add(someSectionText6);
			 try {
					document.add(section6);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			  Section section7 = chapter1.addSection(title17);
			  //Date date2=Date.from(endChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			 Paragraph someSectionText7 = new Paragraph(data[6],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section7.add(someSectionText7);
			 try {
					document.add(section7);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Section section8 = chapter1.addSection(title18);
			 Paragraph someSectionText8 = new Paragraph(data[7],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section8.add(someSectionText8);
			 try {
					document.add(section8);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 
			  Section section9 = chapter1.addSection(title19);
			 Paragraph someSectionText9 = new Paragraph(data[8],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section9.add(someSectionText9);
			 try {
					document.add(section9);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Section section22 = chapter1.addSection(title22);
			 Paragraph someSectionText10 = new Paragraph(data[9],FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section22.add(someSectionText10);
			 try {
					document.add(section22);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			document.close();
			//pdf section creation almost end
			
			//insertion section of pdf begin
			byte[] file=null;
			return file;
	    	
	    }

	   
	    
	  
	    @FXML
	    void eyeBtnAction(ActionEvent event) {
	    	 msgList=new ArrayList<Message>();
	    	 listView.getItems().clear();
	    	function();
	    }
	    
	    
      public void function(){
    	  msgList=megService.getMsgByUserComfirm();
  		
  		listView.getStylesheets().add("style.css");
  		listView.setExpanded(true);
  		listView.depthProperty().set(1);
  		
  		
  		
  		for( i=0;i<msgList.size();i++){
  			
  		 HBox contents=new HBox();
  			VBox buttons=new VBox();
  			contents.setSpacing(100);
  			JFXButton download=new JFXButton("download");
  			JFXButton confirm=new JFXButton("confirm");
  			JFXButton notConfirm=new JFXButton(" Not confirm");
  			download.setStyle("-fx-background-color:  #06E6A1;");
  			confirm.setStyle("-fx-background-color: #008F82;");
  			notConfirm.setStyle("-fx-background-color:  #06E6A1;");
  			download.getStyleClass().add("button123");
  			confirm.getStyleClass().add("button123");
  			notConfirm.getStyleClass().add("button123");
  			download.setButtonType(ButtonType.RAISED);
  			download.setPrefHeight(30);
  			download.setPrefWidth(260);
  			confirm.setButtonType(ButtonType.RAISED);
  			confirm.setPrefHeight(30);
  			confirm.setPrefWidth(260);
  			notConfirm.setButtonType(ButtonType.RAISED);
  			notConfirm.setPrefHeight(30);
  			notConfirm.setPrefWidth(260);
  			buttons.getChildren().add(download);
  			buttons.getChildren().add(confirm);
  			buttons.getChildren().add(notConfirm);
  			
  			Label lb=new Label();
  			
  			Image img=new Image(getClass().getResourceAsStream("../gui/tools/project.png"));
  			ImageView imgV=new ImageView();
  			imgV.setFitHeight(80);
  			imgV.setFitWidth(80);
  			imgV.setImage(img);
  			lb.setGraphic(imgV);
  			lb.setText("Charity Project N°"+i);
  			contents.getChildren().add(lb);
  			contents.getChildren().add(buttons);
  			listView.getItems().add(contents);
  			
  			notConfirm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					String b="2";
					System.out.println(msgList.get(listView.getSelectionModel().getSelectedIndex()).getIdMessage()+" listview"+msgList.size());
					chProject=chService.find(msgList.get(listView.getSelectionModel().getSelectedIndex()).getStatus());
					System.out.println(chProject.getIdProject());
					chService.updateCharityProjectAdminNouha(b, chProject.getIdProject());
					Image img=new Image(getClass().getResourceAsStream("../gui/tools/Tick_Mark_Dark.png"));
  					Notifications notificationBuilder=Notifications.create()
  							.title("Not Confirmed")
  							.text("project has been refused")
  							.graphic(new ImageView(img))
  							.hideAfter(Duration.seconds(5))
  							.position(Pos.CENTER);
  					
  					notificationBuilder.show();
					
  					megService.deleteNouha(msgList.get(listView.getSelectionModel().getSelectedIndex()).getIdMessage());
				}
			});
  			
  			confirm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					String a="1";
					System.out.println(msgList.get(listView.getSelectionModel().getSelectedIndex()).getStatus()+" id project");
					System.out.println(msgList.get(listView.getSelectionModel().getSelectedIndex()).getIdMessage()+" id mesg");
					chProject=chService.find(msgList.get(listView.getSelectionModel().getSelectedIndex()).getStatus());
					 System.out.println(chProject.getIdProject());
					chService.updateCharityProjectAdminNouha(a, chProject.getIdProject());
					Image img=new Image(getClass().getResourceAsStream("../gui/tools/Tick_Mark_Dark.png"));
  					Notifications notificationBuilder=Notifications.create()
  							.title("Confirmation")
  							.text("The project has been confirmed successfully ")
  							.graphic(new ImageView(img))
  							.hideAfter(Duration.seconds(5))
  							.position(Pos.CENTER);
  					
  					notificationBuilder.show();
  					megService.deleteNouha(msgList.get(listView.getSelectionModel().getSelectedIndex()).getIdMessage());
				}
			});
  			download.setOnAction(new EventHandler<ActionEvent>() {
  				
  				@Override
  				public void handle(ActionEvent event) {
  					System.out.println(msgList.get(listView.getSelectionModel().getSelectedIndex()).getIdMessage()+" listview");
  					fileThings(msgList.get(listView.getSelectionModel().getSelectedIndex()).getContent(), msgList.get(listView.getSelectionModel().getSelectedIndex()).getIdMessage());
  					Image img=new Image(getClass().getResourceAsStream("../gui/tools/Tick_Mark_Dark.png"));
  					Notifications notificationBuilder=Notifications.create()
  							.title("Your download is complete")
  							.text("has been saved to downoads")
  							.graphic(new ImageView(img))
  							.hideAfter(Duration.seconds(5))
  							.position(Pos.CENTER);
  					
  					notificationBuilder.show();
  							
  					
  					
  				}
  			});	

  		}
  	}
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		 jndiname="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CharityService!tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote";
		jndiname2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/MessagesService!tn.esprit.b1.esprit1718b1fundraising.services.MessagesServiceRemote";
		try {
			ctx = new InitialContext();
			 megService=(MessagesServiceRemote) ctx.lookup(jndiname2);
			 chService=(CharityServiceRemote) ctx.lookup(jndiname);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		function();
				
	}

}
