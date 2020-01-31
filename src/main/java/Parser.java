import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public Parser(Ui ui) {
    }

    public static void scan(TaskList taskList, Storage storage ) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    Ui.printBye();
                    break;
                } else if (input.equals("list")) {
                    Ui.printList(taskList);
                } else if (input.contains("done")) {
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]);
                    taskList.taskDone(taskNum);
                    storage.updateFile(taskList);
                } else if (input.contains("todo")) {
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
}
