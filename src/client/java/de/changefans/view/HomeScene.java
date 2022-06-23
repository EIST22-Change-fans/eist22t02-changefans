package de.changefans.view;
import de.changefans.ClientApplication;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeScene extends Scene{
    public HomeScene(ClientApplication application) {
        super(new VBox(), 640, 500);

        var feedbackButton = new Button("Give Feedback");
        var saftyButton = new Button("Watch flight safety instructions");
        saftyButton.setOnAction(event -> {
            application.showSaftyInstructionScene();
        });
        feedbackButton.setOnAction(event -> {
            try {
                application.showFeedbackScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        var vBox = new VBox(10, feedbackButton,saftyButton);
        vBox.setAlignment(Pos.CENTER);
        setRoot(vBox);
    }
}
