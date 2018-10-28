package tn.esprit.b1.esprit1718b1fundraising.app.client.main;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import tn.esprit.b1.esprit1718b1fundraising.app.client.main.LoginController;
import tn.esprit.b1.esprit1718b1fundraising.entities.*;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;
import utils.ConfirmBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class ProfilInvestorController implements Initializable {

	@FXML
	private ImageView imageViewProfile;
    @FXML
    private JFXButton SaveButton;
    @FXML
    private JFXButton btnViewAll;
    @FXML
    private MaterialDesignIconView closeIcon;
    @FXML
    private JFXTextField UserNameTF;
    @FXML
    private JFXTextField mailTF;
    @FXML
    private JFXTextField descriptionTF;
    @FXML
    private JFXTextField addressTF;
    @FXML
    private JFXTextField MoneyTF;
    @FXML
    private JFXTextField LastNameTF;
    @FXML
    private JFXTextField FirstNameTF;
    @FXML
    private JFXDatePicker dtDob;
    @FXML
	private Button mailEDIT;
	@FXML
	private Button bioEDIT;
    boolean isChanged = true;
	InitialContext ctx;
	public static UtilisateurServiceRemote userManagment;
	public static Utilisateur userLogedIn;
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			ctx = new InitialContext();
			Object object = ctx.lookup("esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote");
			
			userManagment = (UtilisateurServiceRemote) object;
		} catch (NamingException e) {
		}
		
    	if (LoginController.userLogedIn.getPicture() == null) {
			File file = new File("buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				imageViewProfile.setImage(image);
			} catch (IOException e) {
			}

		} else {
			try {
				byte[] b = LoginController.userLogedIn.getPicture();
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
				imageViewProfile.setImage(wr);
			} catch (IOException e) {
			}

		}
    }

    @FXML
    private void closeStage(MouseEvent event) {
    	closeIcon.getScene().getWindow().hide();
    }
  
	@FXML
	private void SaveChanges(ActionEvent event) {
		if (isChanged) {	
			LoginController.userLogedIn.setFirstName(FirstNameTF.getText());
			LoginController.userLogedIn.setLastName(LastNameTF.getText());
			((Investor) LoginController.userLogedIn).setDescription(descriptionTF.getText());
			((Investor) LoginController.userLogedIn).setMoney(MoneyTF.getAnchor());
			try {
				((Investor) LoginController.userLogedIn).setMoney(Float.valueOf(MoneyTF.getText()));

			} catch (NumberFormatException E) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Money is NUMERIC only ");
				alert.showAndWait();
				MoneyTF.requestFocus();
				return;
			}
			if (!LoginController.userLogedIn.getLogin().equals(UserNameTF.getText())) {
				if (!userManagment.checkUsernameExistance(UserNameTF.getText())) {
					
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("FUNDRAISING");
					alert.setHeaderText(null);
					alert.setContentText("Login entered exist in our database please try another one");
					alert.showAndWait();
					mailTF.requestFocus();
					return;
				}
			}
			if (!LoginController.userLogedIn.getEmail().equals(mailTF.getText())) {
				if (!userManagment.checkMailExistance(mailTF.getText())) {
					
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("FUNDRAISING");
					alert.setHeaderText(null);
					alert.setContentText("Mail entered exist in our database please try another one");
					alert.showAndWait();
					mailTF.requestFocus();
					return;
				}
			}
			LoginController.userLogedIn.setEmail(mailTF.getText());
			userManagment.update((Utilisateur) LoginController.userLogedIn);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("FUNDRAISING");
			alert.setHeaderText(null);
			alert.setContentText("changes successfully");
			alert.showAndWait();
			mailTF.requestFocus();
			return;
		}
	}

	@FXML
	private void ChangeProfilePIC(MouseEvent event) {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this change ?");

		if (confirm) {
			imageViewProfile.setImage(image);
			LoginController.userLogedIn.setPicture(bFile);
			isChanged = true;
			SaveButton.setVisible(true);
		}

	}
	@FXML
	public void showProfile(MouseEvent event) {
		ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
		showProfileBTN(ae);
	}
	@FXML
	public void showProfileBTN(ActionEvent event) {

			FirstNameTF.setTooltip(new Tooltip("Click To Edit"));
			LastNameTF.setTooltip(new Tooltip("Click To Edit"));
			FirstNameTF.setText(LoginController.userLogedIn.getFirstName());
			LastNameTF.setText(LoginController.userLogedIn.getLastName());
			descriptionTF.setText(((Investor) LoginController.userLogedIn).getDescription());
			addressTF.setText(((Investor) LoginController.userLogedIn).getAddress());
			mailTF.setText(LoginController.userLogedIn.getEmail());
			UserNameTF.setText(LoginController.userLogedIn.getLogin());
			MoneyTF.setText(String.valueOf(((Investor) LoginController.userLogedIn).getMoney()));
			if (LoginController.userLogedIn.getPicture() == null) {
				File file = new File("buttons/PasDePhotoDeProfil.png");
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(file);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageViewProfile.setImage(image);
				} catch (IOException e) {
				}

			} else {
				try {
					byte[] b = LoginController.userLogedIn.getPicture();
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
					imageViewProfile.setImage(wr);
				} catch (IOException e) {
				}
			}
		}
}