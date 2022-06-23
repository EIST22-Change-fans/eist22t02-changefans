package de.changefans.view;

import de.changefans.ClientApplication;
import de.changefans.controller.FeedbackController;
import de.changefans.model.Feedback;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class FeedbackScene extends Scene {
    private final FeedbackController feedbackController;
    private final ClientApplication clientApplication;
    private final FlowPane flowPane;

    public FeedbackScene(FeedbackController feedbackController, ClientApplication clientApplication) {
        super(new VBox(), 640, 500);
        this.feedbackController = feedbackController;
        this.clientApplication = clientApplication;

        flowPane = new FlowPane(10, 10);
        var scrollPane = new ScrollPane(flowPane);
        scrollPane.setPrefHeight(450);
        scrollPane.setFitToWidth(true);

        var vBox = new VBox(10, scrollPane, createButtonBox());
        feedbackController.getAllFeedbacks(this::setFeedbacks);
        this.setRoot(vBox);
    }

    public void setFeedbacks(List<Feedback> feedbacks){
        Platform.runLater(() -> {
            var children = flowPane.getChildren();
            children.setAll(feedbacks.stream().map(this::createFeedbackView).toList());
        });
    }

    private Node createFeedbackView(Feedback feedback) {
        var id = new Text("FlightID: "+ feedback.getFlightID());
        id.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.EXTRA_BOLD, 20));

        var spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        var deleteButton = new Button("Show feedback");
        deleteButton.setTextFill(Color.ORANGE);
        deleteButton.setOnAction(event -> showPopup(feedback));


        var titleBox = new HBox(id, spacer,deleteButton);


        var vBox = new VBox(10, titleBox);
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(200);
        vBox.setStyle("-fx-background-color:white;");
        vBox.setPadding(new Insets(5));
        return vBox;
    }

    private HBox createButtonBox() {
        var backButton = new Button("Back");
        backButton.setOnAction(event -> clientApplication.showHomeScene());

        var addButton = new Button("Add Feedback");
        addButton.setOnAction(event -> showPopup(null));

        var refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> feedbackController.getAllFeedbacks(this::setFeedbacks));


        var buttonBox = new HBox(10, backButton, addButton,refreshButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private void showPopup(Feedback feedback) {
        boolean commented=false;
        var popup = new Popup();
        var idTextField = new TextField();
        idTextField.setPromptText("Enter flightID");
        idTextField.setText(feedback == null ? "" : String.valueOf(feedback.getFlightID()));

        var cateringTextField = new TextField();
        cateringTextField.setPromptText("Rate catering");
        cateringTextField.setText(feedback == null ? "" : String.valueOf(feedback.getCateringScore()));

        var entertainmentTextField = new TextField();
        entertainmentTextField.setPromptText("Rate entertainment");
        entertainmentTextField.setText(feedback == null ? "" : String.valueOf(feedback.getEntertainmentScore()));

        var serviceTextField = new TextField();
        serviceTextField.setPromptText("Rate service");
        serviceTextField.setText(feedback == null ? "" : String.valueOf(feedback.getServiceScore()));

        var comfortTextField = new TextField();
        comfortTextField.setPromptText("Rate comfort");
        comfortTextField.setText(feedback == null ? "" : String.valueOf(feedback.getComfortScore()));


        var commentTextArea = new TextArea();
        commentTextArea.setPromptText("Comment");
        commentTextArea.setText(feedback == null ? "" : feedback.getComment());

        var addButton = new Button("Save");
        addButton.setOnAction(event -> {
            var newFeedback = feedback != null ? feedback : new Feedback();
            newFeedback.setFlightID(Integer.parseInt(idTextField.getText()));
            newFeedback.setCateringScore(Integer.parseInt(cateringTextField.getText()));
            newFeedback.setComfortScore(Integer.parseInt(comfortTextField.getText()));
            newFeedback.setEntertainmentScore(Integer.parseInt(entertainmentTextField.getText()));
            newFeedback.setServiceScore(Integer.parseInt(serviceTextField.getText()));
            newFeedback.setComment(commentTextArea.getText());
            if (feedback == null) {
                feedbackController.addFeedback(newFeedback, this::setFeedbacks);
            }
            showPopup2(newFeedback);
            popup.hide();
        });

        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> popup.hide());


        var hBox = new HBox(10, addButton, cancelButton);
        hBox.setAlignment(Pos.CENTER);

        var vBox = new VBox(10, idTextField,serviceTextField,cateringTextField,comfortTextField,entertainmentTextField,commentTextArea, hBox);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(200);
        vBox.setPadding(new Insets(5));
        popup.getContent().add(vBox);
        popup.show(clientApplication.getStage());
        popup.centerOnScreen();
    }

    private void showPopup2(Feedback feedback){
        var popup = new Popup();


        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> popup.hide());

        Text text= new Text();
        text.setText(reward(feedback));

        //InputStream stream;
        /*

        if(detailed){
            stream = new FileInputStream("C:\\Users\\User\\EIST\\eist22t02-changefans\\src\\server\\resources\\SafetyInstructions\\detailedSafetyInstructions.jpg");
        }else {
            stream = new FileInputStream("C:\\Users\\User\\EIST\\eist22t02-changefans\\src\\server\\resources\\SafetyInstructions\\simpleSafetyInstructions.jpg");
        }


        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);

         */



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

    /*
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

     */


    public String reward(Feedback feedback){
        Random random= new Random();

        String comment = feedback.getComment();
        if (!(comment == null || comment.equals(""))){
            int n = random.nextInt(10, 10+10 * comment.length());
            return "You have received " + n + " miles!";
            //feedback.getPassenger.updateMiles(n);
        }
        else {
            StringBuilder code = new StringBuilder(10);
            for (int i = 0; i<10; i++) {
                code.append((char) random.nextInt(256)); //generate random code
            }
            //feedback.getPassenger().addCouponCode(code.toString()); //add coupon code to passenger's couponsList

            String destination = "";
            //destination = feedback.getPassenger().getFlightList().stream().filter(x -> x.getID() == feedback.getFlightID()).toList().get(0).getArrivalPlace().getName();
            return "You have received a coupon for a free meal at the " + destination + "airport!\n You can find the code in your Coupons' List";
        }
    }

}
