import java.io.IOException;

/**
 * Parser class to handle inputs and initiate the appropriate actions.
 */
public class Parser {
    public Parser(Ui ui) {
    }

    /**
     * Method reads input from user and performs required actions.
     * @param taskList TaskList to store tasks
     * @param storage Storage to update/write information to
     * */
    public static void read(String input, TaskList taskList, Storage storage) {
        try {
            if (input.equals("bye")) {
                Ui.printBye();
            } else if (input.equals("list")) {
                Ui.printList(taskList);
            } else if (input.contains("done")) {
                String[] inputArr = input.split(" ", 2);
                assert inputArr.length == 2;
                int taskNum = Integer.parseInt(inputArr[1]);
                taskList.taskDone(taskNum);
                storage.updateFile(taskList);
            } else if (input.contains("find")) {
                String[] inputArr = input.split(" ", 2);
                assert inputArr.length == 2;
                taskList.findTasks(inputArr[1]);
            } else if (input.contains("todo")) {
                assert input.substring(5).length() > 0;
                if (input.split(" ").length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    taskList.addList(new ToDo(input.substring(5)));
                    storage.updateFile(taskList);
                }
            } else if (input.contains("deadline")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    int index = input.indexOf("/");
                    taskList.addList(new Deadline(input.substring(9, index), input.substring(index + 4)));
                }
            } else if (input.contains("event")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                } else {
                    int index = input.indexOf("/");
                    taskList.addList(new Event(input.substring(6, index), input.substring(index + 4)));
                }
            } else if (input.contains("delete")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("OOPS!!! Please specify a task number.");
                } else {
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]);
                    taskList.deleteTask(taskNum);
                    storage.updateFile(taskList);
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            Ui.printException(e);
        } catch (IOException e) {
            Ui.printException(e);
        }
    }
}
