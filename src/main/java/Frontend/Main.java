package Frontend;

import Backend.ChatterBox;
import Backend.Exceptions.DukeException;
import Backend.Storage;
import Backend.Switcher;
import Backend.TaskList;

import Frontend.Components.DialogBox.DukeDialogBox;
import Frontend.Components.DialogBox.UserDialogBox;
import Frontend.Components.Link;
import Frontend.Components.SendButton.SendButton;
import Frontend.Objects.User;

import java.net.URI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 * This class handles all frontend rendering of components.
 * Only functions that deal with the user interface should be included.
 *
 *    With references to:
 *    Title: JavaFX Tutorial
 *    Author: Jeffry Lum, Damith C. Rajapakse
 *    Availability: https://github.com/se-edu/duke/blob/master/tutorials
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private Link link;

    private User user = new User();
    private User duke = new User("/images/DaDuke.png");

    private TaskList taskList;
    private Switcher switcher;

    @Override
    public void start( Stage stage ) {
        initialiseBackendComponents();
        initialiseComponents();
        setTheStage(stage);
        resizeComponents(stage);
        setHandlers();
        greet();
    }

    /**
     * Displays the text value of multiple users and handles empty texts.
     * Clears the text field and the text value of the user once display is done.
     */
    private void displayUserInput(){

        if( user.getText().length() > 0 ){
            dialogContainer.getChildren().add(
                    new UserDialogBox( user )
            );

            user.clearText();
        }

        if( duke.getText().length() > 0 ){
            dialogContainer.getChildren().add(
                    new DukeDialogBox( duke )
            );

            duke.clearText();
        }

        userInput.clear();

    }

    /**
     * Greets the user on launch and prints existing tasks (if any)
     */
    private void greet(){

        String str = "";

        str += ChatterBox.sayHello();
        str += "\n";
        str += ChatterBox.sayTaskList( taskList.getList() );

        duke.addText( str );

        dialogContainer.getChildren().add(
                new DukeDialogBox( duke )
        );

        duke.clearText();
    }

    /**
     * Retrieves user input and displays response.
     * Also handles a DukeException but does not handle any logic.
     */
    private void handleUserInput() {

        try {
            String userText = userInput.getText();

            user.addText( userText );
            duke.addText( switcher.res(user.getText()) );

            displayUserInput();

            System.out.println( userText );
            if( userText.equals("exit") ){
                exitDuke();
            }

        } catch ( DukeException e ){

            duke.addText(e.getErrorMsg());
            displayUserInput();

        }

    }

    private void exitDuke(){
        new Thread( () -> {
            try {
                Thread.sleep( 1000 );
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } ).start();
    }

    /**
     * Handles the initialisation of all backend components
     */
    private void initialiseBackendComponents(){
        taskList = new TaskList();

        try {
            taskList.loadTasks( Storage.loadDataFromFile() );
        } catch ( DukeException ignored){

        } finally {

            switcher = new Switcher( taskList );

        }

    }

    /**
     * Handles the initialisation of all rendered components
     */
    private void initialiseComponents(){
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new SendButton("Send");

        link = new Link( "User Guide", "https://github.com/waynewee/duke/wiki/Duke-User-Guide" );

        link.setOnAction( event -> {
            try {
                link.openLink();
            } catch (DukeException e) {
                duke.addText( e.getErrorMsg() );
                displayUserInput();
            }
        } );


        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, link);

        scene = new Scene(mainLayout);


    }

    /**
     * Sets widths and heights for various components.
     * Sets positioning of child elements
     * @param stage top level JavaFX container
     */
    private void resizeComponents(Stage stage){
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setPadding( new Insets(0, 0, 12.5, 0) );

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor( link, 27.5 );

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setTheStage(Stage stage){
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke v1.0 - Type \"help\" for more");
    }

    /**e
     * Binds functions to various components
     */
    private void setHandlers(){
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //handles mouse click
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        //handles key press
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

}
