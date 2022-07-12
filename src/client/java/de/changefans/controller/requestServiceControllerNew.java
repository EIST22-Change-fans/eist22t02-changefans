package de.changefans.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class requestServiceControllerNew {

    @FXML
    private Button AssistanceButton;

    @FXML
    private Button BackToMainButton;

    @FXML
    private ImageView requestServicePicture;
    Image  ImageRequestService = new Image(getClass().getResourceAsStream("servicePhoto.jpg"));

    public void displayRequestImage(){
        requestServicePicture.setImage(ImageRequestService);
    }

    @FXML
    void BackToMainButtonClicked(MouseEvent event) {

    }

    @FXML
    void handlAssistanceButtonClicked(MouseEvent event) {

    }

}