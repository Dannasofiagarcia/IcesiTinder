package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import model.MasterClass;

public class EditViewController {

    @FXML
    private TextField emailTF;

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField facultyTF;

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
    private Pane MainPane;

    private MasterClass mc;
    MainController mainController;

    public EditViewController(Pane mainPane, MasterClass mc, MainController mainController) {
        MainPane = mainPane;
        this.mc = mc;
        this.mainController = mainController;
    }

    @FXML
    void updateProfile(ActionEvent event) {

    }
}
