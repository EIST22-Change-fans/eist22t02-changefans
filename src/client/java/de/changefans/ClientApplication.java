package de.changefans;

import de.changefans.controller.FeedbackController;
import de.changefans.controller.SafetyInstructionController;
import de.changefans.view.FeedbackScene;
import de.changefans.view.HomeScene;
import de.changefans.view.SafetyInstructionsScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class ClientApplication extends Application {
    private final FeedbackController feedbackController=new FeedbackController();
    private final SafetyInstructionController safetyInstructionController=new SafetyInstructionController();
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        primaryStage.setScene(new HomeScene(this));
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }

    public void showHomeScene() {
        stage.setScene(new HomeScene(this));
    }

    public void showSafetyInstructionsScene() {
        stage.setScene(new SafetyInstructionsScene(safetyInstructionController, this));
    }

    public void showFeedbackScene() throws IOException {

        stage.setScene(new FeedbackScene(feedbackController, this));
    }
    public Stage getStage() {
        return stage;
    }
}

