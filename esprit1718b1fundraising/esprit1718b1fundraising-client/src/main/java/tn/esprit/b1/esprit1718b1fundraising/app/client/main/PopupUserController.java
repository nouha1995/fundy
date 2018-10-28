/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1fundraising.app.client.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b1.esprit1718b1fundraising.entities.*;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurServiceRemote;

/**
 * FXML Controller class
 *
 * @author BenAlayaAchraf
 */
public class PopupUserController implements Initializable {
    @FXML
    private MaterialDesignIconView closeIcon;
	@FXML
	private AnchorPane popupAnchor;
	@FXML
	private ImageView UserSelectedPic;
	@FXML
	private JFXTextField firstName;
	@FXML
	private JFXTextField LastName;
	@FXML
	private JFXTextField mailTf;
	@FXML
	private JFXTextField usernameTF;
	@FXML
	private JFXButton FollowUnfollow;
	@FXML
	private Tab titleTab;
	@FXML
	private AnchorPane InvestorThing;
	@FXML
	private JFXTextField AddressTF;
	@FXML
	private JFXTextField DescriptionTF;
	@FXML
	private AnchorPane FounderThing;
	@FXML
	private JFXTextField BioLabel;
	@FXML
	private JFXComboBox<String> FieldsCombo;
	@FXML
	private TableView<Utilisateur> Follow;

	@FXML
	private TableColumn<Utilisateur, byte[]> PictureTV;

	@FXML
	private TableColumn<Utilisateur, String> FirstNameTV;

	@FXML
	private TableColumn<Utilisateur, String> LastNameTV;

	@FXML
	private TableColumn<Utilisateur, String> ActionTV;
	ObservableList<Utilisateur> UsersData = FXCollections.observableArrayList();
	List<Utilisateur> LrecherchFN = new ArrayList<>();
	List<Founder> LrecherchUS = new ArrayList<>();
	@FXML
	private JFXButton FollowersBTN;
	@FXML
	private JFXButton followedBTN;

	static public Utilisateur userChoosen;
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
		Follow.setVisible(false);
		if (userChoosen instanceof Investor) {
			InvestorThing.setVisible(true);
			FounderThing.setVisible(false);
			AddressTF.setText(((Investor) userChoosen).getAddress());
			DescriptionTF.setText(((Investor) userChoosen).getDescription());
			DescriptionTF.setEditable(false);
			AddressTF.setEditable(false);
			followedBTN.setVisible(false);
		}

		if (userChoosen instanceof Founder) {
			FieldsCombo.getItems().add("----------------------");
			InvestorThing.setVisible(false);
			FounderThing.setVisible(true);
			BioLabel.setText(((Founder) userChoosen).getBio());
			BioLabel.setEditable(false);
			for (FounderFields af : ((Founder) userChoosen).getLfields()) {
				FieldsCombo.getItems().add(af.getField().getLibelle());
			}
		}

		//
		firstName.setEditable(false);
		LastName.setEditable(false);
		mailTf.setEditable(false);
		usernameTF.setEditable(false);
		titleTab.setText("More about " + userChoosen.getFirstName() + " " + userChoosen.getLastName());
		firstName.setText(userChoosen.getFirstName());
		LastName.setText(userChoosen.getLastName());
		mailTf.setText(userChoosen.getEmail());
		usernameTF.setText(userChoosen.getLogin());
		if (userChoosen.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				UserSelectedPic.setImage(image);
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
				UserSelectedPic.setImage(wr);
			} catch (IOException e) {
			}
		}
		if (userManagment.getAllFollowed(LoginController.userLogedIn).contains(userChoosen)) {
			FollowUnfollow.setText("UnFollow");
		} else {
			FollowUnfollow.setText("Follow");
		}

	}

	@FXML
	private void FollowUnfollowUser(ActionEvent event) {
		if (FollowUnfollow.getText().equals("Follow")) {
			userManagment.addFollower(LoginController.userLogedIn, userChoosen);
			FollowUnfollow.setText("UnFollow");

		} else {
			userManagment.removeFollower(LoginController.userLogedIn, userChoosen);
			FollowUnfollow.setText("Follow");
		}
	}

	@FXML
	private void ShowFollowers(ActionEvent event) {
		Follow.setVisible(true);
		InvestorThing.setVisible(false);
		FounderThing.setVisible(false);
		LrecherchUS.clear();
		UsersData.clear();
		LrecherchUS = userManagment.getAllFollowed(userChoosen);
		PictureTV.setCellValueFactory(new PropertyValueFactory<Utilisateur, byte[]>("Picture"));
		PictureTV.setCellFactory(new Callback<TableColumn<Utilisateur, byte[]>, TableCell<Utilisateur, byte[]>>() {
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
		ActionTV.setCellValueFactory(new PropertyValueFactory<>("Action"));
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
									if (userManagment.getAllFollowed(userChoosen)
											.contains(getTableView().getItems().get(getIndex()))) {
										btn.setText("UnFollow");
										btn.setOnAction((ActionEvent event) -> {
											Utilisateur person = getTableView().getItems().get(getIndex());
											userManagment.removeFollower(userChoosen,
													person);
											Follow.refresh();
										});
									} else {
										btn.setText("More Information");
									}

									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		ActionTV.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchUS);
		FirstNameTV.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("firstName"));
		LastNameTV.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("lastName"));
		Follow.setItems(UsersData);

	}

	@FXML
	private void showFollowed(ActionEvent event) {
		// eli houma itab3ou fih
		// followUnfollow
		LrecherchFN.clear();
		UsersData.clear();
		Follow.setVisible(true);
		InvestorThing.setVisible(false);
		FounderThing.setVisible(false);

		for (FounderFollowers u : ((Founder) userChoosen).getFollowers()) {
			LrecherchFN.add(u.getUser());
		}
		PictureTV.setCellValueFactory(new PropertyValueFactory<Utilisateur, byte[]>("Picture"));
		PictureTV.setCellFactory(new Callback<TableColumn<Utilisateur, byte[]>, TableCell<Utilisateur, byte[]>>() {
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
		ActionTV.setCellValueFactory(new PropertyValueFactory<>("Action"));
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
									btn.setText("More Information");
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		ActionTV.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchFN);
		FirstNameTV.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("firstName"));
		LastNameTV.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("lastName"));
		Follow.setItems(UsersData);

	}

	@FXML
	public void MoreInformation(MouseEvent event) throws IOException {
		if (PopupUserController.userChoosen == Follow.getSelectionModel().getSelectedItem()) {
			return;
		}
		PopupUserController.userChoosen = Follow.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popupUser.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setOnCloseRequest(e ->{
            e.consume();
            Sc.close();
            userChoosen = null ;
		});
		Sc.setTitle("Fundraising");
		Sc.showAndWait();

	}
    @FXML
    private void closeStage(MouseEvent event) {
    	closeIcon.getScene().getWindow().hide();
    }

}
