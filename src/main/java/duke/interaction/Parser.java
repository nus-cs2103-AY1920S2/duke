package duke.interaction;

import java.util.ArrayList;

import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.task.Task;


/**
 * Handles user commands.
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private ArrayList<Task> archived;

    /**
     * Constructor for Parser.
     * @param ui ui instance.
     * @param taskList tasklist instance.
     * @param storage storage instance.
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Runs actions invoked by user input.
     * @throws DukeException  If invalid command such as empty command.
     */
    public String parse(String lines) {
        String str;
        String[] inputArr = lines.split(" ");
        String instr = inputArr[0];

        if (instr.toLowerCase().contains("bye")) {
            //exit when "bye"
            str = ui.sayBye();
            System.exit(0);

        } else if (instr.equalsIgnoreCase("list")) {
            //loop through tasks array to list
            str = String.format("%s%s", ui.list(), taskList.list());

        } else if (instr.contains("delete")) {
            int eventNum = Integer.parseInt(inputArr[1]);
            assert eventNum > 0 : "invalid task index";

            try {
                str = taskList.delete(eventNum);
            } catch (IndexOutOfBoundsException e) {
                str = ("OOPS! Task number does not exist! Try again?");
            }

        } else if (instr.contains("done")) {
            //get task number and change isDone to true
            int eventNum = Integer.parseInt(inputArr[1]);
            assert eventNum > 0 : "invalid task index";

            try {
                str = taskList.done(eventNum);
            } catch (IndexOutOfBoundsException e) {
                str = ("OOPS! Task number does not exist! Try again?");
            }

        } else if (instr.contains("find")) {
            String[] arr = instr.split(" ");
            String keyword = inputArr[1];
            str = taskList.find(keyword);

        } else if (instr.contains("archive")) {
            //store in separate file and clear current
            storage.archive(taskList.tasksToString());
            str = "archived!";
            taskList.clearTasks();

        } else if (instr.contains("history")) {
            //show most recently archived content
            try {
                archived = storage.retreive();
            } catch (DukeException e) {
                e.printStackTrace();
            }
            str = "Here are your recently archived tasks:\n" + taskList.listArchived(archived);
        } else {
            try {
                if (lines.length() < 5) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                assert lines.length() > 4 : "invalid input!";
                String input = lines.substring(5);
                //add items to tasks array
                if (instr.equalsIgnoreCase("todo")) {
                    if (!input.isEmpty()) {
                        str = taskList.addTodo(input);
                    } else {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }

                } else if (instr.equalsIgnoreCase("deadline")) {
                    if (!input.isEmpty()) {
                        str = taskList.addDeadline(input.substring(4));
                    } else {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }

                } else if (instr.equalsIgnoreCase("event")) {
                    if (!input.isEmpty()) {
                        str = taskList.addEvent(input.substring(1));
                    } else {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }

                } else {
                    //instruction does not exist
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                str += String.format("\nNow you have %d tasks in the list.\n", taskList.getSize());
            } catch (DukeException ex) {
                //catch exceptions thrown and print out message for user
                str = ex.toString();

            } catch (AssertionError err) {
                str = err.toString();
            }
        }
        //write to storage
        storage.writeToFile(taskList.tasksToString());
        return str;
    }
}