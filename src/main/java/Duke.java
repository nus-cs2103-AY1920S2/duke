import javafx.application.Application;
import javafx.scene.control.Label;

import javafx.stage.Stage;

/*
 * Duke
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * <p>The Duke class is the main class of the bot,
 * where the application starts.</p>
 * @author Daniel Alfred Widjaja
 */
public class Duke extends Application {

    private String fileLoc;


    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    @Override
    public void start(Stage stage) {

        fileLoc = "./src/main/data/duke.txt";
        Parser parser = new Parser(fileLoc);
        Handler handler = new Handler(fileLoc);

        Ui ui = new Ui(stage, parser, handler);
        ui.start();

    }

}

