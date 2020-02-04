package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * <h1> Parser</h1>
 * The Parser class handles directly the user's command. After parsing the command, the Parser object will call the
 * relevant methods in response to the command.
 * <p> A Parser object has Storage, Ui, TaskList and Scanner objects as its attribute</p>
 */
public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Scanner sc;

    /**
     * Constructor for the Parser class. Takes in Storage, Ui, TaskList and Scanner as its input.
     * @param storage Storage object specific to this parser
     * @param ui Ui object specific to this parser
     * @param taskList TaskList object specific to this parser
     * @param sc Scanner object specific to this parser
     */
    public Parser(Storage storage, Ui ui, TaskList taskList, Scanner sc) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
        this.sc = sc;
    }

    /**
     * This method makes sense of the user's command and calls the appropriate methods in response.
     * @param command This is the user's command
     * @throws DukeException
     * @throws IOException
     */
    public void parse(String command) throws DukeException, IOException {
        if (command.equals("list")) {
            this.ui.printList();
        } else if (command.equals("done")) {
            int index = sc.nextInt() - 1;
            if (index < 0 || index > this.taskList.size() - 1) {
                throw new DukeException("done");
            } else {
                this.ui.markAsDone(index);
                this.storage.save();
            }
        } else if (command.equals("todo")) {
            String str = sc.nextLine();
            if (str.split("").length > 1) {
                ToDo t = new ToDo(str.trim());
                this.taskList.addTask(t);
                this.storage.save();
                this.ui.printTask(t);
            } else {
                throw new DukeException("todo");
            }
        } else if (command.equals("deadline")) {
            String str = sc.nextLine();
            if (str.split("").length > 1) {
                String[] splitStr = str.split("/by");
                if (splitStr[1].trim().split("\\s+").length > 1) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(splitStr[1].trim(), formatter);
                    DeadLine t = new DeadLine(splitStr[0].trim(), dateTime);
                    this.taskList.addTask(t);
                    this.storage.save();
                    this.ui.printTask(t);
                } else {
                    throw new DukeException("timing");
                }
            } else {
                throw new DukeException("deadline");
            }
        } else if (command.equals("event")) {
            String str = sc.nextLine();
            if (str.split("").length > 1) {
                String[] splitStr = str.split("/at");
                if (splitStr[1].trim().split("\\s+").length > 1) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(splitStr[1].trim(), formatter);
                    Event t = new Event(splitStr[0].trim(), dateTime);
                    this.taskList.addTask(t);
                    this.storage.save();
                    this.ui.printTask(t);
                } else {
                    throw new DukeException("timing");
                }
            } else {
                throw new DukeException("event");
            }
        } else if (command.equals("delete")) {
            int index = sc.nextInt() - 1;
            if (index < 0 || index > taskList.size() - 1) {
                throw new DukeException("delete");
            } else {
                Task t = taskList.get(index);
                taskList.deleteTask(index);
                this.storage.save();
                this.ui.printDeletedTask(t);
            }
        } else {
            throw new DukeException("invalid");
        }
    }
}
