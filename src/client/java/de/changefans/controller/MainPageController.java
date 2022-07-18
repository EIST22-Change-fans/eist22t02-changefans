package de.changefans.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainPageController {

    @FXML
    private Button FlightInformation;

    @FXML
    private Button MainPage;

    @FXML
    private Button MainPage1;

    @FXML
    private ImageView MyImageView;
    Image myImage = new Image(getClass().getResourceAsStream("/airplaneMainPagePicture.png"));

    public void displayImage(){
        MyImageView.setImage(myImage);
    }

    @FXML
    private Button RequestService;

    @FXML
    private Button SafetyInstrucionPart;

    @FXML
    void FlightInformationClicked(MouseEvent event) {

    }

    @FXML
    void MainPageClicked(MouseEvent event) {

    }

    @FXML
    void RequestServiceClicked(MouseEvent event) {

    }

    @FXML
    void SafetyInstrucionPartClicked(MouseEvent event) {

    }

}
