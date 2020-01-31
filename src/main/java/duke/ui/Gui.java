package duke.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Gui extends Application implements Ui {

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
        Application.launch(Gui.class, new String['a']);
    }

    private String lineBreak = "===========================================================\n";

    public void showReply(String reply) {
        System.out.print(formatReply(reply));
    }

    public void showError(String error) {
        System.out.print(formatReply(error));
    }

    public void showGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("\n");
        sb.append(formatReply("Hello! I'm Duke\nGive me a moment while I locate your save file..."));
        System.out.print(sb.toString());
    }

    private String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }

    
}