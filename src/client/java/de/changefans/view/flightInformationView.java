package de.changefans.view;

import de.changefans.ClientApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class flightInformationView {
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
