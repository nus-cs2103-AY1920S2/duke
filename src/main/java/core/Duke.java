package core;

import command.Command;
import dukexception.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main driver of the program.
 */
public class Duke extends Application {

    private Ui ui;
    private Common common;
    private Parser parser;

    /**
     * Constructor to initialize essential components of the program.
     */
    public Duke(){
        ui=new Ui();
        try {
            parser = new Parser();
            common = new Common();
        }catch (DukeException e){
            ui.errorLog(e.getMessage());
        }
    }

    /**
     * runs the program.
     */
    public void run(){
        ui.preLog();

        boolean isExit=false;

        while(!isExit){
            String userInput=ui.getInput();
            try {
                Command command = parser.parse(userInput);
                command.execute(common,ui);
                isExit=command.isExit();
            }catch (DukeException e){
                ui.errorLog(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }


}
