package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.MasterClass;

public class SignController {

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	Pane MainPane;

	AddNewUserController addN;

	private MasterClass mc;
	private MainController mainController;

	public SignController(Pane mainPane, MasterClass mc, MainController mainController) {
		MainPane = mainPane;
		addN = new AddNewUserController(MainPane, mc, mainController);
		this.mc = mc;
		this.mainController = mainController;
	}

	public void setPane(Pane MainPane) {
		this.MainPane = MainPane;
	}

	@FXML
	public void signUpUser(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addNewUser.fxml"));
		fxmlLoader.setController(addN);
		Parent signUp = fxmlLoader.load();

		MainPane.getChildren().clear();
		MainPane.getChildren().add(signUp);

	}

	@FXML
	public void singInUser(ActionEvent event) throws IOException {
		if (mc.signIn(userName.getText(), password.getText())) {
			logIn();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Congratulationsn't!!!!!!!!");
			alert.setGraphic(new ImageView(this.getClass().getResource("not.gif").toString()));
			alert.setHeaderText(null);
			alert.setContentText("Correo o Contraseña incorrectos");

			alert.showAndWait();

		}
	}

	public void logIn() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainApp.fxml"));
		fxmlLoader.setController(new MainAppController(MainPane, mc, mainController));
		Parent mainApp = fxmlLoader.load();

		MainPane.getChildren().clear();
		MainPane.getChildren().add(mainApp);

	}

}
