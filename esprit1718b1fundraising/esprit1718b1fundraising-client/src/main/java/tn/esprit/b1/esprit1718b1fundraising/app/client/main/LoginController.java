package tn.esprit.b1.esprit1718b1fundraising.app.client.main;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.*;

import javax.swing.SwingWorker;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LoginController implements Initializable {
	InitialContext ctx;
	public static UtilisateurServiceRemote userManagment;
	public static Utilisateur userLogedIn;
    @FXML
	private TextField usernameTF;
	@FXML
	private PasswordField passwordTF;
	@FXML
	private StackPane resetPasswordPane;
	@FXML
	private AnchorPane loginAnchor;
	  @FXML
	    private JFXButton btnAccount;
	  @FXML
	    private Pane popUpHolder;
	    @FXML
	    private JFXButton btnLogoff;
	    @FXML
	    private JFXButton btnClose;
	    @FXML
	    private StackPane rootPane;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ctx = new InitialContext();
			Object object = ctx.lookup("esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote");
			
			userManagment = (UtilisateurServiceRemote) object;
		} catch (NamingException e) {
		}
	}

    @FXML
	public void LogIn(ActionEvent event) throws IOException  {
		String login = usernameTF.getText();
		String password = passwordTF.getText();
		if (userManagment.loginUser(login, password)) {
			userLogedIn = userManagment.findByUsername(login);
			int redirect = userManagment.RedirectUser(userLogedIn);
			if (redirect == 1) {
				// Founder
				gotoFounder(event);
			} else if (redirect == 2) {
				// Investor
				gotoInvestor(event);
			} else {
				// Admin
				gotoAdmin(event);
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("FUNDRAISING");
			alert.setHeaderText(null);
			alert.setContentText("Wrong Password or username");

			alert.showAndWait();
			usernameTF.clear();
			passwordTF.clear();
			return ;
		}

	}
    void gotoAdmin(ActionEvent event) throws IOException {
		Parent adminScene = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
		Scene scene = new Scene(adminScene);
		//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setTitle("Fundraising");
		Sc.setOnCloseRequest(e ->{
            e.consume();
            MainGUI.closeProgram(Sc);
           
        });

		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	void gotoFounder(ActionEvent event) throws IOException {

		Parent founderScene = FXMLLoader.load(getClass().getResource("FounderMain.fxml"));
		Scene scene = new Scene(founderScene);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		//scene.getStylesheets().add(getClass().getResource("tableView.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	void gotoInvestor(ActionEvent event) throws IOException {
		Parent investorScene = FXMLLoader.load(getClass().getResource("InvestorMain.fxml"));
		Scene scene = new Scene(investorScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}
	@FXML
	public void registerNewUser(MouseEvent event) throws IOException {
		ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
		gotoRegistration(ae);
	}
	void gotoRegistration(ActionEvent event) throws IOException {
		Parent adminScene = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		Scene scene = new Scene(adminScene);
		//scene.getStylesheets().add(getClass().getResource("Registration.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void resetPassword(MouseEvent event) {
		loginAnchor.setVisible(false);
		Label inform = new Label("To reset your password please type in your email");
		inform.setMinWidth(300);
		inform.setTranslateX(110);
		inform.setTranslateY(-85);
		TextField mailTextField = new TextField();
		mailTextField.setMaxWidth(240);
		mailTextField.setTranslateX(50);
		mailTextField.setTranslateY(80);
		Button reset = new Button("Reset My Password");
		reset.setTranslateX(250);
		reset.setTranslateY(80);
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (mailTextField.getText().trim().isEmpty()) {
					// please type in a valid mail address
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fundrainsing");
					alert.setHeaderText(null);
					alert.setContentText("Please insert an email address");
					alert.showAndWait();

					return;
				}

				if (userManagment.findByEmail(mailTextField.getText().trim()) == null) {
					// this user doesnt exist
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fundrainsing");
					alert.setHeaderText(null);
					alert.setContentText("This address does not exist in our database");
					alert.showAndWait();
					Button createNewAccount = new Button("Create a new account from here");
					createNewAccount.setMaxWidth(240);
					createNewAccount.setTranslateX(50);
					createNewAccount.setTranslateY(120);
					createNewAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
							try {
								gotoRegistration(ae);
							} catch (IOException e) {
							}
						};
					});
					resetPasswordPane.getChildren().add(createNewAccount);
					System.out.println("user doesn't exist");
					return;
				}
				String verificationCode = userManagment.codeGeneration();
				Runnable mailing = new Runnable() {

					@Override
					public void run() {
						System.out.println("verification code " + verificationCode);
						try {

							userManagment.sendMail(mailTextField.getText(),
									"A new code has been generated for your account.\r\n" + 
									"Your code is used on the website and on the Desktop application.\r\n" + 
									"\r\n" + 
									"To reset your password use this code : " + verificationCode, "Fundrainsing account:Password reset");
						} catch (AddressException e) {
							// TODO Auto-generated catch block
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
						}

					}
				};
				new Thread(mailing).start();
				// resetPasswordPane.getChildren().clear();
				Label informVerification = new Label("Please type in your verification code");
				informVerification.setMinWidth(300);
				informVerification.setTranslateX(100);
				informVerification.setTranslateY(120);
				TextField verificationCodeTyped = new TextField();
				verificationCodeTyped.setMaxWidth(100);
				verificationCodeTyped.setTranslateX(10);
				verificationCodeTyped.setTranslateY(160);
				Button verify = new Button("Verify");
				verify.setTranslateX(190);
				verify.setTranslateY(160);
				verify.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (verificationCodeTyped.getText().equals(verificationCode)) {
							resetPasswordPane.getChildren().clear();
							Label informNewPassword = new Label("Please type in your new password");
							informNewPassword.setMinWidth(300);
							informNewPassword.setTranslateX(150);
							informNewPassword.setTranslateY(-85);
							PasswordField newPassword = new PasswordField();
							newPassword.setMaxWidth(100);
							newPassword.setTranslateX(100);
							newPassword.setTranslateY(50);
							PasswordField reNewPassword = new PasswordField();
							reNewPassword.setMaxWidth(100);
							reNewPassword.setTranslateX(100);
							reNewPassword.setTranslateY(100);
							Button saveNewPassword = new Button("Save new password");
							saveNewPassword.setMinWidth(150);
							saveNewPassword.setTranslateX(99);
							saveNewPassword.setTranslateY(160);
							

							saveNewPassword.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									if(!reNewPassword.getText().equals(newPassword.getText()))
									{
										Alert alert = new Alert(Alert.AlertType.ERROR);
										alert.setTitle("Fundrainsing");
										alert.setHeaderText(null);
										alert.setContentText("Passwords does not match");
										alert.showAndWait();
										return;
									}
									if(newPassword.getText().length() < 6)
									{
										Alert alert = new Alert(Alert.AlertType.ERROR);
										alert.setTitle("Fundrainsing");
										alert.setHeaderText(null);
										alert.setContentText("Password must have at least 6 digits");
										alert.showAndWait();
										return;
									}
								Utilisateur u = userManagment.findByEmail(mailTextField.getText().trim());
								u.setPassword(newPassword.getText());
								userManagment.updateUser(u);
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("Fundrainsing");
									alert.setHeaderText(null);
									alert.setContentText("Password changed you can log in with it now");
									alert.showAndWait();
									Stage news=new Stage();
							        
									Parent root;
									try {
										saveNewPassword.getScene().getWindow().hide();

										root = FXMLLoader.load(getClass().getResource("Login.fxml"));
										Scene s=new Scene(root);
								        news.setScene(s);
								        news.show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							        
								}
							});

							resetPasswordPane.getChildren().add(informNewPassword);
							resetPasswordPane.getChildren().add(newPassword);
							resetPasswordPane.getChildren().add(reNewPassword);
							resetPasswordPane.getChildren().add(saveNewPassword);

						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Fundrainsing");
							alert.setHeaderText(null);
							alert.setContentText("Wrong verification Code");
							alert.showAndWait();
						}

					}
				});
				resetPasswordPane.getChildren().add(informVerification);
				resetPasswordPane.getChildren().add(verificationCodeTyped);
				resetPasswordPane.getChildren().add(verify);
			}
		});
		resetPasswordPane.getChildren().add(inform);
		resetPasswordPane.getChildren().add(mailTextField);
		resetPasswordPane.getChildren().add(reset);
		resetPasswordPane.setVisible(true);
	}
	@FXML
    private void returnLoginPage(ActionEvent event) throws IOException {
        btnAccount.getScene().getWindow().hide();
        Stage news=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene s=new Scene(root);
        news.setScene(s);
        news.show();
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void showActions(ActionEvent event) {
        JFXPopup popup=new JFXPopup();
        popup.setPopupContent(popUpHolder);
        popup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -45, 65);
    }
}
