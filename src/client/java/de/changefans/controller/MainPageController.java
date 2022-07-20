package de.changefans.controller;

import de.changefans.ClientApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class MainPageController {

    private ClientApplication clientApplication;

    public ClientApplication getClientApplication() {
        return clientApplication;
    }

    public void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }



    @FXML
    private Button FlightInformation;

    @FXML
    private Button MainPage;

    @FXML
    private Button MainPage1;

    @FXML
    private ImageView MyImageView;


    @FXML
    private Button RequestService;

    @FXML
    private Button SafetyInstrucionPart;

    @FXML
    void FlightInformationClicked(MouseEvent event) throws IOException {
        clientApplication.showFlightInformation();
    }

    @FXML
    void FeedbackClicked(MouseEvent event) throws IOException {
        clientApplication.showFeedbackScene();
    }

    @FXML
    void RequestServiceClicked(MouseEvent event) throws IOException {
        clientApplication.showRequestService();
    }

    @FXML
    void SafetyInstrucionPartClicked(MouseEvent event) {
        clientApplication.showSafetyInstructionsScene();
    }

}
