package tn.esprit.b1.esprit1718b1fundraising.app.client.gui;



import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainNouha extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		  Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/b1/esprit1718b1fundraising/app/client/charity/CreateChProjectN.fxml"));
          Scene scene = new Scene(root);
          
          stage.initStyle(StageStyle.TRANSPARENT);
          
          stage.setScene(scene);
          stage.show();
		
	}
   
	 public static void main(String[] args) {
	        launch(args);
	    }
	
}
