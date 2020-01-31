import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import exceptions.EmptySearchException;
import exceptions.InvalidActionException;
import exceptions.InvalidTaskNumberException;
import tasks.TaskList;
import tasks.Task;
import tasks.Deadline;
import tasks.Todo;
import tasks.Event;
import ui.Ui;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Makes sense of user inputs.
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Scanner sc;

    /**
     * Parses command line inputs into valid actions of the chat bot.
     *
     * @param taskList List to store all tasks.
     * @param storage Allows persistence of data.
     * @param ui UI of the chat bot.
     * @param sc Reads user inputs.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui, Scanner sc) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.sc = sc;
    }

    /**
     * Splits the input into actions and arguments (if any).
     * Run the respective actions and outputs feedback from the action taken.
     * "List" will list all the tasks out.
     * "Done i" will mark ith task as done.
     * "Delete i" will delete the ith task.
     * "todo d" will create a Todo task with description d.
     * "event d t" will create an event with description d and start time t.
     * "deadline d dt" will create a deadline with description d and start time dt.
     * "date d" will return all the dated tasks with date d.
     *
     * @param input User inputs.
     */
    public void parse(String input) {
        String action = input.split(" ")[0];
        try {
            switch (action) {
            case "list":
                taskList.print();
                break;
            case "done":
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new InvalidTaskNumberException(taskList.size());
                }
                taskList.markAsDone(taskNumber - 1);
                break;
            case "delete":
                taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new InvalidTaskNumberException(taskList.size());
                }
                taskList.delete(taskNumber - 1);
                break;
            case "todo":
                try {
                    String[] fields = input.split("todo ");
                    if (fields.length < 2) {
                        throw new EmptyDescriptionException("todo");
                    }
                    Task newTodo = new Todo(fields[1]);
                    taskList.add(newTodo);
                } catch (EmptyDescriptionException ex) {
                    ui.printFormattedOutput(ex.toString());
                }
                break;
            case "event":
            case "deadline":
                try {
                    String[] fields = input.split(action + " ");
                    if (fields.length < 2) {
                        throw new EmptyDescriptionException(action);
                    }

                    fields = action.equals("event")
                            ? fields[1].split(" /at ")
                            : fields[1].split(" /by ");
                    if (fields.length < 2) {
                        throw new EmptyTimeException(action, fields);
                    }

                    Task newTask = action.equals("event")
                            ? new Event(fields[0], fields[1])
                            : new Deadline(fields[0], fields[1]);

                    taskList.add(newTask);
                } catch (EmptyDescriptionException ex) {
                    ui.printFormattedOutput(ex.toString());
                } catch (EmptyTimeException ex) {
                    String message = ex.toString()
                            + "\n    Type in the time/date or press enter to stop creating task";
                    ui.printFormattedOutput(message);
                    System.out.print(ex.stringifyFields());
                    input = sc.nextLine();
                    if (!input.equals("")) {
                        String[] fields = ex.getFields();
                        Task newTask = action.equals("event")
                                ? new Event(fields[0], input)
                                : new Deadline(fields[0], input);
                        taskList.add(newTask);
                    } else {
                        ui.printFormattedOutput("Stopped creating task.");
                    }
                }
                break;
            case "date":
                try {
                    String[] fields = input.split(" ");
                    LocalDate date = LocalDate.parse(fields[1]);
                    taskList.searchDateTask(date);
                } catch (DateTimeException ex) {
                    ui.printFormattedOutput("Sorry, I don't recognize this date format. "
                            + "Try to follow this format: 2020-12-31");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    ui.printFormattedOutput("Please input a date!");
                }
                break;
            case "find":
                String[] fields = input.split(" ");
                if (fields.length < 2) {
                    throw new EmptySearchException();
                }
                String searchWord = fields[1];
                ArrayList<Task> searchList = taskList.search(searchWord);
                ui.printList(searchList);
                break;
            default:
                throw new InvalidActionException();
            }
            storage.save(taskList);
        } catch (InvalidActionException ex) {
            ui.printFormattedOutput(ex.toString());
        } catch (InvalidTaskNumberException ex) {
            ui.printFormattedOutput(ex.toString());
        } catch (IOException ex) {
            ui.printFormattedOutput(ex.toString());
        } catch (DateTimeException ex) {
            ui.printFormattedOutput("You have entered an invalid time/date format.\n    "
                    + "Please follow the following format: 23:59 2020-12-31\n    "
                    + "You may input '-' to omit either the time or date");
        } catch (EmptySearchException ex) {
            ui.printFormattedOutput(ex.toString());
        }
    }
}
