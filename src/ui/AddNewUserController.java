package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

    public AddNewUserController(Pane mainPane) {
        MainPane = mainPane;
    }

    @FXML
    void DeleterUserData(ActionEvent event) {

    }

}
