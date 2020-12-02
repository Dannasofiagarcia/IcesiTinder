package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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

    public ProfileViewController(Pane mainPane, MasterClass mc, MainController mainController) {
        MainPane = mainPane;
        this.mc = mc;
        this.mainController = mainController;
    }

    @FXML
    void loadEditProfileView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/deleteOrUpdateUser.fxml"));
        fxmlLoader.setController(new EditViewController(MainPane, mc, mainController));
        Parent editProfileView = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(editProfileView);
    }

    @FXML
    public void initialize() {
        User t = mc.getCurrentUser();

        nameLogged.setText(t.getName());

        userNameL.setText(t.getUserName());
        emailL.setText(t.getEmail());
        fullNameL.setText(t.getName());
        facultyL.setText(t.getFaculty());
        genderL.setText((t.getGender() == 'm') ? "Masculino" : "Femenino");
        sexOrL.setText(t.getSexualOrientation() == 1 ? "Heterosexual" : "Homosexual");

    }

    @FXML
    void returnToMainAppView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainApp.fxml"));
        fxmlLoader.setController(new MainAppController(MainPane, mc, mainController));
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
