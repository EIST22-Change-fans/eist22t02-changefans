package de.changefans.view;

import de.changefans.ClientApplication;
import de.changefans.controller.FeedbackController;
import de.changefans.controller.FeedbackControllerNew;
import de.changefans.model.Feedback;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class FeedbackScene extends Scene {
    private final FeedbackController feedbackController;
    private final ClientApplication clientApplication;
    private final FlowPane flowPane;
    Popup popup;

    public FeedbackScene(FeedbackController feedbackController, ClientApplication clientApplication) {
        super(new VBox(), 640, 500);
        this.feedbackController = feedbackController;
        this.clientApplication = clientApplication;

        flowPane = new FlowPane(10, 10);
        var scrollPane = new ScrollPane(flowPane);
        scrollPane.setPrefHeight(450);
        scrollPane.setFitToWidth(true);

        var vBox = new VBox(10, scrollPane, createButtonBox());
        feedbackController.getAllFeedbacks(this::setFeedbacks);
        this.setRoot(vBox);
    }

    public void setFeedbacks(List<Feedback> feedbacks){
        Platform.runLater(() -> {
            var children = flowPane.getChildren();
            children.setAll(feedbacks.stream().map(this::createFeedbackView).toList());
        });
    }

    private Node createFeedbackView(Feedback feedback) {
        var id = new Text("FlightID: "+ feedback.getFlightID());
        id.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.EXTRA_BOLD, 20));

        var spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        var deleteButton = new Button("Show feedback");
        deleteButton.setTextFill(Color.ORANGE);
        deleteButton.setOnAction(event -> {
            try {
                showPopup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        var titleBox = new HBox(id, spacer,deleteButton);


        var vBox = new VBox(10, titleBox);
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(200);
        vBox.setStyle("-fx-background-color:white;");
        vBox.setPadding(new Insets(5));
        return vBox;
    }

    private HBox createButtonBox() {
        var backButton = new Button("Back");
        backButton.setOnAction(event -> clientApplication.showHomeScene());

        var addButton = new Button("Add Feedback");
        addButton.setOnAction(event -> {
            try {
                showPopup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        var refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> feedbackController.getAllFeedbacks(this::setFeedbacks));


        var buttonBox = new HBox(10, backButton, addButton,refreshButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private void showPopup() throws IOException {
        popup = new Popup();
        Feedback feedback=new Feedback();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FeedbackViewNew.fxml"));
        popup.getContent().add((Parent)loader.load());
        ((FeedbackControllerNew)loader.getController()).setFeedbackController(feedbackController);
        ((FeedbackControllerNew)loader.getController()).setFeedback(feedback);
        ((FeedbackControllerNew)loader.getController()).setFeedbackScene(this);

        popup.show(clientApplication.getStage());
        popup.centerOnScreen();
    }

    public void hidePopup(){
        popup.hide();
    }

    public void showPopup2(Feedback feedback){
        var popup = new Popup();


        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> popup.hide());

        Text text= new Text();
        text.setText(feedback.getReward());

        var hBox = new HBox(10, cancelButton);
        hBox.setAlignment(Pos.CENTER);

        var vBox = new VBox(10,text,hBox);
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
}
