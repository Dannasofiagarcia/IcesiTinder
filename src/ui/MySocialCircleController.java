package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MasterClass;
import model.User;

import java.io.IOException;

public class MySocialCircleController {

	private Stage primaryStage;
	@FXML
	private Pane MainPane;
	private MasterClass mc;
	MainController mainController;

	@FXML
	private TableView<User> friendsTW;

	@FXML
	private TableColumn<User, String> usernameTC;

	@FXML
	private TableColumn<User, String> nameTC;

	@FXML
	private TableColumn<User, String> genderTC;

	@FXML
	private TableColumn<User, String> emailTC;

	@FXML
	private TableColumn<User, String>orientationTC;

	@FXML
	private TableColumn<User, String> facultyTC;

	@FXML
	private Label nameLogged;

	@FXML
	private Hyperlink hyperCerrarSesion;

	@FXML
	void logOut(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainView.fxml"));
		fxmlLoader.setController(this.mainController);
		Parent mainView = (Parent)fxmlLoader.load();
		this.MainPane.getChildren().clear();
		this.MainPane.getChildren().add(mainView);
	}

	public MySocialCircleController(Pane mainPane, MasterClass mc, MainController mainController, Stage primaryStage) {
		MainPane = mainPane;
		this.mc = mc;
		this.mainController = mainController;
		this.primaryStage = primaryStage;
	}

	@FXML
	public void initialize() {
		nameLogged.setText(mc.getCurrentUser().getName());
		initializeTableView();
	}

	public void initializeTableView(){
		ObservableList<User> list = FXCollections.observableArrayList(mc.mySocialCircle());
		friendsTW.setItems(list);
		usernameTC.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
		nameTC.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
		genderTC.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
		emailTC.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		facultyTC.setCellValueFactory(new PropertyValueFactory<User,String>("faculty"));
		orientationTC.setCellValueFactory(new PropertyValueFactory<User,String>("sexualOrientation"));

	}

	@FXML
	void returnToMainAppView(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainApp.fxml"));
		fxmlLoader.setController(new MainAppController(MainPane, mc, mainController, primaryStage));
		Parent mainAppView = fxmlLoader.load();
		MainPane.getChildren().clear();
		MainPane.getChildren().add(mainAppView);
	}

}
