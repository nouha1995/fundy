package tn.esprit.b1.esprit1718b1fundraising.app.client.main;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1fundraising.app.client.gui.MainNouhaController;
import tn.esprit.b1.esprit1718b1fundraising.entities.Admin;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Project;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;
import utils.ConfirmBox;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ProfilMainController implements Initializable {


	@FXML
	private ImageView myimage;
	@FXML
	private ImageView magic2;
	@FXML
	private AnchorPane ResearchUserPane;
	boolean isChanged = false;
	@FXML
	private JFXTextField searchTF;
	@FXML
	private TableView<Utilisateur> searchTable;

	@FXML
	private TableColumn<Utilisateur, byte[]> Picture;

	@FXML
	private TableColumn<Utilisateur, String> FirstName;

	@FXML
	private TableColumn<Utilisateur, String> LastName;

	@FXML
	private TableColumn<Utilisateur, String> Action;
    @FXML
    private MaterialDesignIconView closeIcon;
	ObservableList<Utilisateur> UsersData = FXCollections.observableArrayList();
	List<Utilisateur> LrecherchFN = new ArrayList<>();
	List<Utilisateur> Lremove = new ArrayList<>();



	InitialContext ctx;
	public static UtilisateurServiceRemote userManagment;
	public static Utilisateur userLogedIn;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			ctx = new InitialContext();
			Object object = ctx.lookup("esprit1718b1fundraising-ear/esprit1718b1fundraising-service/UtilisateurService!tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote");
			
			userManagment = (UtilisateurServiceRemote) object;
		} catch (NamingException e) {
		}
		searchTable.setVisible(false);
		ResearchUserPane.setVisible(true);
		// aymen//
		if (LoginController.userLogedIn.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				myimage.setImage(image);
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
				//myimage.setImage(wr);
			} catch (IOException e) {
			}
		}
		// endAymen//

	}
	/*+++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    @FXML
    private void closeStage(MouseEvent event) {
    	closeIcon.getScene().getWindow().hide();
    }   
    @FXML
    private void ShowAllUsers(ActionEvent event) {
    }

    @FXML
    private void showArtsDetail(ActionEvent event) throws IOException {

    }

    @FXML
    private void addVisualAction(ActionEvent event) {
    }

    @FXML
    private void btnpicAction(ActionEvent event) {
    	
    }

	@FXML
	public void findUsers(KeyEvent event) throws IOException {
		LrecherchFN.clear();
		Lremove.clear();
		UsersData.clear();
		searchTable.setVisible(true);

		if (searchTF.getText().trim().isEmpty()) {
			searchTable.setVisible(false);
			return;
		}
		if (searchTF.getText().trim().length() < 1) {
			searchTable.setVisible(false);
			return;
		}
		LrecherchFN = userManagment.filterLastNameAndFirstName(searchTF.getText());
		if (LrecherchFN == null || LrecherchFN.equals(UsersData)) {
			return;
		}

		for (Utilisateur user : LrecherchFN) {
			if (user.isBlocked() || (!user.isActive()) || (user.equals(LoginController.userLogedIn))
					|| (user instanceof Admin)) {
				Lremove.add(user);
			}
		}
		LrecherchFN.removeAll(Lremove);
		Picture.setResizable(false);
		Picture.setSortable(false);
		Picture.setCellValueFactory(new PropertyValueFactory<Utilisateur, byte[]>("Picture"));
		Picture.setCellFactory(new Callback<TableColumn<Utilisateur, byte[]>, TableCell<Utilisateur, byte[]>>() {
			@Override
			public TableCell<Utilisateur, byte[]> call(TableColumn<Utilisateur, byte[]> param) {
				TableCell<Utilisateur, byte[]> cell = new TableCell<Utilisateur, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});
		Action.setCellValueFactory(new PropertyValueFactory<>("Action"));
		Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFactory = //
				new Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>>() {
					@Override
					public TableCell call(final TableColumn<Utilisateur, String> param) {
						final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {

							final JFXButton btn = new JFXButton();

							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									if (getTableView().getItems().get(getIndex()) instanceof Investor) {
										btn.setText("More Information");
										btn.setOnAction((ActionEvent event) -> {
										});
									} 
									else {
										if (userManagment.getAllFollowed(LoginController.userLogedIn)
												.contains(getTableView().getItems().get(getIndex()))) {
											btn.setText("UnFollow");
											btn.setOnAction((ActionEvent event) -> {
												Utilisateur person = getTableView().getItems().get(getIndex());
												userManagment
														.removeFollower(LoginController.userLogedIn, person);
												searchTable.refresh();
											});
										} else {
											btn.setText("Follow");
											btn.setOnAction((ActionEvent event) -> {
												Utilisateur person = getTableView().getItems().get(getIndex());
												userManagment.addFollower(LoginController.userLogedIn,
														person);
												searchTable.refresh();
											});
										}
									}
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		Action.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchFN);
		FirstName.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("lastName"));
		searchTable.setItems(UsersData);
		searchTable.setVisible(true);

	}

	@FXML
	public void OpenSearchedProfile(MouseEvent event) throws IOException {
		if (PopupUserController.userChoosen == searchTable.getSelectionModel().getSelectedItem()) {
			return;
		}
		PopupUserController.userChoosen = searchTable.getSelectionModel().getSelectedItem();
		setStage("popupUser.fxml");
	}
    private void setStage(String fxml) {
        try {
            //dim overlay on new stage opening
            Region veil = new Region();
            veil.setPrefSize(1100, 650);
            veil.setStyle("-fx-background-color:rgba(0,0,0,0.3)");
            Stage newStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource(fxml));
            
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.getScene().getRoot().setEffect(new DropShadow());
            newStage.centerOnScreen();
            newStage.show();
        } catch (IOException ex) {
          //  Logger.getLogger(MainNouhaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
