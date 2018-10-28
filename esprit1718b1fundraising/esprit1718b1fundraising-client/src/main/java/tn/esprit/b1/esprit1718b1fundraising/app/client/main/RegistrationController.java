package tn.esprit.b1.esprit1718b1fundraising.app.client.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1fundraising.entities.Fields;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RegistrationController implements Initializable {
	Utilisateur newUser = new Investor();
	Utilisateur newUser1 = new Founder();

	File file;
	@FXML
	private JFXTextField firstNameRegistrationTF;
	@FXML
	private JFXTextField lastNameRegistrationTF;
	@FXML
	private JFXTextField mailRegistrationTF;
	@FXML
	private JFXTextField usernameRegistrationTF;
	@FXML
	private JFXPasswordField PasswordRegistrationTF;
	@FXML
	private JFXPasswordField rePasswordRegistrationTF;
	@FXML
	private ImageView myImageView;
	@FXML
	private Button mailRegistrationError;
	@FXML
	private Button usernameRegistrationError;
	@FXML
	private AnchorPane firstFrame;
	@FXML
	private AnchorPane secondFrame;
	@FXML
	private AnchorPane registrationFrame;
	@FXML
	private AnchorPane thirdFrameFounder;
	@FXML
	private AnchorPane thirdFrameInvestor;
	@FXML
	private Button Generalinformation;
	@FXML
	private Button youare;
	@FXML
	private Button lastStep;
	@FXML
	private Button InvestorRegistration;
	@FXML
	private Button FounderRegistration;
	@FXML
	private JFXButton stepsButtonRegistration;
	@FXML
	private JFXButton InvestorFinish;
	@FXML
	private JFXButton FounderFinish;
	@FXML
	private ToggleButton developmentField;
	@FXML
	private ToggleButton designField;
	@FXML
	private ToggleButton marketingField;
	@FXML
	private ToggleButton traductionField;
	@FXML
	private ToggleButton testerField;
	@FXML
	private JFXTextArea bioTextArea;
	@FXML
	private JFXTextField addressRegistrationTF;
	@FXML
	private JFXTextField moneyRegistrationTF;
	@FXML
	private JFXTextArea DescriptionRegistrationTA;
	InitialContext ctx;
	public static UtilisateurServiceRemote userManagment;
	public static Utilisateur userLogedIn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ctx = new InitialContext();
			Object object = ctx.lookup("esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote");
			
			userManagment = (UtilisateurServiceRemote) object;
		} catch (NamingException e) {
		}
		// TODO Auto-generated method stub
		FounderFinish.setVisible(false);
		InvestorFinish.setVisible(false);
		mailRegistrationError.setVisible(false);
		usernameRegistrationError.setVisible(false);
		secondFrame.setVisible(false);
		thirdFrameFounder.setVisible(false);
		thirdFrameInvestor.setVisible(false);
		youare.setDisable(true);
		lastStep.setDisable(true);

	}

	// Event Listener on JFXButton.onAction
	@FXML
	public void nextStepRegistration(ActionEvent event) throws IOException {
		if ((firstNameRegistrationTF.getText().trim().isEmpty()) || (lastNameRegistrationTF.getText().trim().isEmpty())
				|| (mailRegistrationTF.getText().trim().isEmpty())
				|| (usernameRegistrationTF.getText().trim().isEmpty())
				|| (PasswordRegistrationTF.getText().trim().isEmpty())
				|| (rePasswordRegistrationTF.getText().trim().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("Empty fields ARE NOT ACCEPTED please fulfill all fields");
			alert.showAndWait();
			return;
		}
		if (!PasswordRegistrationTF.getText().equals(rePasswordRegistrationTF.getText())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("Passwords does not match");
			alert.showAndWait();
			return;
		}

		if (mailRegistrationError.isVisible()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("E-mail already exist");
			alert.showAndWait();
			return;
		}

		if (usernameRegistrationError.isVisible()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("Username already exist");
			alert.showAndWait();
			return;
		}
        try {
            for (int i = 0; i < firstNameRegistrationTF.getText().trim().length(); i++) {
                if ((firstNameRegistrationTF.getText().trim().toUpperCase().charAt(i) > 90 )||(firstNameRegistrationTF.getText().trim().toUpperCase().charAt(i) < 65 )) {
                    throw new NumberFormatException();
                }
            }
            }catch (NumberFormatException E) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Fundraising");
    			alert.setHeaderText(null);
    			alert.setContentText("First Name must Contains only characters");
    			alert.showAndWait();
    			return;
        }
        
        try {
            for (int i = 0; i < lastNameRegistrationTF.getText().trim().length(); i++) {
                if ((lastNameRegistrationTF.getText().trim().toUpperCase().charAt(i) > 90 )||(lastNameRegistrationTF.getText().trim().toUpperCase().charAt(i) < 65 )) {
                    throw new NumberFormatException();
                }
            }
            }catch (NumberFormatException E) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Fundraising");
    			alert.setHeaderText(null);
    			alert.setContentText("Last Name must Contains only characters");
    			alert.showAndWait();
    			return;
        }
        
		if(PasswordRegistrationTF.getText().length() < 6)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("Password must have at least 6 digits");
			alert.showAndWait();
			return;
		}

		newUser.setFirstName(firstNameRegistrationTF.getText());
		newUser.setLastName(lastNameRegistrationTF.getText());
		newUser.setEmail(mailRegistrationTF.getText());
		newUser.setLogin(usernameRegistrationTF.getText());
		newUser.setPassword(PasswordRegistrationTF.getText());
		
		newUser1.setFirstName(firstNameRegistrationTF.getText());
		newUser1.setLastName(lastNameRegistrationTF.getText());
		newUser1.setEmail(mailRegistrationTF.getText());
		newUser1.setLogin(usernameRegistrationTF.getText());
		newUser1.setPassword(PasswordRegistrationTF.getText());
		if (file == null) {
			file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			myImageView.setImage(image);
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}
		newUser.setPicture(bFile);
		newUser1.setPicture(bFile);
		firstFrame.setVisible(false);
		secondFrame.setVisible(true);
		youare.setDisable(false);
		stepsButtonRegistration.setVisible(false);
		FounderFinish.setVisible(false);
		InvestorFinish.setVisible(true);

		InvestorRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				
				//investor = (Investor) newUser;
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameInvestor.setVisible(true);
				stepsButtonRegistration.setVisible(false);
				InvestorRegistration.setVisible(true);
				FounderFinish.setVisible(false);
				InvestorFinish.setText("Finish");
				InvestorFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if(DescriptionRegistrationTA.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fundraising");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Description must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						((Investor) newUser).setDescription(DescriptionRegistrationTA.getText());
						try {
							((Investor) newUser).setMoney(Float.valueOf(moneyRegistrationTF.getText()));

						} catch (NumberFormatException E) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Money is NUMERIC only ");
							alert.showAndWait();
							moneyRegistrationTF.requestFocus();
							return;
						}
				((Investor) newUser).setAddress(addressRegistrationTF.getText());
				//((Investor) newUser).setActive(true);
						userManagment.addUtilisateur(newUser);
						//// mail test ///
						try {

							userManagment.sendMail(mailRegistrationTF.getText(),"welcome to our application", null);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
						}
					/// Fin mail test ///
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					}
				});
	
			}
		});

		FounderRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				//Founder founder = new Founder(newUser);
				List<Fields> Lf = new ArrayList<>();
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameFounder.setVisible(true);
				stepsButtonRegistration.setVisible(false);
				InvestorFinish.setVisible(false);
				FounderFinish.setVisible(true);
				FounderFinish.setText("Finish");
				FounderFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (developmentField.isSelected()) {
							Fields development = userManagment.findFieldsByName("Development");
							Lf.add(development);
						}
						if (designField.isSelected()) {
							Fields design = userManagment.findFieldsByName("design");
							Lf.add(design);
						}
						if (marketingField.isSelected()) {
							Fields marketing = userManagment.findFieldsByName("Marketing");
							Lf.add(marketing);
						}
						if (traductionField.isSelected()) {
							Fields traduction = userManagment.findFieldsByName("Traduction");
							Lf.add(traduction);
						}
						if (testerField.isSelected()) {
							Fields tester = userManagment.findFieldsByName("Testers");
							Lf.add(tester);
						}
						if(bioTextArea.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fundraising");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Bio must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						((Founder) newUser1).setBio(bioTextArea.getText());
						//((Founder) newUser1).setActive(true);
						userManagment.addUtilisateur(newUser1);
						//// mail test ///
						try {

							userManagment.sendMail(mailRegistrationTF.getText(),"welcome to our application", null);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
						}
					/// Fin mail test ///
						for (Fields f : Lf) {
							userManagment.addFields(f,
									userManagment.findByUsername(newUser1.getLogin()));
						}
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}

					}
				});
			}
		});

	}

	@FXML
	public void verifyRegistrationMail(KeyEvent event) {
		Runnable mailVerification = new Runnable() {

			@Override
			public void run() {
				if ((!userManagment.checkMailExistance(mailRegistrationTF.getText()))
						|| (!userManagment.verifyMail(mailRegistrationTF.getText()))) {
					mailRegistrationError.setVisible(true);
				} else {
					mailRegistrationError.setVisible(false);
				}

			}
		};
		new Thread(mailVerification).run();
	}

	@FXML
	public void verifyRegistrationUsername(KeyEvent event) {
		Runnable usernameVerification = new Runnable() {

			@Override
			public void run() {
				if (!userManagment.checkUsernameExistance(usernameRegistrationTF.getText())) {
					usernameRegistrationError.setVisible(true);
				} else {
					usernameRegistrationError.setVisible(false);
				}

			}
		};
		new Thread(usernameVerification).run();
	}

	// Event Listener on JFXButton.onAction
	@FXML
	public void pickPicture(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			myImageView.setImage(image);
		} catch (IOException ex) {
		}
	}

	@FXML
	public void ChooseWhoYouAre(ActionEvent event) {
		thirdFrameFounder.setVisible(false);
		thirdFrameInvestor.setVisible(false);
		secondFrame.setVisible(true);
		lastStep.setDisable(true);
		stepsButtonRegistration.setVisible(false);
		FounderFinish.setVisible(false);
		InvestorFinish.setVisible(false);

		
		InvestorRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				//Investor investor = new Investor(newUser);
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameInvestor.setVisible(true);
				stepsButtonRegistration.setText("Finish");
				stepsButtonRegistration.setVisible(false);
				FounderFinish.setVisible(false);
				InvestorFinish.setVisible(true);
				InvestorFinish.setText("Finish");
				InvestorFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if(DescriptionRegistrationTA.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fundraising");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Description must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						((Investor) newUser).setDescription(DescriptionRegistrationTA.getText());
						try {
							((Investor) newUser).setMoney(Float.valueOf(moneyRegistrationTF.getText()));

						} catch (NumberFormatException E) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Money is NUMERIC only ");
							alert.showAndWait();
							moneyRegistrationTF.requestFocus();
							return;
						}
					((Investor) newUser).setAddress(addressRegistrationTF.getText());
					//((Investor) newUser).setActive(true);
						userManagment.addUtilisateur(newUser);

						//// mail test ///
							try {

								userManagment.sendMail(mailRegistrationTF.getText(),"welcome to our application", "Fundraising account creation");
							} catch (AddressException e) {
								// TODO Auto-generated catch block
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
							}
						/// Fin mail test ///
						
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					}
				});

			}
		});

		FounderRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				//Founder founder = new Founder(newUser);
				List<Fields> Lf = new ArrayList<>();
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameFounder.setVisible(true);
				stepsButtonRegistration.setVisible(false);
				FounderFinish.setVisible(true);
				InvestorFinish.setVisible(false);
				FounderFinish.setText("Finish");
				FounderFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (developmentField.isSelected()) {
							Fields development = userManagment.findFieldsByName("Development");
							Lf.add(development);
						}
						if (designField.isSelected()) {
							Fields design = userManagment.findFieldsByName("Design");
							Lf.add(design);
						}
						if (marketingField.isSelected()) {
							Fields marketing = userManagment.findFieldsByName("Marketing");
							Lf.add(marketing);
						}
						if (traductionField.isSelected()) {
							Fields traduction = userManagment.findFieldsByName("Traduction");
							Lf.add(traduction);
						}
						if (testerField.isSelected()) {
							Fields tester = userManagment.findFieldsByName("Testers");
							Lf.add(tester);
						}
						if(bioTextArea.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fundraising");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Bio must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						((Founder) newUser1).setBio(bioTextArea.getText());
						//((Founder) newUser1).setActive(true);
						userManagment.addUtilisateur(newUser1);
						//// mail test ///
						try {

							userManagment.sendMail(mailRegistrationTF.getText(),"welcome to our application", null);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
						}
					/// Fin mail test ///
						for (Fields f : Lf) {
							userManagment.addFields(f,
									userManagment.findByUsername( newUser1.getLogin()));
						}
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}

					}
				});

			}
		});

	}

	@FXML
	public void reTypeGeneralInformation(ActionEvent event) {
		thirdFrameFounder.setVisible(false);
		thirdFrameInvestor.setVisible(false);
		secondFrame.setVisible(false);
		firstFrame.setVisible(true);
		lastStep.setDisable(true);
		youare.setDisable(true);
		FounderFinish.setVisible(false);
		InvestorFinish.setVisible(false);
		stepsButtonRegistration.setVisible(true);
		stepsButtonRegistration.setText("Next");

	}

	@FXML
	public void goToGeneral(MouseEvent event) {
		if (!Generalinformation.isDisable()) {
			ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
			reTypeGeneralInformation(ae);
		}
	}

	@FXML
	public void goToWhoYouAre(MouseEvent event) {
		if (!youare.isDisable()) {
			ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
			ChooseWhoYouAre(ae);
		}
	}

	@FXML
	public void goBack(ActionEvent event) throws IOException {
		Parent adminScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(adminScene);
		//scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setTitle("Fundraising");
		/*Sc.setOnCloseRequest(e -> {
			e.consume();
			MainGUI.closeProgram(Sc);
		});*/

		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	void FinalAlert() throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Fundraising");
		alert.setHeaderText(null);
		alert.setContentText(
				"THANK you for choosing our application you will get an approval e-mail from our contact service announcing the activation of your account");
		alert.showAndWait();
		Parent adminScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setTitle("Fundraising");

		Sc.show();
		final Stage stage = (Stage) firstFrame.getScene().getWindow();
		stage.close();

	}

}
