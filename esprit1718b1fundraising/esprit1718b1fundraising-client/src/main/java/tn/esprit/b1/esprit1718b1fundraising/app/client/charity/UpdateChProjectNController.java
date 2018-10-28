package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1fundraising.app.client.gui.MainNouhaController;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Message;
import tn.esprit.b1.esprit1718b1fundraising.entities.Organization;
import tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.MessagesServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;

public class UpdateChProjectNController implements Initializable {

	 @FXML
     private AnchorPane rootPane;
     

     @FXML
     private StackPane stackPane;
     
	    @FXML
	    private JFXButton NextBtn1;

	    @FXML
	    private JFXButton previewBtn1;

	    @FXML
	    private JFXTextField orgName;

	    @FXML
	    private JFXDatePicker orgYear;

	    ObservableList<String> cities=FXCollections.observableArrayList();

	    @FXML
	    private JFXComboBox<String> orgCountry=new JFXComboBox<>(cities);
	    
      
	    @FXML
	    private JFXCheckBox animalsBox;

	    @FXML
	    private JFXCheckBox disasterRecoveryBox;

	    @FXML
	    private JFXCheckBox DemocracyBox;

	    @FXML
	    private JFXCheckBox cultureBox;

	    @FXML
	    private JFXCheckBox childrenBox;

	    @FXML
	    private JFXCheckBox healthBox;

	    @FXML
	    private JFXCheckBox humanRightBox;

	    @FXML
	    private JFXCheckBox humanitarianAssisBox;

	    @FXML
	    private JFXCheckBox educationBox;

	    @FXML
	    private JFXCheckBox climateBox;

	    @FXML
	    private JFXCheckBox environmentBox;

	    @FXML
	    private JFXCheckBox economicBox;

	    @FXML
	    private JFXCheckBox hungerBox;

	    @FXML
	    private JFXCheckBox microfinanceBox;

	    @FXML
	    private JFXCheckBox TechBox;
	    
	    @FXML
	    private JFXTextField chProjectName;

	    @FXML
	    private JFXButton submitChP;

	    @FXML
	    private JFXDatePicker endChPDate;

	    @FXML
	    private JFXTextArea descriptionChP;

	    @FXML
	    private JFXDatePicker startChPDate;


	    @FXML
	    private JFXTabPane tabPane;
	    
	  @FXML
   private JFXButton previewBtn2;
	    
	 @FXML
  private JFXButton NextBtn2;
	 
	 @FXML
  private ImageView chPPicture;

  @FXML
  private ImageView orgLogo;
  
  @FXML
  private JFXButton chPPictureBtn;

  @FXML
  private JFXButton orgLogoBtn;
  
  @FXML
  private MaterialDesignIconView iconClose;
  
  @FXML
  private MaterialDesignIconView iconClose1;
  @FXML
  private JFXButton closeBtn;
  
