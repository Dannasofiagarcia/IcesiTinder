package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
	public void initialize() throws IOException {

		User t = currentUserToShow;

		if (currentUserToShow.getUserName().equals(mc.getCurrentUser().getUserName()) == true) {
			editButton.setDisable(false);
			editButton.setVisible(true);
			tittleViewPerfil.setText("Mi perfil");
			paneToDoMatch.setVisible(false);
		} else {
			editButton.setDisable(true);
			editButton.setVisible(false);
			tittleViewPerfil.setText("Busqueda de usuario");
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
			if(!currentUserToShow.getUserName().equals(mc.getCurrentUser().getUserName())) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/miniPaneToDoMatch.fxml"));
				fxmlLoader.setController(new MiniController(paneToDoMatch, MainPane, mc, primaryStage, t.hashCode()));
				Parent doMatch = fxmlLoader.load();
				paneToDoMatch.getChildren().clear();
				paneToDoMatch.getChildren().add(doMatch);
			}
			

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
}
