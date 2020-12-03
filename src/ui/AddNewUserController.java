package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MasterClass;
import model.User;

public class AddNewUserController {

    @FXML
    private TextField nameUser;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField fullNameTF;

    @FXML
    private RadioButton heteroRButton;

    @FXML
    private ToggleGroup sexualOrientation;

    @FXML
    private RadioButton homoRButton, hombreRB, mujerRB;

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
    private Label statusUserName;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField facultySelected;

    @FXML
    Pane MainPane;

    private MasterClass mc;
    MainController mainController;

    Stage primaryStage;

    public AddNewUserController(Pane mainPane, MasterClass mc, MainController mainController, Stage primaryStage) {

        MainPane = mainPane;
        this.mc = mc;
        this.mainController = mainController;
        this.primaryStage = primaryStage;

    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/signInOrSignUp.fxml"));
        fxmlLoader.setController(new SignController(MainPane, mc, mainController, primaryStage));
        Parent signIn = fxmlLoader.load();
        MainPane.getChildren().clear();
        MainPane.getChildren().add(signIn);
    }

    @FXML
    void confirmSignUp(ActionEvent event) throws IOException {

        try {

            if (mc.findUser(nameUser.getText()) != null) {
                throw new Exception("Nombre de usuario no disponible");
            }

            mc.signUp(nameUser.getText(), passwordTF.getText(), fullNameTF.getText(), facultySelected.getText(),
                    emailTF.getText(), (hombreRB.isSelected()) ? 'm' : 'f', (homoRButton.isSelected() ? 0 : 1));

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registro completado");
            alert.setHeaderText(null);
            alert.setContentText("¡Felicitaciones! Ya haces parte de esta gran familia.");

            alert.showAndWait();

            signIn(event);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize() {

        nameUser.setEditable(true);

        nameUser.setOnKeyReleased(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {

                if (mc.findUser(nameUser.getText()) != null) {
                    statusUserName.setText("No disponible");
                    statusUserName.setStyle("-fx-text-fill: red; -fx-font-size: 14;");

                } else {
                    statusUserName.setText("Disponible");
                    statusUserName.setStyle("-fx-text-fill: green; -fx-font-size: 14;");

                }
            }
        });

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

    }
}