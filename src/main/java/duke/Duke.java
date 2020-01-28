package duke;

import duke.data.Calender;
import duke.data.TaskList;
import duke.enums.Command;
import duke.exception.BadDateException;
import duke.exception.BadDescriptionException;
import duke.exception.DukeDescriptionException;
import duke.exception.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;

/**
 * Represents the main working class of DukeProject.
 */
public class Duke {
    /** Primary data structure to store the tasks. */
    private TaskList taskList;
    /** Storage to read/write task list from/into files. */
    private Storage storage;
    /** Data structure to store tasks aligned by dates.*/
    private Calender calender;
    /** Object that handles user interface and communicating with user. */
    private Ui ui;
    /** Primary I/O object used. */
    private Scanner sc;

    /**
     * Execute commands to manipulate task list and calender. The method will create/delete task objects as required
     * and store/remove them from all data structures as needed.
     * @param command Command to be executed.
     * @throws DukeDescriptionException If command requires a description and it not given.
     * @throws BadDescriptionException If description provided does not match the format required by command.
     * @throws BadDateException If date provided does not match format required by command.
     */
    public void readCommand(Command command) throws DukeDescriptionException,
            BadDescriptionException, BadDateException {
        switch (command) {
        case LIST:
            System.out.println("Here are all your tasks:");
            taskList.printTask();
            break;
        case DONE:
            try {
                String strDone = sc.nextLine();
                if (strDone.isEmpty()) {
                    throw new DukeDescriptionException("Empty Description");
                }
                int done = Integer.parseInt(strDone.substring(1)); //there must be a space between command and input
                if (done <= 0 || done > taskList.getSize()) {
                    throw new BadDescriptionException("Description for done cannot be "
                            + done);
                }
                taskList.getTask(done - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done: \n" +
                        "  " + taskList.getTask(done - 1));
                storage.writeListIntoFile(taskList.getList());
            } catch (NumberFormatException e) {
                throw new BadDescriptionException("Non-Integer");
            }
            break;
        case TODO:
            String todo = sc.nextLine();
            if (todo.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            Task taskToDo = new Todo(todo.substring(1));
            taskList.addTask(taskToDo);
            System.out.println("I've added this task: \n" +
                    "  " + taskToDo + "\nNow you have " +
                    taskList.getSize() + " tasks in the list." );
            storage.writeListIntoFile(taskList.getList());
            break;
        case EVENT:
            String event = sc.nextLine();
            if (event.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int eventDate = event.indexOf("/");
            if (eventDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskEvent = new Event(event.substring(1, eventDate),
                    event.substring(eventDate + 4));
            taskList.addTask(taskEvent);
            calender.addDate(taskEvent);
            System.out.println("I've added this task: \n" +
                    "  " + taskEvent + "\nNow you have " +
                    taskList.getSize() + " tasks in the list." );
            storage.writeListIntoFile(taskList.getList());
            break;
        case DEADLINE:
            String deadline = sc.nextLine();
            if (deadline.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int dLineDate = deadline.indexOf("/");
            if (dLineDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskDLine = new Deadline(deadline.substring(1, dLineDate),
                    deadline.substring(dLineDate + 4));
            taskList.addTask(taskDLine);
            calender.addDate(taskDLine);
            System.out.println("I've added this task: \n" +
                    "  " + taskDLine + "\nNow you have " +
                    taskList.getSize() + " tasks in the list." );
            storage.writeListIntoFile(taskList.getList());
            break;
        case DELETE:
            try {
                String strDelete = sc.nextLine();
                if (strDelete.isEmpty()) {
                    throw new DukeDescriptionException("Empty Description");
                }
                int delete = Integer.parseInt(strDelete.substring(1)); //there must be a space between command and input
                if (delete <= 0 || delete > taskList.getSize()) {
                    throw new BadDescriptionException("Description for delete cannot be " + delete);
                }
                Task deletedTask = taskList.getTask(delete - 1);
                taskList.removeTask(delete - 1);
                calender.removeTask(deletedTask, deletedTask.getDate());
                System.out.println("Okay! I have deleted this task:\n" +
                        "  " + deletedTask + "\nNow you have " +
                        taskList.getSize() + " tasks in the list.");
                storage.writeListIntoFile(taskList.getList());
            } catch (NumberFormatException e) {
                throw new BadDescriptionException("Non-Integer");
            }
            break;
        case SEARCH:
            String search = sc.nextLine();
            if (search.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            LocalDate date = Parser.dateParser(search.substring(1));
            System.out.println("Here are the events on " +
                    date.format(Parser.DATE_READ_FORMATTER) + ":");
            calender.searchDate(date);
            break;
        case FIND:
            String find = sc.nextLine();
            if (find.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            TaskList outputList = new TaskList(taskList.find(find.substring(1)));
            System.out.println("Here are the matching tasks in your list:");
            outputList.printTask();
            break;
        default:
            break;
        }
    }

    /**
     * Generates DukeProject. This method will run the main interface of the DukeProject.
     * It will read commands given by the system using a scanner and store results in a task list before writing it
     * into storage.
     */
    public void run() {
        ui.getIntroduction();
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("bye")) {
                break;
            }
            else {
                try {
                    ui.lineBreak();
                    Command command = Parser.commandParser(next);
                    readCommand(command);
                } catch (InvalidCommandException e) {
                    System.out.println("Sorry I do not know what that means!");
                    sc.nextLine();
                } catch (DukeDescriptionException e) {
                    System.out.println("OOPS! You forgot to include a description!");
                } catch (BadDescriptionException e) {
                    System.out.println("OOPS! " + e.getMessage());
                } catch (BadDateException e) {
                    System.out.println("Sorry I don't recognise this date format!\n" +
                            "Please make sure the format is: dd mm yy");
                } finally {
                    ui.lineBreak();
                }
            }
        }
        ui.exit();
    }

    /**
     * Constructs a Duke object with a filepath to store the task list.
     * @param filepath Filepath of the Duke storage.
     */
    public Duke(String filepath) {
        ui = new Ui();
        calender = new Calender();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.printFileIntoList(calender));
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new Duke("." + File.separator + "data" +
                File.separator + "Task.txt").run();
    }
}
