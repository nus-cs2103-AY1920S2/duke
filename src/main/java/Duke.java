import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;


public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    /**
     *Initialises everything needed by Duke.
     *
     * @param filePath path used to access tasks in Hard Disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.getTasksFromFile(filePath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("data/tasks.txt");
    }

    public String getResponse(String input) {
        String output = "";
        try {
            if (input.equalsIgnoreCase("list")) {
                output = tasks.list();
            } else if (input.startsWith("done")) {
                int n = Integer.parseInt(parser.parse(input)[1]);
                output = tasks.done(n);
            } else if (input.startsWith("delete")) {
                int n = Integer.parseInt(parser.parse(input)[1]);
                output = tasks.delete(n);
            } else if (input.startsWith("find")) {
                String str = parser.parse(input)[1];
                output = tasks.find(str);
            } else if(input.equalsIgnoreCase("bye")) {
                output = "Bye! Hope to see you again soon!\n";
                try {
                    storage.addTasksToFile(tasks.tasks);
                } catch (IOException e) {
                    output = output + "Error in saving file.\n";
                }
            } else {
                String[] splitInput = parser.parse(input);
                if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    try {
                        output = tasks.add(splitInput[0], splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException arr) {
                        throw new EmptyTaskException("");
                    }
                } else {
                    throw new InvalidRequestException();
                }
            }
        } catch (EmptyTaskException empty) {
            output = output + empty.toString() + "\n";
        } catch (InvalidRequestException invalid) {
            output = output + invalid.toString() + "\n";
        } catch (DateTimeParseException incorrectFormat) {
            output = output + ui.descriptionError() + "\n";
        }
        return output;
    }
}
