package de.changefans.controller;

import de.changefans.model.Feedback;
import de.changefans.view.FeedbackScene;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FeedbackControllerNew {
    private Feedback feedback;
    private FeedbackController feedbackController;
    private FeedbackScene feedbackScene;

    public FeedbackScene getFeedbackScene() {
        return feedbackScene;
    }

    public void setFeedbackScene(FeedbackScene feedbackScene) {
        this.feedbackScene = feedbackScene;
    }

    public FeedbackController getFeedbackController() {
        return feedbackController;
    }

    public void setFeedbackController(FeedbackController feedbackController) {
        this.feedbackController = feedbackController;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @FXML
    private ImageView imageOkeyCatering;

    @FXML
    private ImageView imageOkeyComfort;

    @FXML
    private ImageView imageOkeyEntertainment;

    @FXML
    private ImageView imageOkeyFlight;

    @FXML
    private ImageView imageOkeyService;

    @FXML
    private ImageView imagePerfectCatering;

    @FXML
    private ImageView imagePerfectComfort;

    @FXML
    private ImageView imagePerfectEntertainment;

    @FXML
    private ImageView imagePerfectFlight;

    @FXML
    private ImageView imagePerfectService;

    @FXML
    private ImageView imageUnhappyCatering;

    @FXML
    private ImageView imageUnhappyComfort;

    @FXML
    private ImageView imageUnhappyFlight;

    @FXML
    private ImageView imageUnhappyService;

    @FXML
    private ImageView imageUnhappyentertainment;

    @FXML
    private TextArea taSummary;

    @FXML
    void handleImageOkeyServiceClicked(MouseEvent event) {
        feedback.setServiceScore(3);

    }

    @FXML
    void handleImageUnhappyComfortClicked(MouseEvent event) {
        feedback.setCateringScore(1);
    }

    @FXML
    void handleSubmitClicked(MouseEvent event) {
        feedbackController.addFeedback(feedback, feedbackScene::setFeedbacks);
        feedback.reward();
        feedbackScene.showPopup2(feedback);
        feedbackScene.hidePopup();

    }

    @FXML
    void handleUnhappyServiceClicked(MouseEvent event) {
        feedback.setServiceScore(1);
    }

    @FXML
    void handleimageOkeyCatering(MouseEvent event) {
        feedback.setCateringScore(3);
    }

    @FXML
    void handleimageOkeyComfortClicked(MouseEvent event) {
        feedback.setComfortScore(3);
    }

    @FXML
    void handleimageOkeyEntertainment(MouseEvent event) {
        feedback.setEntertainmentScore(3);
    }

    @FXML
    void handleimagePerfectCatering(MouseEvent event) {
        feedback.setCateringScore(5);
    }

    @FXML
    void handleimagePerfectComfortClicked(MouseEvent event) {
        feedback.setComfortScore(5);
    }

    @FXML
    void handleimagePerfectEntertainment(MouseEvent event) {
        feedback.setEntertainmentScore(5);
    }

    @FXML
    void handleimagePerfectServiceClicked(MouseEvent event) {
        feedback.setServiceScore(5);
    }

    @FXML
    void handleimageUnhappyCatering(MouseEvent event) {
        feedback.setCateringScore(1);
    }

    @FXML
    void handleimageUnhappyentertainment(MouseEvent event) {
        feedback.setEntertainmentScore(1);
    }

    @FXML
    void imageOkeyFlightClicked(MouseEvent event) {
        feedback.setFlightScore(3);
    }

    @FXML
    void imagePerfectFlightClicked(MouseEvent event) {
        feedback.setFlightScore(5);
    }

    @FXML
    void imageUnhappyFlightClicked(MouseEvent event) {
        feedback.setFlightScore(1);
    }

}