	    CharityServiceRemote chService;
		OrganizationServiceRemote orgService;
		MessagesServiceRemote megService;
	    CharityProject chProject;
	    Message message;
	    Organization org;
	    String jndiname2;
	    String jndiname3;
	    String jndiname4;
	    String jndiname;
	    Context ctx;
	    int chProjectId;
	    int orgId;
	    String selectedProgram=null;
	    static Organization organization;
	    static CharityProject charityProject;
	    CharityProject chPofListfound;
	    Organization orgOfListFound;
	    
	   
	    @FXML
	    void closeStage(MouseEvent event) {

	    	 JFXDialogLayout content=new JFXDialogLayout();
	            content.setHeading(new Text("Close Window"));
	            content.setBody(new Text("are you sure you want to close your charity project ?"));
	            JFXDialog dialog=new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
	            JFXButton save=new JFXButton("Save And Close");
	            JFXButton close=new JFXButton("Close");
	            save.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						//save project:comfirm=3 has been saved without submitting
						dialog.close();
						chService.updateCharityProjectAdminNouha("3", chProjectId);
						
	  					iconClose.getScene().getWindow().hide();
					}
				});
	            
	            close.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						dialog.close();
						chService.deleteChPNouha(chProjectId);
						orgService.deleteOrgNouha(orgId);
						 iconClose1.getScene().getWindow().hide();
					}
				});
	          
				content.setActions(save,new Label(" "),close);
			
				dialog.show();
	    	
	    }

	    @FXML
	    void chPPictureAction(ActionEvent event) {
	    	
		        	FileChooser fc=new FileChooser();
			    	fc.setInitialDirectory(new File("C:\\Users\\HP\\git\\esprit1718b1fundraising\\esprit1718b1fundraising\\esprit1718b1fundraising-client\\src\\main\\java\\tn\\esprit\\b1\\esprit1718b1fundraising\\app\\client\\gui\\tools"));
			    	File file=fc.showOpenDialog(null);
			    	Image img=null;
			    	if(file!=null)
			    		img=new Image(getClass().getResourceAsStream("../gui/tools/"+file.getName()),273,249,false,false);
			    	else
			    		System.out.println("fileimage not found");
			    	chPPicture.setImage(img);
			    	chService.updatePictureChPNouha("../gui/tools/"+file.getName(), chProjectId);
		      
	    }
	    @FXML
	    void orgLogoAction(ActionEvent event) {
	    	
		       
		        	FileChooser fc=new FileChooser();
			    	fc.setInitialDirectory(new File("C:\\Users\\HP\\git\\esprit1718b1fundraising\\esprit1718b1fundraising\\esprit1718b1fundraising-client\\src\\main\\java\\tn\\esprit\\b1\\esprit1718b1fundraising\\app\\client\\gui\\tools"));
			    	File file=fc.showOpenDialog(null);
			    	Image img=null;
			    	if(file!=null)
			    		{System.out.println(file.getName());
			         img=new Image(getClass().getResourceAsStream("../gui/tools/"+file.getName()),273,249,false,false);
			    		// img=new Image("../gui/tools/"+file.getName(),273,249,false,false);
			         }
			    	else
			    		System.out.println("fileimage not found");
			    	orgLogo.setImage(img);
			    	orgService.updateLogoOrgNouha("../gui/tools/"+file.getName(), orgId);
		    
	    }
	    
	    @FXML
	    void NextStep1(ActionEvent event) {
	    	
	    	tabPane.getSelectionModel().select(1);
	    	/*
	    	Date date=Date.from(orgYear.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	org=new Organization(orgName.getText(),orgCountry.getValue(),date, selectedProgram, "file");
	    orgService.updateOrganizationNouha(org.getName(), org.getCountry(), org.getFoundingYear(), org.getProgram(), org.getProgramMaterials(), orgId);*/
	    	
	    	
	    }
	    @FXML
	    void NextStep2(ActionEvent event) {
	    	tabPane.getSelectionModel().select(2);
	    }

	    @FXML
	    void previewAction1(ActionEvent event) {
      	
	    	 organization=orgService.find(orgId);
	    	 charityProject=chService.find(chProjectId);
	    	 setStage("/tn/esprit/b1/esprit1718b1fundraising/app/client/charity/About2.fxml");
	    	
	    	
	    	
	    }
	    @FXML
	    void nameOrgReleased(KeyEvent event) {
	    	
	    	orgService.updateOrganizationNouha(orgName.getText(), null, null, null, null, orgId);
	    	
             
	    	
	    }
	    @FXML
	    void yearOrgAction(ActionEvent event) {
	    	Date date=Date.from(orgYear.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	if(date.after(new Date())){
	    		orgYear.getEditor().clear();
	    		Notifications notificationBuilder=Notifications.create()
						.title("Warning !!")
						.text("Your date pass the current date")
						.hideAfter(Duration.seconds(5))
						.position(Pos.CENTER);
				
				notificationBuilder.show();
				
	    	}
	    	else
	    	orgService.updateOrganizationNouha(null, null, date, null, null, orgId);

	    }
	    @FXML
	    void countryOrgAction(ActionEvent event) {
	    	orgService.updateOrganizationNouha(null, orgCountry.getValue(),null, null, null, orgId);

	    }
	    @FXML
	    void handleButtonAction(ActionEvent event) {
	    	if(animalsBox.isSelected()) {
	    		selectedProgram=animalsBox.getText();
	    	}
	    	
	    	if(childrenBox.isSelected()) selectedProgram=childrenBox.getText();
	    	
	    	if(climateBox.isSelected()) selectedProgram=climateBox.getText();
	    	
	    	if(cultureBox.isSelected()) selectedProgram=cultureBox.getText();
	    	
	    	if(DemocracyBox.isSelected()) selectedProgram=DemocracyBox.getText();
	    	
	    	if(disasterRecoveryBox.isSelected()) selectedProgram=disasterRecoveryBox.getText();
	    	
	    	if(economicBox.isSelected()) selectedProgram=economicBox.getText();
	    	
	    	if(educationBox.isSelected()) selectedProgram=educationBox.getText();
	    	
	    	if(environmentBox.isSelected()) selectedProgram=environmentBox.getText();
	    	
	    	if(healthBox.isSelected()) selectedProgram=healthBox.getText();
	    	
	    	if(humanitarianAssisBox.isSelected()) selectedProgram=humanitarianAssisBox.getText();
	    	
	    	if(humanRightBox.isSelected()) selectedProgram=humanRightBox.getText();
	    	
	    	if(hungerBox.isSelected()) selectedProgram=hungerBox.getText();
	    	
	    	if(microfinanceBox.isSelected()) selectedProgram=microfinanceBox.getText();
	    	
	    	if(TechBox.isSelected()) selectedProgram=TechBox.getText();
	    	
	    	orgService.updateOrganizationNouha(null, null, null, selectedProgram, null, orgId);
       
	    }
	    
	    @FXML
	    void nameChPAction(KeyEvent event) {
       chService.updateCharityProjectNouha(chProjectName.getText(), null, null, null, chProjectId);
	    }
	    @FXML
	    void despChPAction(KeyEvent event) {
	       chService.updateCharityProjectNouha(null, null, null, descriptionChP.getText(), chProjectId);

	    }

	    @FXML
	    void startDateChPAction(ActionEvent event) {
	    	
	    	Date date=Date.from(startChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		       chService.updateCharityProjectNouha(null, date, null, null, chProjectId);


	    }
	    @FXML
	    void endDateChPAction(ActionEvent event) {
             
	    	Date date2=Date.from(endChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	if(date2.before(Date.from(startChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))){
	    		endChPDate.getEditor().clear();
	    		Notifications notificationBuilder=Notifications.create()
							.title("Warning !!")
							.text("Your end date is before start date")
							.hideAfter(Duration.seconds(5))
							.position(Pos.CENTER);
					
					notificationBuilder.show();
					endChPDate.getEditor().clear();
	    	}
	    	else
	    	chService.updateCharityProjectNouha(null, null, date2, null, chProjectId);

	    }
	    
	    private static byte[] readBytesFromFile(String filePath) throws IOException {
	        File inputFile = new File(filePath);
	        FileInputStream inputStream = new FileInputStream(inputFile);
	         
	        byte[] fileBytes = new byte[(int) inputFile.length()];
	        inputStream.read(fileBytes);
	        inputStream.close();
	         
	        return fileBytes;
	    }

	    public String testString(){
                   String country="";
                   String year="";
                   String startDate="";
                   String endDate="";
                   String program="";
				
			 if(orgCountry.getValue()==null) country=orgOfListFound.getCountry();
			 else country=orgCountry.getValue();
			 if(orgYear.getValue()!=null)year=Date.from(orgYear.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString();
			 else year=orgOfListFound.getFoundingYear().toString();
			 if(selectedProgram==null)program=orgOfListFound.getProgram();
			 else program=selectedProgram;
			 if(startChPDate.getValue()==null)startDate=chPofListfound.getDateDebut().toString();
			 else startDate=Date.from(startChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString();
			 if(endChPDate.getValue()==null)endDate=chPofListfound.getDateFin().toString();
			 else endDate=Date.from(endChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString();
			 
			 String mm=orgName.getText()+","+country+","+year
					 +","+program+","+chProjectName.getText()+","+startDate
					 +","+endDate+","+descriptionChP.getText()+
							 ","+LoginController.userLogedIn.getFirstName()+" "+LoginController.userLogedIn.getFirstName()+","+LoginController.userLogedIn.getEmail();
		
			 return mm;
	    } 

	    @FXML
	    void submitChPAction(ActionEvent event) throws ParseException {
	    	//Date date=Date.from(startChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	//Date date2=Date.from(endChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	//chProject=new CharityProject(chProjectId, chProjectName.getText(), date, date2, descriptionChP.getText(), LoginController.userLogedIn, org);
         //chService.updateCharityProjectNouha(chProjectName.getText(), date, date2, descriptionChP.getText(), chProjectId);
	    	System.out.println("w'er in");
         JFXDialogLayout content=new JFXDialogLayout();
         content.setHeading(new Text("Comfirmation"));
         content.setBody(new Text("are you sure you want to submit your charity project ?"));
         JFXDialog dialog=new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
         
         JFXButton save=new JFXButton("Save");
         JFXButton submit=new JFXButton("Submit");
         JFXButton close=new JFXButton("Close");
         save.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					//save project:comfirm=3 has been saved without submitting
					dialog.close();
					chService.updateCharityProjectAdminNouha("3", chProjectId);
					
					
					Stage stage = (Stage) NextBtn1.getScene().getWindow();
		           
		            stage.close();	
				}
			});
         
                     submit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					byte []file=fileThings();
					System.out.println("ChPId"+chProjectId+"orgId"+orgId);
					chService.updateCharityProjectAdminNouha("0", chProjectId);
					
						message=new Message(testString(), "nowTime", chProjectId,file, LoginController.userLogedIn, LoginController.userManagment.findByUsername("admin"));
		            megService.addMessage(message);
		            dialog.close();
		            Stage stage = (Stage) NextBtn1.getScene().getWindow();
		           
		            stage.close();					
				}
			});
         
         close.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					dialog.close();
				}
			});
       
			content.setActions(save,new Label(" "),submit,new Label(" "),close);
		
			dialog.show();
         
    	    	
	    }

	    public byte[] fileThings(){
	    	//pdf section******
			
			Document document=new Document(PageSize.A3,50,50,50,50);
			
			try {
				PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\HP\\Downloads\\Confirmation-File.pdf"));
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
			 Paragraph someSectionText = new Paragraph(orgName.getText(),FontFactory.getFont(FontFactory.HELVETICA, 14));
			 
			 section1.add(someSectionText);
			 try {
					document.add(section1);
				} catch (DocumentException e1) {
					
					e1.printStackTrace();
				}
				
			 
			
			 Section section2 = chapter1.addSection(title12);
			 
			 Paragraph someSectionText2 = new Paragraph(orgCountry.getValue(),FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section2.add(someSectionText2);
			 
			 try {
					document.add(section2);
				} catch (DocumentException e1) {
					
					e1.printStackTrace();
				}
				
			 
			
			 Section section3 = chapter1.addSection(title13);
			 Date date=null;
			 String dateString="";
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 if(orgYear.getValue()!=null){
			  date=Date.from(orgYear.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			  dateString=dateString.toString();
			 }
			 else
				 dateString=dateFormat.format(orgOfListFound.getFoundingYear());
			 Paragraph someSectionText3 = new Paragraph(dateString,FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section3.add(someSectionText3);
			  try {
					document.add(section3);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			  Section section4 = chapter1.addSection(title14);
			 Paragraph someSectionText4 = new Paragraph(selectedProgram,FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section4.add(someSectionText4);
			 try {
					document.add(section4);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			  Section section5 = chapter1.addSection(title15);
			 Paragraph someSectionText5 = new Paragraph(chProjectName.getText(),FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section5.add(someSectionText5);
			 try {
					document.add(section5);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			 Date date1=null;
			 String dateString1="";
			  Section section6 = chapter1.addSection(title16);
			  if(startChPDate.getValue()!=null){
			  date1=Date.from(startChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			  dateString1=date1.toString();
			  }
			  else
				  dateString1=dateFormat.format(chPofListfound.getDateDebut());
			 Paragraph someSectionText6 = new Paragraph(dateString1,FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section6.add(someSectionText6);
			 try {
					document.add(section6);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Date date2=null;
			 String dateString2="";
			  Section section7 = chapter1.addSection(title17);
			  if(endChPDate.getValue()!=null){
			   date2=Date.from(endChPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			   dateString2=date2.toString();
			  }
			  else
				  dateString2=dateFormat.format(chPofListfound.getDateFin());
			 Paragraph someSectionText7 = new Paragraph(dateString2,FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section7.add(someSectionText7);
			 try {
					document.add(section7);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Section section8 = chapter1.addSection(title18);
			 Paragraph someSectionText8 = new Paragraph(descriptionChP.getText(),FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section8.add(someSectionText8);
			 try {
					document.add(section8);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 
			  Section section9 = chapter1.addSection(title19);
			 Paragraph someSectionText9 = new Paragraph(LoginController.userLogedIn.getFirstName()+" "+LoginController.userLogedIn.getFirstName(),FontFactory.getFont(FontFactory.HELVETICA, 14));
			 section9.add(someSectionText9);
			 try {
					document.add(section9);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Section section22 = chapter1.addSection(title22);
			 Paragraph someSectionText10 = new Paragraph(LoginController.userLogedIn.getEmail(),FontFactory.getFont(FontFactory.HELVETICA, 14));
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
			try {
				file = readBytesFromFile("C:\\Users\\HP\\Downloads\\Confirmation-File.pdf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return file;
	    	
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		  jndiname="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote";
		 jndiname2="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/CharityService!tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote";
		  jndiname3="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/OrganizationService!tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote";
		 jndiname4="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/MessagesService!tn.esprit.b1.esprit1718b1fundraising.services.MessagesServiceRemote";
		try {
			ctx = new InitialContext();
			chService=(CharityServiceRemote) ctx.lookup(jndiname2);
			 orgService=(OrganizationServiceRemote) ctx.lookup(jndiname3);
          LoginController.userManagment=(UtilisateurServiceRemote) ctx.lookup(jndiname);
          megService=(MessagesServiceRemote) ctx.lookup(jndiname4);
			
          //chProject=new CharityProject("0");
			// org=new Organization();
			 
			// chProjectId=chService.saveNouha(chProject);
			// orgId=orgService.saveNouha(org);
			//orgService.affecterChProjectAOrganizationNouha(chProjectId, orgId);
			
			//LoginController.userManagment.affecterChProjectAUserNouha(chProjectId, LoginController.userLogedIn.getId());
			
			 
		           } catch (NamingException e) {
			
			         e.printStackTrace();
	                    	}
		
		chPofListfound=ChPPrivateListFoundNController.chProject;
		chProjectId=chPofListfound.getIdProject();
		System.out.println("ChPId"+chProjectId+"orgId"+orgId);
	     orgOfListFound=ChPPrivateListFoundNController.org;
	     orgId=orgOfListFound.getId();
	     System.out.println("ChPId"+chProjectId+"orgId"+orgId);
	     if(orgOfListFound.getName()!=null)
	    	 orgName.setText(orgOfListFound.getName());
	     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	     if(orgOfListFound.getFoundingYear()!=null){
	    	 orgYear.getEditor().setText(dateFormat.format(orgOfListFound.getFoundingYear()));
	    		 
	     }
	     if(orgOfListFound.getProgram()!=null){
	    	 
	    	 if(childrenBox.getText().equals(orgOfListFound.getProgram()))childrenBox.setSelected(true);
		    	
		    	if(climateBox.getText().equals(orgOfListFound.getProgram())) climateBox.setSelected(true);
		    	
		    	if(cultureBox.getText().equals(orgOfListFound.getProgram())) cultureBox.setSelected(true);
		    	
		    	if(DemocracyBox.getText().equals(orgOfListFound.getProgram())) DemocracyBox.setSelected(true);
		    	
		    	if(disasterRecoveryBox.getText().equals(orgOfListFound.getProgram())) disasterRecoveryBox.setSelected(true);
		    	
		    	if(economicBox.getText().equals(orgOfListFound.getProgram())) economicBox.setSelected(true);
		    	
		    	if(educationBox.getText().equals(orgOfListFound.getProgram())) educationBox.setSelected(true);
		    	
		    	if(environmentBox.getText().equals(orgOfListFound.getProgram())) environmentBox.setSelected(true);
		    	
		    	if(healthBox.getText().equals(orgOfListFound.getProgram())) healthBox.setSelected(true);
		    	
		    	if(humanitarianAssisBox.getText().equals(orgOfListFound.getProgram())) humanitarianAssisBox.setSelected(true);
		    	
		    	if(humanRightBox.getText().equals(orgOfListFound.getProgram())) humanRightBox.setSelected(true);
		    	
		    	if(hungerBox.getText().equals(orgOfListFound.getProgram())) hungerBox.setSelected(true);
		    	
		    	if(microfinanceBox.getText().equals(orgOfListFound.getProgram())) microfinanceBox.setSelected(true);
		    	
		    	if(TechBox.getText().equals(orgOfListFound.getProgram())) TechBox.setSelected(true);
	     }
	     if(orgOfListFound.getCountry()!=null)
	    	 orgCountry.getEditor().setText(orgOfListFound.getCountry());
	     orgCountry.setValue(orgOfListFound.getCountry());
	     if(orgOfListFound.getLogo()!=null){
	    	Image img=new Image(getClass().getResourceAsStream(orgOfListFound.getLogo()),273,249,false,false);
	    	 orgLogo.setImage(img);
	     }
	     if(chPofListfound.getTitle()!=null)
	    	 chProjectName.setText(chPofListfound.getTitle());
	     if(chPofListfound.getDateDebut()!=null){
	    	 
	    	 startChPDate.getEditor().setText(dateFormat.format(chPofListfound.getDateDebut()));
	    	 
	     }
	     if(chPofListfound.getDateFin()!=null){
	    	 endChPDate.getEditor().setText(dateFormat.format(chPofListfound.getDateFin()));
	    	 
	    	 
	     }
	     if(chPofListfound.getDescription()!=null)
	    	descriptionChP.setText(chPofListfound.getDescription());
	     if(chPofListfound.getPicture()!=null){
	    	 Image img=new Image(getClass().getResourceAsStream(chPofListfound.getPicture()),273,249,false,false);
	    	 chPPicture.setImage(img);
	}
		String[] locales1=Locale.getISOCountries();
		for(String countrylist : locales1){
			Locale obj=new Locale("", countrylist);
			String[] city={obj.getDisplayCountry()};
			for(int x=0;x<city.length;x++){
				cities.add(obj.getDisplayCountry());
			}
		}
		
		
		orgCountry.setItems(cities);
		
		
		
		
	}
	
	 private void setStage(String fxml) {
	        try {
	            //dim overlay on new stage opening
	            Region veil = new Region();
	            veil.setPrefSize(1105, 684);
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
