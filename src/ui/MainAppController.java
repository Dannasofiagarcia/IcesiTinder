package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MasterClass;

public class MainAppController {

	@FXML
	private Pane MainPane;

	@FXML
	private Label nameLogged;

	private MasterClass mc;
	MainController mainController;
	Stage primaryStage;

	public MainAppController(Pane MainPane, MasterClass mc, MainController mainController, Stage primaryStage) {
		this.MainPane = MainPane;
		this.mc = mc;
		this.mainController = mainController;
		this.primaryStage = primaryStage;
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
	void goToProfileView(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/profileView.fxml"));
		fxmlLoader.setController(new ProfileViewController(MainPane, mc, mainController, primaryStage,
				mc.getCurrentUser().getUserName()));
		Parent profileView = fxmlLoader.load();
		MainPane.getChildren().clear();
		MainPane.getChildren().add(profileView);

	}

	@FXML
	private Hyperlink buscarBtn;

	@FXML
	private Hyperlink cancelarBtn;

	@FXML
	private Hyperlink hyperCerrarSesion;

	@FXML
	private Hyperlink ajustesOpt;

	@FXML
	private Button goToProfileViewBtn;

	@FXML
	private Button searchUserOnActionBtn;

	@FXML
	private Button seeMySocialCircleBtn;

	@FXML
	private TextField userNameToSearch;

	@FXML
	void searchUserOnActionBtn(ActionEvent event) throws IOException {

		Stage stageToSearch = new Stage(StageStyle.TRANSPARENT);

		FXMLLoader fxSearch = new FXMLLoader(getClass().getResource("/view/paneToSearch.fxml"));
		fxSearch.setController(this);
		Parent parentToSearch = fxSearch.load();

		Scene sceneToSearch = new Scene(parentToSearch);
		stageToSearch.setScene(sceneToSearch);

		stageToSearch.setAlwaysOnTop(true);

		cancelarBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				hyperCerrarSesion.setDisable(false);
				goToProfileViewBtn.setDisable(false);
				searchUserOnActionBtn.setDisable(false);
				seeMySocialCircleBtn.setDisable(false);
				ajustesOpt.setDisable(false);

				stageToSearch.close();

			}

		});

		buscarBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					searchUserFunction(userNameToSearch.getText(), stageToSearch);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		ajustesOpt.setDisable(true);
		hyperCerrarSesion.setDisable(true);
		goToProfileViewBtn.setDisable(true);
		searchUserOnActionBtn.setDisable(true);
		seeMySocialCircleBtn.setDisable(true);

		stageToSearch.show();

	}

	public void searchUserFunction(String userName, Stage stageToSearch) throws IOException {

		if (mc.findUser(userName) != null) {
			stageToSearch.close();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/profileView.fxml"));
			fxmlLoader.setController(new ProfileViewController(MainPane, mc, mainController, primaryStage, userName));
			Parent profileView = fxmlLoader.load();
			MainPane.getChildren().clear();
			MainPane.getChildren().add(profileView);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Usuario no encontrado");
			alert.setHeaderText(null);
			alert.setContentText("El usuario no se encuentra registrado en el sistema");

			stageToSearch.setAlwaysOnTop(false);
			alert.showAndWait();
			stageToSearch.setAlwaysOnTop(true);
		}

	}

	@FXML
	void seeMySocialCircle(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/mySocialCircle.fxml"));
		fxmlLoader.setController(new MySocialCircleController(MainPane, mc, mainController, primaryStage));
		Parent mainView = fxmlLoader.load();
		this.MainPane.getChildren().clear();
		this.MainPane.getChildren().add(mainView);
	}

	Stage stageToSettings;

	@FXML
	void ajustesOpt(ActionEvent event) throws IOException {

		stageToSettings = new Stage(StageStyle.TRANSPARENT);

		FXMLLoader fxSearch = new FXMLLoader(getClass().getResource("/view/settingsView.fxml"));
		fxSearch.setController(this);
		Parent parentToSearch = fxSearch.load();

		Scene sceneToSearch = new Scene(parentToSearch);
		stageToSettings.setScene(sceneToSearch);

		stageToSettings.setAlwaysOnTop(true);
		modalityAP.getChildren().add(modalityCB);

		cancelarBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				hyperCerrarSesion.setDisable(false);
				goToProfileViewBtn.setDisable(false);
				searchUserOnActionBtn.setDisable(false);
				seeMySocialCircleBtn.setDisable(false);
				// ajustesOpt.setDisable(false);

				stageToSettings.close();

			}

		});

		// ajustesOpt.setDisable(true);
		hyperCerrarSesion.setDisable(true);
		goToProfileViewBtn.setDisable(true);
		searchUserOnActionBtn.setDisable(true);
		seeMySocialCircleBtn.setDisable(true);

		stageToSettings.show();

	}

	@FXML
	void saveSettings(ActionEvent event) {

		if (modalityCB.isSelected()) {
			mc.setDataStructureType(false);
		} else {
			mc.setDataStructureType(true);

		}
		hyperCerrarSesion.setDisable(false);
		goToProfileViewBtn.setDisable(false);
		searchUserOnActionBtn.setDisable(false);
		seeMySocialCircleBtn.setDisable(false);
		stageToSettings.close();

	}

	@FXML
	private AnchorPane modalityAP;

	private CheckBox modalityCB;

	@FXML
	private ImageView miniPicProfile;

	@FXML
	public void initialize() throws FileNotFoundException {

		nameLogged.setText(mc.getCurrentUser().getName());
		nameLogged.setVisible(true);

		modalityCB = new CheckBox();
		modalityCB.setText("Activar soporte con matriz de adyacencia");

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

}
