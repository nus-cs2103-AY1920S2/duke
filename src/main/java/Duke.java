import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static String CLOSE = "0";
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.intro();
        ui.output(tasks.listToPrint());
        while (true) {
            String userInput = ui.input();
            String dukeReply = tasks.read(userInput);
            if(dukeReply.equals(CLOSE)){
                ui.output("Bye. See you soon! :)");
                break;
            }else{
                ui.output(dukeReply);
                storage.save(tasks.getTaskArr());
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

}

