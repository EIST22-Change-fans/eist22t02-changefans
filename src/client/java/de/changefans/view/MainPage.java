package de.changefans.view;
import de.changefans.controller.MainPageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPage extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {
      // this.stage = primaryStage;
        //primaryStage.setScene(new HomeScene(this));
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
         primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] arg){launch(arg);}
}
