package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MasterClass;

public class MainController {

    @FXML
    private Pane MainPane;

    SignController signIn;
    AddNewUserController addNU;
    private MasterClass mc;
    Stage primaryStage;

    public MainController(Stage primaryStage) {
        mc = new MasterClass();
        this.primaryStage = primaryStage;
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        signIn = new SignController(MainPane, mc, this, primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/signInOrSignUp.fxml"));
        fxmlLoader.setController(signIn);
        Parent signIn = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(signIn);
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

        addNU = new AddNewUserController(MainPane, mc, this, primaryStage);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addNewUser.fxml"));
        fxmlLoader.setController(addNU);
        Parent signUp = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(signUp);

    }

}
