package tn.esprit.b1.esprit1718b1fundraising.app.client.main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;
import utils.ConfirmBox;

public class ProfilManageController implements Initializable {
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label userLoggedInName;
	@FXML
	private MaterialDesignIconView closeIcon;
	@FXML
	private TableView<Utilisateur> AllUsersTable;

	@FXML
	private TableColumn<Utilisateur, byte[]> picture;

	@FXML
	private TableColumn<Utilisateur, String> firstName;

	@FXML
	private TableColumn<Utilisateur, String> LastName;

	@FXML
	private TableColumn<Utilisateur, String> mail;


	@FXML
	private TableColumn<Utilisateur, String> username;

	@FXML
	private TableColumn<Utilisateur, String> status;

	@FXML
	private JFXComboBox<String> nature;

	/*
	 * @FXML private JFXComboBox<String> byFollowers;
	 */

	@FXML
	private Label sortLabel;

	@FXML
	private AnchorPane AymensPane;
	ObservableList<Utilisateur> UsersData = FXCollections.observableArrayList();
	
///////////manel//////////////////////
@FXML
  private JFXButton vart;
@FXML
private AnchorPane ManelsPane;


@FXML
private AnchorPane ImensPane;
@FXML
private AnchorPane OussamsPane;



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
		nature.setVisible(false);
		// byFollowers.setVisible(false);
		sortLabel.setVisible(false);
		// byFollowers.getItems().addAll("LESS FOLLOWED", "MOST FOLLOWED",
		// "NEITHER");
		nature.getItems().addAll("Founders", "Investors", nature.getPromptText());
		AllUsersTable.setEditable(true);
		AllUsersTable.setVisible(false);

