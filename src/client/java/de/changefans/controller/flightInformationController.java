package de.changefans.controller;

import de.changefans.ClientApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class flightInformationController {
    private ClientApplication clientApplication;

    public ClientApplication getClientApplication() {
        return clientApplication;
    }

    public void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    @FXML
    private TextArea departureBox;

    @FXML
    private TextArea landingBox;

}
