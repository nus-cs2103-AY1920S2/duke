import java.util.*;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.time.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke Class.
     *
     * @param filepath Path to file where the data for saved tasks are stored.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the code for the Duke Assistant.
     */
    public void run() {
        ui.printStartUp();
        while(true) {
            String in = ui.newInput();
            Parser parser = new Parser(in);
            if(parser.getCommand().equals("bye")) {
                ui.terminateUi();
                break;
            }

            else if(parser.getCommand().equals("list")) {
                ui.printOutTasks(tasks);
            }

            else if(parser.getCommand().equals("delete")) {
                try {
                    ui.printOutDeleted(tasks, parser.getIndex() - 1);
                    tasks.removeTask(parser.getIndex() - 1);
                }
                catch (DukeException e) {
                    System.out.println(e);
                }
                try {
                    storage.saveData(tasks);
                }
                catch (IOException e){
                    System.out.println("An error occurred while saving, please try again!");
                }
            }

            else if(parser.getCommand().equals("done")) {
                try {
                    tasks.getTask(parser.getIndex() - 1).doTask();
                    ui.printOutDoneTask(tasks, parser.getIndex() - 1);
                }
                catch (DukeException e) {
                    System.out.println(e);
                }
                try {
                    storage.saveData(tasks);
                }
                catch (IOException e) {
                    System.out.println("An error occurred while saving, please try again!");
                }
            }

            else {
                try {
                    if(!((parser.getCommand().equals("todo"))||parser.getCommand().equals("deadline")|| parser.getCommand().equals("event"))) {
                        throw new DukeException("I'm sorry, but I do not know what that means :-(");
                    }
                    if (parser.getCommand().equals("event")) {
                        tasks.newEvent(parser.getDescription(), parser.getTiming());
                    } else if (parser.getCommand().equals("deadline")) {
                       try{
                           tasks.newDeadline(parser.getDescription(), parser.getTiming());
                       }
                       catch (DateTimeParseException e) {
                           throw new DukeException("Invalid date format for deadline used! Please re-try using the date format 'yyyy-mm-dd HHMM'");
                       }
                    } else if (parser.getCommand().equals("todo")) {
                        tasks.newToDo(parser.getDescription());
                    }

                    ui.printOutAdded(tasks);
                    try {
                        storage.saveData(tasks);
                    }
                    catch (IOException e) {
                        System.out.println("An error occurred while saving, please try again!");
                    }
                }
                catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
