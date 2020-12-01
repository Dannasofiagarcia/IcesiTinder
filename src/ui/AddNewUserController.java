package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.MasterClass;

public class AddNewUserController {

    @FXML
    private TextField nameUser;

    @FXML
    private TextField hotmail;

    @FXML
    private TextField fullName;

    @FXML
    private MenuButton faculty;

    @FXML
    private MenuItem engineering;

    @FXML
    Pane MainPane;

    private MasterClass mc;

    public AddNewUserController(Pane mainPane, MasterClass mc) {
        MainPane = mainPane;
        this.mc = mc;
    }

    @FXML
    void DeleterUserData(ActionEvent event) {
        System.out.println(engineering.getText());
    }

}
