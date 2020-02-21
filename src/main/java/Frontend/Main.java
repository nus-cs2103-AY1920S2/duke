package Frontend;

import Backend.*;
import Backend.Exceptions.DukeException;

import Frontend.Components.*;
import Frontend.Components.DialogBox.DukeDialogBox;
import Frontend.Components.DialogBox.UserDialogBox;
import Frontend.Constants.Config;
import Frontend.Constants.Styles;
import Frontend.Objects.User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    private DukeScrollPane scrollPane;
    private DialogContainer dialogContainer;
    private DukeInput userInput;
    private SendButton sendButton;
    private Scene scene;
    private DukeAnchorPane mainLayout;
    private DukeLink link;

    private User user = new User();
    private User duke = new User( Config.DUKE_IMG_PATH );

    private TaskList taskList;
    private Switcher switcher;
    private Cache cache = new Cache();

    private int userInputHistoryIndex = 0;

    @Override
    public void start( Stage stage ) {
        initBackendComponents();
        initComponents();
        setTheStage(stage);
        resizeStage(stage);
        setHandlers();
        greet();
    }

    private void addUserInputHistoryIndex(){

        if( cache.getUserInputHistorySize() > 0 ){
            String latestCommand = cache.getUserInputHistory( userInputHistoryIndex % cache.getUserInputHistorySize() );
            userInput.setText(latestCommand);
            userInput.positionCaret( latestCommand.length() );

            userInputHistoryIndex++;
        }

    }

    /**
     * Displays the text value of multiple users and handles empty texts.
     * Clears the text field and the text value of the user once display is done.
     */
    private void displayUserInput(){

        if( user.getText().length() > 0 ){
            dialogContainer.addChild(
                    new UserDialogBox( user )
            );

            user.clearText();
        }

        if( duke.getText().length() > 0 ){
            dialogContainer.addChild(
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

        str += DynamicMessenger.sayHello();
        str += "\n";
        str += DynamicMessenger.sayTaskList( taskList.getList() );

        duke.addText( str );

        dialogContainer.addChild(
                new DukeDialogBox( duke )
        );

        duke.clearText();
    }

    private void handleKeyEvent( KeyEvent keyEvent){

        KeyCode keyCode = keyEvent.getCode();

        boolean isKeyUp = keyCode == KeyCode.UP;
        boolean isKeyCtrlC = ( keyCode == KeyCode.C && keyEvent.isControlDown() );
        boolean isKeyCtrlD = ( keyCode == KeyCode.D && keyEvent.isControlDown() );

        if( isKeyUp ){
            addUserInputHistoryIndex();
        } else {
            resetUserInputHistoryIndex();
        }

        if( isKeyCtrlC ){
            userInput.clear();
        }

        if( isKeyCtrlD ){
            exitDuke();
        }

    }

    /**
     * Retrieves user input and displays response.
     * Also handles a DukeException but does not handle any logic.
     */
    private void handleUserInput() {

        try {
            String userText = userInput.getText();

            cache.addUserInput(userText);

            user.addText( userText );

            duke.addText( switcher.res(user.getText()) );

            displayUserInput();

            if( userText.equals( Config.EXIT_CMD ) ){
                exitDuke();
            }

        } catch ( DukeException e ){
            displayError( e );
        }

    }

    private void exitDuke(){
        new Thread( () -> {
            try {
                Thread.sleep( Config.EXIT_DELAY );
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } ).start();
    }

    /**
     * Handles the initialisation of all backend components
     */
    private void initBackendComponents(){
        taskList = new TaskList();

        try {
            taskList.loadTasks( Storage.loadDataFromFile() );
        } catch ( DukeException e ){
            System.out.println("Unable to load file");
        } finally {
            switcher = new Switcher( taskList, cache );
        }

    }

    /**
     * Handles the initialisation of all rendered components
     */
    private void initComponents(){

        dialogContainer = new DialogContainer();
        scrollPane = new DukeScrollPane(dialogContainer);
        userInput = new DukeInput();
        sendButton = new SendButton();
        link = new DukeLink( Config.LINK_TEXT, Config.LINK_URL );

        link.setOnAction( event -> {
            try {
                link.openLink();
            } catch (DukeException e) {
                displayError(e);
            }
        } );

        mainLayout = new DukeAnchorPane(scrollPane, userInput, sendButton, link);
        scene = new Scene(mainLayout);
    }

    private void displayError(DukeException e){
        duke.addText( e.getErrorMsg() );
        displayUserInput();
    }

    private void resetUserInputHistoryIndex(){
        userInputHistoryIndex = 0;
    }

    /**
     * Sets widths and heights for various components.
     * Sets positioning of child elements
     * @param stage top level JavaFX container
     */
    private void resizeStage(Stage stage){
        stage.setResizable(false);
        stage.setMinHeight( Styles.STAGE_MIN_HEIGHT );
        stage.setMinWidth( Styles.STAGE_MIN_WIDTH );
    }

    private void setTheStage(Stage stage){
        stage.setScene(scene);
        stage.show();
        stage.setTitle( Config.APP_TITLE );
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

        userInput.setOnKeyPressed( this::handleKeyEvent );

        //handles press send
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

}
