package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField faculty;

    @FXML
    Pane MainPane;

    private MasterClass mc;

    public AddNewUserController(Pane mainPane, MasterClass mc) {
        MainPane = mainPane;
        this.mc = mc;
    }

    @FXML
    void DeleterUserData(ActionEvent event) {

    }

}
