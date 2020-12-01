package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SignController {

	@FXML
	private TextField userName;

	@FXML
	private TextField password;

	Pane MainPane;

	AddNewUserController addN;

	public SignController(Pane mainPane) {
		MainPane = mainPane;
		addN = new AddNewUserController(MainPane);
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
	public void singInUser(ActionEvent event) {

	}

}
