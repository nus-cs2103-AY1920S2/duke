import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Gui ui;

    /**
     * Constructor for the Duke Class.
     */
    public Duke() {
        String filepath = "data/duke.txt";
        ui = new Gui();
        //ui.printStartUp();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    /*public static void main(String[] args) {
        new Duke().run();
    }*/

    /**
     * Runs the code for the Duke program.
     */
    public String getResponse(String in) {
        String response = "";
        Parser parser = new Parser(in);
        if (parser.getCommand().equals("bye")) {
            response = (ui.terminateGui());
            Platform.exit();
        } else if (parser.getCommand().equals("list")) {
            response = (ui.printOutTasks(tasks));
        } else if (parser.getCommand().equals("delete")) {
            try {
                response = ui.printOutDeleted(tasks, parser.getIndex() - 1);
                tasks.removeTask(parser.getIndex() - 1);
                //return toReturn;
            } catch (DukeException e) {
                response = e.toString();
            }
            try {
                storage.saveData(tasks);
            } catch (IOException e) {
                response += ("\nAn error occurred while saving, please try again!\n");
            }
        } else if (parser.getCommand().equals("done")) {
            try {
                tasks.getTask(parser.getIndex() - 1).doTask();
                response = (ui.printOutDoneTask(tasks, parser.getIndex() - 1));
            } catch (DukeException e) {
                response = e.toString();
            }
            try {
                storage.saveData(tasks);
            } catch (IOException e) {
                response += ("\nAn error occurred while saving, please try again!\n");
            }
        } else if (parser.getCommand().equals("find")) {
            try {
                response = (ui.printOutFound(tasks.getMatches(parser.getDescription())));
            } catch (DukeException e) {
                response = e.toString();
            }
        } else {
            try {
                if (!((parser.getCommand().equals("todo")) || parser.getCommand().equals("deadline")
                        || parser.getCommand().equals("event"))) {
                    throw new DukeException("I'm sorry, but I do not know what that means :-(");
                }
                if (parser.getCommand().equals("event")) {
                    tasks.newEvent(parser.getDescription(), parser.getTiming());
                } else if (parser.getCommand().equals("deadline")) {
                    try {
                        tasks.newDeadline(parser.getDescription(), parser.getTiming());
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid date format for deadline used! "
                                + "Please re-try using the date format 'yyyy-mm-dd HHMM'");
                    }
                } else if (parser.getCommand().equals("todo")) {
                    tasks.newToDo(parser.getDescription());
                }

                response = (ui.printOutAdded(tasks));
                try {
                    storage.saveData(tasks);
                } catch (IOException e) {
                    response += ("\nAn error occurred while saving, please try again!\n");
                }
            } catch (DukeException e) {
                response = e.toString();
            }
        }
        return (response);
    }
}
