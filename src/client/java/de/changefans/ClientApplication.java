package de.changefans;

import de.changefans.controller.FeedbackController;
import de.changefans.controller.SafetyInstructionController;
import de.changefans.view.FeedbackScene;
import de.changefans.view.HomeScene;
import de.changefans.view.SaftyInstructionScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {
    private final FeedbackController feedbackController=new FeedbackController();
    private final SafetyInstructionController safetyInstructionController=new SafetyInstructionController();
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        primaryStage.setScene(new HomeScene(this));
        primaryStage.show();
    }

public void showHomeScene() {
    stage.setScene(new HomeScene(this));
}
public void showSaftyInstructionScene(){stage.setScene(new SaftyInstructionScene(safetyInstructionController,this));}
public void showFeedbackScene() throws IOException {
        /*
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FeedbackView.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Feedback");
    stage.setScene(scene);

         */
    stage.setScene(new FeedbackScene(feedbackController,this));
}
    public Stage getStage() {
        return stage;
    }
}

