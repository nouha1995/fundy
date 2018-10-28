package tn.esprit.b1.esprit1718b1fundraising.app.client.charity;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.Article;
import tn.esprit.b1.esprit1718b1fundraising.services.ArticleServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.CharityServiceRemote;
import tn.esprit.b1.esprit1718b1fundraising.services.OrganizationServiceRemote;

public class ChProjectInvestorNController implements Initializable{

	  @FXML
	    private Label chPNameLabel;

	    @FXML
	    private Label chPstartDateLabel;

	    @FXML
	    private Label chPEndDateLabel;

	    @FXML
	    private Label orgNameLabel;

	    @FXML
	    private Label orgYearLabel;

	    @FXML
	    private Label orgProgramLabel;

	    @FXML
	    private ImageView chPPicture;

	    @FXML
	    private TextField comment;

	    @FXML
	    private JFXButton commentBtn;
	    
	    @FXML
	    private StackPane stackPane;

	    @FXML
	    private JFXButton closeBtn;

	    
	    CharityServiceRemote chService;
	  	OrganizationServiceRemote orgService;
	  	ArticleServiceRemote articleService;
	  	Article article;
	  	String jndiname;
	  	List<Article> articlesList=new ArrayList<Article>();
	    Context ctx;
	    int i;

	    @FXML
	    void closeAction(ActionEvent event) {
	    	closeBtn.getScene().getWindow().hide();
	    }
	    @FXML
	    void commentAction(ActionEvent event) {
	    	Date date = new Date();
            article=new Article(comment.getText(), "article", null, null, date, LoginController.userLogedIn, ChPListInvestorNController.chProject);
	    	articleService.save(article);
	    	articlesList=new ArrayList<>();
	    	articlesList=articleService.getArticleListByProjectId(ChPListInvestorNController.chProject.getIdProject());
			System.out.println(articlesList.size()+" seizeListArticle");
			comment.setText("");
			forum(articlesList.size());
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		jndiname="esprit1718b1fundraising-ear/esprit1718b1fundraising-service/ArticleService!tn.esprit.b1.esprit1718b1fundraising.services.ArticleServiceRemote";
		
		try {
			ctx = new InitialContext();
			articleService= (ArticleServiceRemote) ctx.lookup(jndiname);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		articlesList=articleService.getArticleListByProjectId(ChPListInvestorNController.chProject.getIdProject());
		System.out.println(articlesList.size()+" seizeListArticle");
		forum(articlesList.size());
		
		chPNameLabel.setText("Our Project: "+ChPListInvestorNController.chProject.getTitle());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		chPstartDateLabel.setText("Date starts in: "+dateFormat.format(ChPListInvestorNController.chProject.getDateDebut()));
		chPEndDateLabel.setText("Date ends in: "+dateFormat.format(ChPListInvestorNController.chProject.getDateFin()));
		Image img=new Image(getClass().getResourceAsStream(ChPListInvestorNController.chProject.getPicture()),724,231,false,false);
		chPPicture.setImage(img);
		orgNameLabel.setText("Who we are: "+ChPListInvestorNController.org.getName());
		orgProgramLabel.setText("Our Goals: "+ChPListInvestorNController.org.getProgram());
		orgYearLabel.setText("Year Founded: "+dateFormat.format(ChPListInvestorNController.org.getFoundingYear()));
	}
	
	
public void forum(int rows) {
		
		GridPane table = new GridPane();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for(i=0; i<rows; i++){
            for(int j=0;j<1;j++){
            	 AnchorPane articleContent=new AnchorPane();
            	
            	 articleContent.setStyle("-fx-background-color:rgb(255.0, 255.0, 255.0);-fx-background-radius: 4.0;-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);-fx-padding: 0 0 0 0;");
            	 articleContent.setPrefSize(699,117);
            	 
            	 List<Integer> list=findRow(articleContent, j, i);
            	 
        	     HBox hBox=new HBox();
        	     VBox vBox=new VBox();
        	     Label label1=new Label();
        	     Label labelLogin=new Label();
        	     Label labelDate=new Label();
        	     labelDate.prefWidth(104);
        	     labelDate.setStyle("-fx-text-alignment: right;-fx-font-size:16px;");
        	    
        	     Label space=new Label();
        	     space.setText("testtestestetstestetstestetstestetttttttttttttttttttttttttttttttttttttttt");
        	     space.setTextFill(Color.web("#ffffff"));
        	     label1.setText(articlesList.get(i).getBody());
        	     labelLogin.setText(articlesList.get(i).getUtilisateur().getLogin());
        	     labelDate.setText(dateFormat.format(articlesList.get(i).getDate()));
        	    Image img=null;
        		  img=new Image(getClass().getResourceAsStream("../gui/tools/user.png"),40,40,false,false);
        	   ImageView imgV=new ImageView();
        	//HBox.setHgrow(imgV, Priority.ALWAYS);
        	// imgV.prefHeight(100);
        	 //imgV.prefWidth(100);
        	 imgV.setImage(img);
        	
        	 hBox.getChildren().add(imgV);
        	 hBox.getChildren().add(labelLogin);
        	 hBox.getChildren().add(space);
        	 hBox.getChildren().add(labelDate);
        	 hBox.setSpacing(20);
        	 vBox.getChildren().add(hBox);
        	 vBox.getChildren().add(label1);
        
             //articleContent.getStyleClass().add("menus");
             //imgV.fitHeightProperty().bind(chPContent.heightProperty());
           
             articleContent.getChildren().add(vBox);
            //add them to the GridPane
            addF( j, i,articleContent,table); //  (child, columnIndex, rowIndex)
           
            

            // margins are up to your preference
          //  GridPane.setMargin(chPContent2, new Insets(5));
            GridPane.setMargin(articleContent, new Insets(5));
           
         
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


}
