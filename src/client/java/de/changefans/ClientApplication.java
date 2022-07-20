package de.changefans;

import de.changefans.controller.*;
import de.changefans.view.FeedbackScene;
import de.changefans.view.SafetyInstructionsScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ClientApplication extends Application {
    private final FeedbackController feedbackController=new FeedbackController();
    private final SafetyInstructionController safetyInstructionController=new SafetyInstructionController();
    private final MainPageController mainPageController=new MainPageController();
    private Stage stage;
    private Scene main;
    private Scene reqSer;
    private Scene flightInfo;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("MainPage.fxml")));
        Pane pane=loader.load();
        ((MainPageController)loader.getController()).setClientApplication(this);
        main=new Scene(pane,652,445);
        primaryStage.setScene(main);
        primaryStage.show();
        setReqser();
        setFlightinfo();
    }

    private void setReqser() throws IOException {
         FXMLLoader serloader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("RequestService.fxml")));
         Pane pp=serloader.load();
        ((requestServiceControllerNew)serloader.getController()).setClientApplication(this);
        reqSer=new Scene(pp);
    }

    private void setFlightinfo() throws IOException {
        FXMLLoader infoloader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("flightInformation.fxml")));
        Pane pp=infoloader.load();
        ((flightInformationController)infoloader.getController()).setClientApplication(this);
        flightInfo=new Scene(pp);
    }



    public void showHomeScene() {
        stage.setScene(main);
    }

    public void showSafetyInstructionsScene() {
        stage.setScene(new SafetyInstructionsScene(safetyInstructionController, this));
    }

    public void showFeedbackScene() throws IOException {

        stage.setScene(new FeedbackScene(feedbackController, this));
    }

    public void showRequestService() throws IOException {
        stage.setScene(reqSer);
    }

    public void showFlightInformation() throws IOException {
        stage.setScene(flightInfo);
    }
    public Stage getStage() {
        return stage;
    }
}

