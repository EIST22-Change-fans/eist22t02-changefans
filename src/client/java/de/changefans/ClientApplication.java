package de.changefans;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FeedbackView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Feedback");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

