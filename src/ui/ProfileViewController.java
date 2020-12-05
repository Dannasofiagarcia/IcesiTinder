package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.MasterClass;
import model.User;

public class ProfileViewController {

	private Pane MainPane;
	private MasterClass mc;
	private MainController mainController;

	@FXML
	private Label userNameL;

	@FXML
	private Label emailL;

	@FXML
	private Label fullNameL;

	@FXML
	private Label facultyL;

	@FXML
	private Label genderL;

	@FXML
	private Label sexOrL;

	@FXML
	private Label nameLogged;
	Stage primaryStage;

	private User currentUserToShow;

	public ProfileViewController(Pane mainPane, MasterClass mc, MainController mainController, Stage primaryStage,
			String currentUserName) {
		MainPane = mainPane;
		this.mc = mc;
		this.mainController = mainController;
		this.primaryStage = primaryStage;
		currentUserToShow = mc.findUser(currentUserName);
	}

	@FXML
	void loadEditProfileView(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/deleteOrUpdateUser.fxml"));
		fxmlLoader.setController(new EditViewController(MainPane, mc, mainController, primaryStage));
		Parent editProfileView = fxmlLoader.load();
		MainPane.getChildren().clear();
		MainPane.getChildren().add(editProfileView);
	}

	@FXML
	private Button editButton;

	@FXML
	private Label tittleViewPerfil;

	@FXML
	private Pane paneToDoMatch;

	@FXML
	private Label statusConnectionL;

	@FXML
	private Hyperlink setPropic;

	@FXML
	public void initialize() throws IOException {

		User t = currentUserToShow;

		if (currentUserToShow.getUserName().equals(mc.getCurrentUser().getUserName()) == true) {
			editButton.setDisable(false);
			editButton.setVisible(true);
			tittleViewPerfil.setText("Mi perfil");
			paneToDoMatch.setVisible(false);
			setPropic.setDisable(false);
			setPropic.setVisible(true);

			profileImage.setDisable(false);
			profileImage.setVisible(true);

			if (new File("data/" + mc.getCurrentUser().getUserName() + ".jpg").exists()) {
				Image newPic = new Image(new FileInputStream("data/" + mc.getCurrentUser().getUserName() + ".jpg"));

				profileImage.setImage(newPic);
			} else {
				Image newPic = new Image(new FileInputStream("data/default.jpg"));

				profileImage.setImage(newPic);
			}
		} else {
			editButton.setDisable(true);
			editButton.setVisible(false);
			setPropic.setDisable(true);
			setPropic.setVisible(false);

			tittleViewPerfil.setText("Busqueda de usuario");

			profileImage.setDisable(true);
			profileImage.setVisible(false);
		}

		nameLogged.setText(mc.getCurrentUser().getName());

		userNameL.setText(t.getUserName());
		if (currentUserToShow.getUserName().equals(mc.getCurrentUser().getUserName())) {
			emailL.setText(t.getEmail());
		} else {
			emailL.setText("Privado");

			// emailL.
			emailL.setStyle("-fx-font-weight: bold;");
		}

		fullNameL.setText(t.getName());
		facultyL.setText(t.getFaculty());
		genderL.setText(t.getGender());
		sexOrL.setText(t.getSexualOrientation());

		paneToDoMatch.setVisible(false);

		int tempForce = mc.getRelationForce(t.hashCode());

		if (tempForce != 0) {
			if (currentUserToShow.getUserName().equals(mc.getCurrentUser().getUserName())) {
				paneToDoMatch.setVisible(false);
			} else {
				paneToDoMatch.setVisible(true);

				emailL.setText(currentUserToShow.getEmail());
				if (tempForce == 1) {
					statusConnectionL.setText("SON RE PANAS");
				}

				if (tempForce == 2) {
					statusConnectionL.setText("SON SIMPLES AMIGOS");
				}

				if (tempForce == 3) {
					statusConnectionL.setText("SON SIMPLES CONOCIDOS");
				}

			}
		} else {
			if (!currentUserToShow.getUserName().equals(mc.getCurrentUser().getUserName())) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/miniPaneToDoMatch.fxml"));
				fxmlLoader.setController(new MiniController(paneToDoMatch, MainPane, mc, primaryStage, t.hashCode()));
				Parent doMatch = fxmlLoader.load();
				paneToDoMatch.getChildren().clear();
				paneToDoMatch.getChildren().add(doMatch);

			}

		}

		Circle circle = new Circle();
		circle.setCenterX(miniPicProfile.getFitWidth() / 2);
		circle.setCenterY(miniPicProfile.getFitHeight() / 2);
		circle.setRadius(19.0f);

		miniPicProfile.setClip(circle);

		if (new File("data/" + mc.getCurrentUser().getUserName() + ".jpg").exists()) {
			Image newPic = new Image(new FileInputStream("data/" + mc.getCurrentUser().getUserName() + ".jpg"));

			miniPicProfile.setImage(newPic);
		} else {
			Image newPic = new Image(new FileInputStream("src/view/usuario.png"));

			miniPicProfile.setImage(newPic);
		}

	}

	@FXML
	void returnToMainAppView(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainApp.fxml"));
		fxmlLoader.setController(new MainAppController(MainPane, mc, mainController, primaryStage));
		Parent mainAppView = fxmlLoader.load();
		MainPane.getChildren().clear();
		MainPane.getChildren().add(mainAppView);
	}

	@FXML
	void logOut(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
		fxmlLoader.setController(mainController);
		Parent mainView = fxmlLoader.load();
		MainPane.getChildren().clear();
		MainPane.getChildren().add(mainView);

	}

	@FXML
	void setPropic(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");

		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"));

		File temp = fileChooser.showOpenDialog(mainController.primaryStage);

		if (temp != null) {
			Path origin = FileSystems.getDefault().getPath(temp.getAbsolutePath());
			File theNewImage = new File("data/" + mc.getCurrentUser().getUserName() + ".jpg");

			Path destinationOPath = FileSystems.getDefault().getPath(theNewImage.getAbsolutePath());

			try {
				Files.copy(origin, destinationOPath, StandardCopyOption.REPLACE_EXISTING);

				Image newPic = new Image(new FileInputStream("data/" + mc.getCurrentUser().getUserName() + ".jpg"));

				profileImage.setImage(newPic);
				miniPicProfile.setImage(newPic);
			} catch (Exception e) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Acceso denegado");
				alert.setHeaderText(null);
				alert.setContentText("Occurió algo, tu sistema operativo denegó\nel acceso a el archivo!");

				alert.showAndWait();

			}

		}

	}

	@FXML
	private ImageView profileImage;

	@FXML
	private ImageView miniPicProfile;

}
