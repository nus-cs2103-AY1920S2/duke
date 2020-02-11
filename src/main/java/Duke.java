import javafx.application.Application;
import javafx.scene.control.Label;

import javafx.stage.Stage;


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

        Ui ui = new Ui(stage, fileLoc);
        ui.start();

    }

}

