package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private Pane MainPane;

    SignController signIn;
    AddNewUserController addNU;

    public MainController() {

    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        signIn = new SignController(MainPane);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/signInOrSignUp.fxml"));
        fxmlLoader.setController(signIn);
        Parent signIn = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(signIn);
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

        addNU = new AddNewUserController(MainPane);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addNewUser.fxml"));
        fxmlLoader.setController(addNU);
        Parent signUp = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(signUp);
    }

}
