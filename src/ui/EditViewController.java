package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MasterClass;
import model.User;

public class EditViewController {

    @FXML
    private TextField emailTF;

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField facultySelected;

    @FXML
    private ToggleGroup sexOrTG;

    @FXML
    private MenuButton faculty;

    @FXML
    private MenuItem ingenieriaMI;

    @FXML
    private MenuItem cieciasAdminMI;

    @FXML
    private MenuItem derechoMI;

    @FXML
    private MenuItem cienciasNatMI;

    @FXML
    private MenuItem cienciasEduMI;

    @FXML
    private MenuItem cienciasSaludMI;

    @FXML
    private RadioButton heteroRB;

    @FXML
    private RadioButton homoRB;

    @FXML
    private Label nameLogged;

    @FXML
    private Pane MainPane;

    private MasterClass mc;
    MainController mainController;
    Stage primaryStage;

    public EditViewController(Pane mainPane, MasterClass mc, MainController mainController, Stage primaryStage) {
        MainPane = mainPane;
        this.mc = mc;
        this.mainController = mainController;
        this.primaryStage = primaryStage;
    }

    @FXML
    void updateProfile(ActionEvent event) {

        User t = mc.getCurrentUser();

        t.setEmail(emailTF.getText());

        t.setName(fullNameTF.getText());

        if (heteroRB.isSelected()) {
            t.setSexualOrientation(1);
        } else {
            t.setSexualOrientation(0);
        }

        t.setFaculty(facultySelected.getText());

        nameLogged.setText(t.getName());

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
    void returnToMainAppView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainApp.fxml"));
        fxmlLoader.setController(new MainAppController(MainPane, mc, mainController, primaryStage));
        Parent mainAppView = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(mainAppView);
    }

    @FXML
    public void initialize() {
        User t = mc.getCurrentUser();

        nameLogged.setText(t.getName());

        emailTF.setPromptText(t.getEmail());
        fullNameTF.setPromptText(t.getName());

        ingenieriaMI.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                facultySelected.setText(MasterClass.FACULTIES[0]);
            }

        });

        cieciasAdminMI.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                facultySelected.setText(MasterClass.FACULTIES[1]);
            }

        });

        cienciasSaludMI.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                facultySelected.setText(MasterClass.FACULTIES[2]);
            }

        });

        derechoMI.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                facultySelected.setText(MasterClass.FACULTIES[3]);
            }

        });

        cienciasNatMI.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                facultySelected.setText(MasterClass.FACULTIES[4]);
            }

        });

        cienciasEduMI.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                facultySelected.setText(MasterClass.FACULTIES[5]);
            }

        });

        if (t.getSexualOrientation() == 1) {
            heteroRB.setSelected(true);
            homoRB.setSelected(false);
        } else {
            heteroRB.setSelected(false);
            homoRB.setSelected(true);
        }

    }
}
