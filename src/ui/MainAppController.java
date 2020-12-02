package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.MasterClass;

public class MainAppController {

    @FXML
    private Pane MainPane;

    @FXML
    private Label nameLogged;

    private MasterClass mc;
    MainController mainController;

    public MainAppController(Pane MainPane, MasterClass mc, MainController mainController) {
        this.MainPane = MainPane;
        this.mc = mc;
        this.mainController = mainController;
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
        fxmlLoader.setController(new ProfileViewController(MainPane, mc, mainController));
        Parent profileView = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(profileView);

    }

    @FXML
    public void initialize() {

        nameLogged.setText(mc.getCurrentUser().getName());
        nameLogged.setVisible(true);

    }

}
