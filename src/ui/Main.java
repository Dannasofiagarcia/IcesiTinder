package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Stage wellcomeStage = new Stage(StageStyle.TRANSPARENT);

		wellcomeStage.setOpacity(1);

		Thread thread = new Thread(() -> {

			try {

				Thread.sleep(3000);

				if (wellcomeStage.isShowing()) {

					Platform.runLater(() -> {

						try {

							t(primaryStage, wellcomeStage);

						} catch (IOException e) {

							e.printStackTrace();

						}
					});
				}

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		});

		thread.setDaemon(true);

		FXMLLoader wellcome = new FXMLLoader(getClass().getResource("wellcomeBanner.fxml"));

		Parent wellcomeRoot = wellcome.load();

		Scene test = new Scene(wellcomeRoot);

		wellcomeStage.setScene(test);
		wellcomeStage.setTitle("Bienvenido");
		wellcomeStage.show();
		thread.start();

	}

	public void t(Stage primaryStage, Stage w) throws IOException {
		w.close();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));

		fxmlLoader.setController(new MainController(primaryStage));
		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("IcesiTinder V0.1");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("iconT.png")));
		primaryStage.show();
	}

}