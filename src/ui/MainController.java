package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private Pane MainPane;

    Controlador1 c1;

    public MainController() {
        c1 = new Controlador1(MainPane);
    }

    @FXML
    void dsjjds(ActionEvent event) {

    }

}
