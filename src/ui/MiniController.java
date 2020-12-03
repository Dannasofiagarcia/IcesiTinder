package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MasterClass;
import model.User;

public class MiniController {

    Pane paneToDoMatch;
    MasterClass mc;
    MainController mainController;
    Pane mainPane;
    Stage primaryStage;
    int hashCodeDestination;
    @FXML
    private Hyperlink generateBestPath;

    @FXML
    private Hyperlink generateDirectConection;

    @FXML
    private Hyperlink confirmDirectConection;

    @FXML
    private MenuItem mejoresPanasCategory;

    @FXML
    private MenuItem simplesPanasCategory;

    @FXML
    private MenuItem soloConocidosCategory;

    @FXML
    private TextField finalCategory;

    public MiniController(Pane paneToDoMatch, Pane mainPane, MasterClass mc, Stage primaryStage,
            int hashCodeDestination) {
        this.paneToDoMatch = paneToDoMatch;
        this.mc = mc;
        paneToDoMatch.setVisible(true);
        this.mainPane = mainPane;
        this.primaryStage = primaryStage;
        this.hashCodeDestination = hashCodeDestination;

    }

    @FXML
    void directMatch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/miniBannerModoLanzado.fxml"));
        fxmlLoader.setController(this);
        Parent doMatch = fxmlLoader.load();
        paneToDoMatch.getChildren().clear();
        paneToDoMatch.getChildren().add(doMatch);
    }

    @FXML
    void indirectMatch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/miniBannerModoTimido.fxml"));
        fxmlLoader.setController(this);
        Parent doMatch = fxmlLoader.load();
        paneToDoMatch.getChildren().clear();
        paneToDoMatch.getChildren().add(doMatch);
    }

    @FXML
    void termsAndConditionsCheckA(ActionEvent event) {

        generateBestPath.setDisable(!generateBestPath.isDisable());

    }

    @FXML
    void termsAndConditionsCheckB(ActionEvent event) {

        generateDirectConection.setDisable(!generateDirectConection.isDisable());

    }

    @FXML
    void cancelAndReturnMainView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainApp.fxml"));
        fxmlLoader.setController(new MainAppController(mainPane, mc, mainController, primaryStage));
        Parent mainAppView = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(mainAppView);
    }

    @FXML
    void generateDirectConection(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bannerDirectConection.fxml"));
        fxmlLoader.setController(this);
        Parent mainAppView = fxmlLoader.load();
        paneToDoMatch.getChildren().clear();
        paneToDoMatch.getChildren().add(mainAppView);

        mejoresPanasCategory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                finalCategory.setText(mejoresPanasCategory.getText());
                confirmDirectConection.setDisable(false);
            }

        });

        simplesPanasCategory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                finalCategory.setText(simplesPanasCategory.getText());
                confirmDirectConection.setDisable(false);
            }

        });

        soloConocidosCategory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                finalCategory.setText(soloConocidosCategory.getText());
                confirmDirectConection.setDisable(false);
            }

        });

    }

    @FXML
    void confirmDirectConection(ActionEvent event) throws IOException {

        int conectionForce = 0;

        if (finalCategory.getText().equals(mejoresPanasCategory.getText())) {
            conectionForce = 1;
        }

        if (finalCategory.getText().equals(simplesPanasCategory.getText())) {
            conectionForce = 2;
        }

        if (finalCategory.getText().equals(soloConocidosCategory.getText())) {
            conectionForce = 3;
        }

        mc.doMatch(mc.db.getNodes().get(hashCodeDestination).getValue().getUserName(), conectionForce);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/confirmMsgDirectConection.fxml"));
        Parent msg = fxmlLoader.load();
        paneToDoMatch.getChildren().clear();
        paneToDoMatch.getChildren().add(msg);

    }

    @FXML
    void generateBestPath(ActionEvent event) {

        List<User> bestPath = mc.getBestSocialPath(mc.db.getNodes().get(hashCodeDestination).getValue().getUserName());

        List<String> path = new ArrayList<String>();

        for (int i = 1; i < bestPath.size(); i++) {
            User temp = bestPath.get(i);

            path.add("Paso " + (i) + ". Contactar a: " + temp.getUserName());
        }

        String[] strings = new String[path.size()];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = path.get(i);
        }

        final ObservableList<String> stringList = FXCollections.<String>observableArrayList(Arrays.asList(strings));
        final ListView<String> listView = new ListView<String>(stringList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.setPrefWidth(357);
        listView.setPrefHeight(335);

        listView.setStyle("-fx-border-color: #27ae60");
        listView.setStyle("-fx-border-width: 3");

        paneToDoMatch.getChildren().clear();
        paneToDoMatch.getChildren().add(listView);

    }

    @FXML
    private Hyperlink indirectMatchHL;
    @FXML
    public void initialize() throws IOException {
    	if(mc.db.getNodes().get(mc.getCurrentUser().hashCode()).getEdges().size()==0) {
			
			indirectMatchHL.setDisable(true);
		}
    }

}