		userLoggedInName
		.setText(LoginController.userLogedIn.getFirstName() + " " + LoginController.userLogedIn.getLastName());
if (LoginController.userLogedIn.getPicture() == null) {
	File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
	BufferedImage bufferedImage;
	try {
		bufferedImage = ImageIO.read(file);
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		profilePicture.setImage(image);
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
				profilePicture.setImage(wr);
			} catch (IOException e) {
			}
		}
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void showSettingsScreen(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void showAboutFanny(ActionEvent event) {
		// TODO Autogenerated
	}

	@FXML
	public void ShowAllUsers(ActionEvent event) {
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Active";
		if(nature.getValue() == null)
		{
			nature.setValue("Founder & Investors");
		}

		if (nature.getValue().equals("Founder & Investors")) {

			DisplayUsersByFilter(popupController.fromWhere, "Founder & Investors");
		}

		if (nature.getValue().equals("Investors")) {
			DisplayUsersByFilter(popupController.fromWhere, "Investors");
		}
		if (nature.getValue().equals("Founders")) {
			DisplayUsersByFilter(popupController.fromWhere, "Founders");
		}
	}

	@FXML
	public void showPopup(MouseEvent event) throws IOException {
		if(popupController.userChoosen == AllUsersTable.getSelectionModel().getSelectedItem())
		{
			return ;
		}
		popupController.userChoosen = AllUsersTable.getSelectionModel().getSelectedItem();
		setStage("popup.fxml");
//		Parent adminScene = FXMLLoader.load(getClass().getResource("popup.fxml"));
//		Scene scene = new Scene(adminScene);
//		Stage Sc = new Stage();
//		Sc.setScene(scene);
//		Sc.setScene(scene);
//		Sc.setTitle("Fundraising");
//		Sc.setOnCloseRequest(e ->{
//            e.consume();
//            MainGUI.closeProgram(Sc);
//            popupController.userChoosen = null ;
//		});
//	s	Sc.showAndWait();
	}

	@FXML
	public void ShowWaitList(ActionEvent event) {
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Wating";
		if(nature.getValue() == null)
		{
			nature.setValue("Founder & Investors");
		}
		if (nature.getValue().equals("Investors")) {
			DisplayUsersByFilter(popupController.fromWhere, "Investors");
		}
		if (nature.getValue().equals("Founder & Investors")) {

			DisplayUsersByFilter(popupController.fromWhere, "Founder & Investors");
		}
		if (nature.getValue().equals("Founders")) {
			DisplayUsersByFilter(popupController.fromWhere, "Founders");
		}
	}

	@FXML
	public void ShowBlockedUsers(ActionEvent event) {
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Blocked";
		if(nature.getValue() == null)
		{
			nature.setValue("Founder & Investors");
		}
		if (nature.getValue().equals("Investors")) {
			DisplayUsersByFilter(popupController.fromWhere, "Investors");
		}
		if (nature.getValue().equals("Founder & Investors")) {

			DisplayUsersByFilter(popupController.fromWhere, "Founder & Investors");
		}
		if (nature.getValue().equals("Founders")) {
			DisplayUsersByFilter(popupController.fromWhere, "Founders");
		}
	}

	public void DisplayUsers(String etat) {
		UsersData.removeAll(UsersData);
		AllUsersTable.refresh();
		picture.setResizable(false);
		picture.setSortable(false);
		picture.setCellValueFactory(new PropertyValueFactory<Utilisateur, byte[]>("picture"));
		picture.setCellFactory(new Callback<TableColumn<Utilisateur, byte[]>, TableCell<Utilisateur, byte[]>>() {
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

		/**************************************************************/
		List<Utilisateur> ListUsers = userManagment.getAllUsers();
		for (Utilisateur u : ListUsers) {
			if (etat.equals("Active")) {
				if (u.isActive() && (!u.isBlocked())) {
					if (u.getId() == LoginController.userLogedIn.getId()) {

					} else {
						UsersData.add(u);
					}

				}

			}

			else if (etat.equals("Wating")) {
				if (!u.isActive()) {
					UsersData.add(u);
				}
			} else if (etat.equals("Blocked")) {
				if (u.isBlocked()) {
					UsersData.add(u);
				}
			}
		}
		firstName.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("lastName"));
		mail.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
		username.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("username"));
		status.setCellValueFactory(Utilisateur -> {
			boolean isActive = Utilisateur.getValue().isActive();
			String isActiveAsString = "";
			if (isActive && !Utilisateur.getValue().isBlocked()) {
				isActiveAsString = "Active";
			} else if (!isActive) {
				isActiveAsString = "Not Active";
			} else if (Utilisateur.getValue().isBlocked()) {
				isActiveAsString = "Blocked";
			}
			return new ReadOnlyStringWrapper(isActiveAsString);
		});
		AllUsersTable.setItems(UsersData);
		AllUsersTable.setVisible(true);

		
		
	}

	public void setTable(List<Utilisateur> Users) {
		AllUsersTable.getItems().setAll(Users);
	}

	@FXML
	public void Disconnect(ActionEvent event) throws IOException {
		LoginController.userLogedIn = null;
		Parent adminScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(adminScene, 600, 600);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setTitle("Fundraising");
		Sc.setOnCloseRequest(e -> {
			e.consume();
			MainGUI.closeProgram(Sc);
		});
		Sc.show();
		final Stage stage = (Stage) AymensPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void filterType(Event event) {
		String filterType = nature.getValue();
		// String filterType2 = byFollowers.getValue();
		if (filterType.equals("Investors")) {
			DisplayUsersByFilter(popupController.fromWhere, "Investors");
		}
		if (filterType.equals("Founder & Investors")) {

			DisplayUsersByFilter(popupController.fromWhere, "Founder & Investors");
		}
		if (filterType.equals("Founders")) {
			DisplayUsersByFilter(popupController.fromWhere, "Founders");
		}

	}

	public void DisplayUsersByFilter(String etat, String t) {
		UsersData.removeAll(UsersData);
		AllUsersTable.refresh();
		picture.setResizable(false);
		picture.setSortable(false);
		picture.setCellValueFactory(new PropertyValueFactory<Utilisateur, byte[]>("picture"));
		picture.setCellFactory(new Callback<TableColumn<Utilisateur, byte[]>, TableCell<Utilisateur, byte[]>>() {
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

		/**************************************************************/
		List<Utilisateur> ListUsers = userManagment.getAllUsers();
		for (Utilisateur u : ListUsers) {
			if (etat.equals("Active")) {
				if (u.isActive() && (!u.isBlocked())) {
					if (u.getId() == LoginController.userLogedIn.getId()) {

					} else {
						if (t.equals("Investors")) {
							if (u instanceof Investor) {
								UsersData.add(u);
							} else {

							}
						}
						if (t.equals("Founders")) {
							if (u instanceof Founder) {
								UsersData.add(u);
							} else {

							}
						}
						if (t.equals("Founder & Investors")) {
							UsersData.add(u);
						}

					}

				}

			}

			else if (etat.equals("Wating")) {
				if (!u.isActive()) {
					if (t.equals("Investors")) {
						if (u instanceof Investor) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("Founders")) {
						if (u instanceof Founder) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("Founder & Investors")) {
						UsersData.add(u);
					}

				}
			} else if (etat.equals("Blocked")) {
				if (u.isBlocked()) {
					if (t.equals("Investors")) {
						if (u instanceof Investor) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("Founders")) {
						if (u instanceof Founder) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("Founder & Investors")) {
						UsersData.add(u);
					}
				}
			}
		}
		firstName.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("lastName"));
		mail.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
		username.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("username"));
		status.setCellValueFactory(Utilisateur -> {
			boolean isActive = Utilisateur.getValue().isActive();
			String isActiveAsString = "";
			if (isActive && !Utilisateur.getValue().isBlocked()) {
				isActiveAsString = "Active";
			} else if (!isActive) {
				isActiveAsString = "Not Active";
			} else if (Utilisateur.getValue().isBlocked()) {
				isActiveAsString = "Blocked";
			}
			return new ReadOnlyStringWrapper(isActiveAsString);
		});
		AllUsersTable.setItems(UsersData);
		AllUsersTable.setVisible(true);

	}
	/*FINAYMEN*/
	@FXML
	public void showProfile(MouseEvent event) {
		// TODO Autogenerated
		System.out.println("showing admin profil");
	}
	@FXML
	public void closeFanny(ActionEvent event) {
		boolean answer = ConfirmBox.display("Exit", "Sure you want to exit ?");
		if (answer) {
			Platform.exit();
		}
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
	@FXML
    private void closeStage(MouseEvent event) {
    	closeIcon.getScene().getWindow().hide();
    }
}
