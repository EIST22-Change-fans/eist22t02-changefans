package de.changefans.controller;

import de.changefans.ClientApplication;
import de.changefans.model.Feedback;
import de.changefans.model.RequestService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.io.FileNotFoundException;

public class requestServiceControllerNew {
    RequestServiceController requestServiceController=new RequestServiceController();
    RequestService service=new RequestService();
    private ClientApplication clientApplication;

    public ClientApplication getClientApplication() {
        return clientApplication;
    }


    public void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    @FXML
    private Button AssistanceButton;

    @FXML
    private Button BackToMainButton;

    @FXML
    private ImageView requestServicePicture;
    //Image  ImageRequestService = new Image(getClass().getResourceAsStream("servicePhoto.jpg"));
/*
    public void displayRequestImage(){
        requestServicePicture.setImage(ImageRequestService);
    }
*/
    @FXML
    void BackToMainButtonClicked(MouseEvent event) {
        clientApplication.showHomeScene();
    }

    @FXML
    void handlAssistanceButtonClicked(MouseEvent event) {
        requestServiceController.addService(this::setService);
        showPopup2();
    }

    public void showPopup2(){
        var popup = new Popup();


        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> popup.hide());

        Text text= new Text();
        text.setText(service.getComment());

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


    private void setService(RequestService requestService) {
        this.service=requestService;
    }


}