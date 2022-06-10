package de.changefans.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {

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

}
