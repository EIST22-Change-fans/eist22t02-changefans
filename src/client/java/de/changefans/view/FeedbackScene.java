package de.changefans.view;

import de.changefans.ClientApplication;
import de.changefans.controller.FeedbackController;
import de.changefans.model.Feedback;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.util.List;

public class FeedbackScene extends Scene {
    private final FeedbackController feedbackController;
    private final ClientApplication clientApplication;
    private final FlowPane flowPane;

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
        deleteButton.setOnAction(event -> showPopup(feedback));


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
        addButton.setOnAction(event -> showPopup(null));

        var refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> feedbackController.getAllFeedbacks(this::setFeedbacks));


        var buttonBox = new HBox(10, backButton, addButton,refreshButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private void showPopup(Feedback feedback) {
        var popup = new Popup();
        var idTextField = new TextField();
        idTextField.setPromptText("Enter flightID");
        idTextField.setText(feedback == null ? "" : String.valueOf(feedback.getFlightID()));

        var cateringTextField = new TextField();
        cateringTextField.setPromptText("Rate catering");
        cateringTextField.setText(feedback == null ? "" : String.valueOf(feedback.getCateringScore()));

        var entertainmentTextField = new TextField();
        entertainmentTextField.setPromptText("Rate entertainment");
        entertainmentTextField.setText(feedback == null ? "" : String.valueOf(feedback.getEntertainmentScore()));

        var serviceTextField = new TextField();
        serviceTextField.setPromptText("Rate service");
        serviceTextField.setText(feedback == null ? "" : String.valueOf(feedback.getServiceScore()));

        var comfortTextField = new TextField();
        comfortTextField.setPromptText("Rate comfort");
        comfortTextField.setText(feedback == null ? "" : String.valueOf(feedback.getComfortScore()));


        var commentTextArea = new TextArea();
        commentTextArea.setPromptText("Content");
        commentTextArea.setText(feedback == null ? "" : feedback.getComment());

        var addButton = new Button("Save");
        addButton.setOnAction(event -> {
            var newFeedback = feedback != null ? feedback : new Feedback();
            newFeedback.setFlightID(Integer.parseInt(idTextField.getText()));
            newFeedback.setCateringScore(Integer.parseInt(cateringTextField.getText()));
            newFeedback.setComfortScore(Integer.parseInt(comfortTextField.getText()));
            newFeedback.setEntertainmentScore(Integer.parseInt(entertainmentTextField.getText()));
            newFeedback.setServiceScore(Integer.parseInt(serviceTextField.getText()));
            newFeedback.setComment(cateringTextField.getText());
            if (feedback == null) {
                feedbackController.addFeedback(newFeedback, this::setFeedbacks);
            }

            popup.hide();
        });

        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> popup.hide());


        var hBox = new HBox(10, addButton, cancelButton);
        hBox.setAlignment(Pos.CENTER);

        var vBox = new VBox(10, idTextField,serviceTextField,cateringTextField,comfortTextField,entertainmentTextField,commentTextArea, hBox);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(200);
        vBox.setPadding(new Insets(5));
        popup.getContent().add(vBox);
        popup.show(clientApplication.getStage());
        popup.centerOnScreen();
    }

    /*
    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        chknot.setToggleGroup(group);
        chkokey.setToggleGroup(group);
        chkyes.setToggleGroup(group);
    }

    @FXML
    private Button btnsubmit;

    @FXML
    private RadioButton chknot;

    @FXML
    private RadioButton chkokey;

    @FXML
    private RadioButton chkyes;

    @FXML
    private TextArea taSummary;

    @FXML
    void submitClicked(ActionEvent event) {

    }

     */

}
