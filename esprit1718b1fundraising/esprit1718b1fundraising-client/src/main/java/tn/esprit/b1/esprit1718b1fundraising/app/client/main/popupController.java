package tn.esprit.b1.esprit1718b1fundraising.app.client.main;


import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.FounderFields;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;
import utils.ConfirmBox;

public class popupController implements Initializable {
	@FXML
	private ImageView profileAdmin;
	@FXML
	private JFXButton ChangePic;
	@FXML
	private MaterialDesignIconView closeIcon;
	@FXML
	private JFXTextField firstName;

	@FXML
	private JFXTextField LastName;

	@FXML
	private JFXTextField mailTf;

	@FXML
	private JFXTextField LoginTF;

	@FXML
	private JFXButton disableButton;

	@FXML
	private AnchorPane popupAnchor;

	@FXML
	private Tab titleTab;

	@FXML
	private Label DescriptionTF;

	@FXML
	private Label AddressTF;

	@FXML
	private Label MoneyTF;

	@FXML
	private AnchorPane InvestorThing;
	@FXML
	private AnchorPane FounderThing;

	@FXML
	private Label BioLabel;

	@FXML
	private JFXComboBox<String> FieldsCombo;
	
	@FXML
	private JFXButton FollowersBTN;
	
	@FXML
	private JFXButton ProjectsBTN;
	
	static public Utilisateur userChoosen;
	static public String fromWhere = "";
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
		// the more about section
		if (userChoosen instanceof Investor) {
			InvestorThing.setVisible(true);
			FounderThing.setVisible(false);
			FollowersBTN.setVisible(false);
			ProjectsBTN.setVisible(false);
			MoneyTF.setText(String.valueOf(((Investor) userChoosen).getMoney()));
			AddressTF.setText(((Investor) userChoosen).getAddress());
			DescriptionTF.setText(((Investor) userChoosen).getDescription());
		}

		if (userChoosen instanceof Founder) {
			FollowersBTN.setVisible(true);
			ProjectsBTN.setVisible(true);
			FieldsCombo.getItems().add("----------------------");
			InvestorThing.setVisible(false);
			FounderThing.setVisible(true);
			BioLabel.setText(((Founder) userChoosen).getBio());
			for(FounderFields af:((Founder) userChoosen).getLfields())
			{
				FieldsCombo.getItems().add(af.getField().getLibelle());
			}
		}

