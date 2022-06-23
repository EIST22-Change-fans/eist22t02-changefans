package de.changefans.view;

import de.changefans.ClientApplication;
import de.changefans.controller.SafetyInstructionController;
import de.changefans.model.Feedback;
import de.changefans.model.SafetyInstruction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SafetyInstructionsScene extends Scene {
    private final SafetyInstructionController safetyInstructionController;
    private final ClientApplication clientApplication;
    private SafetyInstruction safetyInstruction;
    private final FlowPane flowPane;
    private boolean viewed = false;

    public SafetyInstructionsScene(SafetyInstructionController safetyInstructionController, ClientApplication clientApplication) {
        super(new VBox(), 640, 500);
        this.safetyInstructionController = safetyInstructionController;
        this.clientApplication = clientApplication;

        flowPane = new FlowPane(10, 10);
        var scrollPane = new ScrollPane(flowPane);
        scrollPane.setPrefHeight(450);
        scrollPane.setFitToWidth(true);

        var vBox = new VBox(10, scrollPane, createButtonBox());
        this.setRoot(vBox);

    }

    private HBox createButtonBox() {
        var backButton = new Button("Back");
        backButton.setOnAction(event -> clientApplication.showHomeScene());

        var detailedButton = new Button("Detailed");
        detailedButton.setOnAction(event -> {
            try {
                showPopup(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        var simpleButton = new Button("Simple");
        simpleButton.setOnAction(event -> {
            try {
                showPopup(false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        var buttonBox = new HBox(10, detailedButton,simpleButton,backButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private void showPopup(boolean detailed) throws FileNotFoundException {
        var popup = new Popup();


        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> popup.hide());

        ImageView imageView = new ImageView();
        //safetyInstructionController.getSafetyInstruction(detailed, this::setSafetyInstruction);
        //imageView.setImage(safetyInstruction.getImage());
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);


        var hBox = new HBox(10, cancelButton);
        hBox.setAlignment(Pos.CENTER);

        var vBox = new VBox(10, imageView,hBox);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        vBox.setPrefWidth(150);
        vBox.setPrefHeight(150);
        vBox.setPadding(new Insets(5));
        popup.getContent().add(vBox);
        popup.show(clientApplication.getStage());
        popup.centerOnScreen();
    }

    public SafetyInstruction getSafetyInstruction() {
        return safetyInstruction;
    }

    public void setSafetyInstruction(SafetyInstruction safetyInstruction) {
        this.safetyInstruction = safetyInstruction;
    }
}