		//
		firstName.setEditable(false);
		LastName.setEditable(false);
		mailTf.setEditable(false);
		LoginTF.setEditable(false);
		titleTab.setText("More about " + userChoosen.getFirstName() + " " + userChoosen.getLastName());
		firstName.setText(userChoosen.getFirstName());
		LastName.setText(userChoosen.getLastName());
		mailTf.setText(userChoosen.getEmail());
		LoginTF.setText(userChoosen.getLogin());
		if (userChoosen.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				profileAdmin.setImage(image);
			} catch (IOException e) {
			}

		} else {
			try {
				byte[] b = userChoosen.getPicture();
				BufferedImage imgbf = null;

				imgbf = ImageIO.read(new ByteArrayInputStream(b));
				WritableImage wr = null;
				if (imgbf != null) {
					wr = new WritableImage(imgbf.getWidth(), imgbf.getHeight());
					PixelWriter pw = wr.getPixelWriter();
					for (int x = 0; x < imgbf.getWidth(); x++) {
						for (int y = 0; y < imgbf.getHeight(); y++) {
							pw.setArgb(x, y, imgbf.getRGB(x, y));
						}
					}
				}
				profileAdmin.setImage(wr);
			} catch (IOException e) {
			}
		}
		if (fromWhere.equals("Active")) {
			disableButton.setText("Block this user");
		} else if (fromWhere.equals("Wating")) {
			disableButton.setText("Approve this user");
		} else {
			disableButton.setText("Unblock this user");
		}

	}

	@FXML
	void DisableUsersAccount(ActionEvent event) throws AddressException, MessagingException {
		if (disableButton.getText().equals("Block this user")) {
			userManagment.blockUser(userChoosen);
			Runnable mailing = new Runnable() {

				@Override
				public void run() {
					try {
						userManagment.sendMail(userChoosen.getEmail(),
								"Fundraising is sorry to announce that we blocked your account for the moment",
								"Something went wrong");
					} catch (MessagingException e) {
					}

				}

			};
			new Thread(mailing).start();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("User Blocked");
			alert.showAndWait();
			disableButton.setDisable(true);
		} else if (disableButton.getText().equals("Approve this user")) {
			userManagment.enableUser(userChoosen);
			Runnable mailing = new Runnable() {

				@Override
				public void run() {
					try {
						userManagment.sendMail(userChoosen.getEmail(),
								"Fundraising is so glad to have you again in our community welcome aboard again",
								"Welcome again");
					} catch (MessagingException e) {
					}

				}

			};
			new Thread(mailing).start();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("User Approved");
			alert.showAndWait();
			disableButton.setDisable(true);
		} else {
			userManagment.unblockUser(userChoosen);
			Runnable mailing = new Runnable() {

				@Override
				public void run() {
					try {
						userManagment.sendMail(userChoosen.getEmail(),
								"Fundraising is so glad to have you in our community so please behave and respect other members",
								"Welcome aboard");
					} catch (MessagingException e) {
					}

				}

			};
			new Thread(mailing).start();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("User Unblocked");
			alert.showAndWait();
			disableButton.setDisable(true);
		}

	}

	@FXML
	void changeUsersPicture(ActionEvent event) throws IOException {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			image = SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (IOException ex) {
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this changes ?");

		if (confirm) {
			profileAdmin.setImage(image);
			userChoosen.setPicture(bFile);
			userManagment.updateUser(userChoosen);
			if (LoginController.userLogedIn.getId() == userChoosen.getId()) {
				LoginController.userLogedIn = userChoosen;
			}
		}
	}

	@FXML
	public void UpdateFirstName(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			firstName.setEditable(true);
			firstName.requestFocus();
			// saveFile
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setLogin(LoginTF.getText());
					userChoosen.setEmail(mailTf.getText());
					userManagment.updateUser(userChoosen);
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ProfilManage.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					ProfilManageController controller = loader.getController();
					// controller.DisplayUsers();
				}
				stage.close();
			});

		} else {

			firstName.setEditable(false);
		}

	}

	@FXML
	public void UpdateLastName(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			LastName.setEditable(true);
			LastName.requestFocus();
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setLogin(LoginTF.getText());
					userChoosen.setEmail(mailTf.getText());
					userManagment.updateUser(userChoosen);
					stage.close();
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ProfilManage.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					ProfilManageController controller = loader.getController();
					controller.setTable(userManagment.getAllUsers());

				}

			});
		} else {
			LastName.setEditable(false);
		}
	}

	@FXML
	public void UpdateMail(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			mailTf.setEditable(true);
			mailTf.requestFocus();
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setLogin(LoginTF.getText());
					userChoosen.setEmail(mailTf.getText());
					userManagment.updateUser(userChoosen);
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ProfilManage.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					ProfilManageController controller = loader.getController();
					// controller.DisplayUsers();

				}
				stage.close();
			});
		}

		else {
			mailTf.setEditable(false);
		}
	}

	@FXML
	public void UpdateLogin(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			LoginTF.setEditable(true);
			LoginTF.requestFocus();
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setLogin(LoginTF.getText());
					userChoosen.setEmail(mailTf.getText());
					userManagment.updateUser(userChoosen);
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ProfilManage.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					ProfilManageController controller = loader.getController();
					// controller.DisplayUsers();
				}
				stage.close();
			});
		} else {
			LoginTF.setEditable(false);
		}

	}

	boolean isChanged() {
		if (LoginTF.getText().trim().isEmpty() || (mailTf.getText().trim().isEmpty())
				|| (firstName.getText().trim().isEmpty()) || (LastName.getText().trim().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fundraising");
			alert.setHeaderText(null);
			alert.setContentText("Empty fields are not accepted chnages wont be saved");
			alert.showAndWait();
			return false;
		} else {
			if ((LoginTF.getText().equals(userChoosen.getLogin()))
					|| (mailTf.getText().equals(userChoosen.getEmail()))
					|| (LastName.getText().equals(userChoosen.getLastName()))
					|| (firstName.getText().equals(userChoosen.getFirstName()))) {
				return true;
			}
		}
		return false;
	}

	@FXML
	public void ShowFollowers(ActionEvent event){
		FollowersBTN.setDisable(true);
		
	}
	
	@FXML
	public void ShowProjects(ActionEvent event){
		ProjectsBTN.setDisable(true);
		
	}
	@FXML
    private void closeStage(MouseEvent event) {
    	closeIcon.getScene().getWindow().hide();
    }
}
