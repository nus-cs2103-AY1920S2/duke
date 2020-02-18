import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents the entire Duke program, basically an interactive To-do List.
 */
public class Duke {
    /**
     * Handles the input and produces the response to be displayed back to the user.
     * 
     * @param input User input
     * @return Response to user input in String format
     * @throws IOException Handles IO exceptions
     */
    public String getResponse(String input) throws IOException {

        String[] split = input.split("\\s+");
        String command = split[0];
        int numOfTask;
        Storage storage = new Storage();
        String tasks = storage.readFromFile();
        TaskList taskList = Parser.storageToTaskList(tasks);

        if (command.equals("bye")) {
            return ("Bye. Hope to see you again soon!");

        } else if (command.equals("list")) {
            if (taskList.list.size() == 0) {
                return ("OOPS list is empty!");
            }
            return (taskList.toString());

        } else if (command.equals("done")) {
            int index = Integer.parseInt(split[1]) - 1;
            Task done = taskList.list.get(index);
            done.markAsDone();
            tasks = storage.readFromFile();
            storage.changeToDone(tasks, index);

            return ("Nice! I've marked this task as done: \n" + done);

        } else if (command.equals("delete")) {
            int index = Integer.parseInt(split[1]) - 1;
            Task remove = taskList.list.remove(index);
            numOfTask = taskList.list.size();
            tasks = storage.readFromFile();
            storage.removeLine(tasks, index);

            return ("Noted. I've removed this task: \n" + remove + "\nNow you have " + numOfTask
                    + " task(s) in the list");

        } else if (command.equals("todo")) {
            if (split.length == 1) {
                return ("OOPS!!!" + " The description of a todo cannot be empty. \n");
            }
            String desc = input.replace("todo ", "");
            Task t = new ToDoTask(desc);
            taskList.list.add(t);
            numOfTask = taskList.list.size();
            storage.writeToFile("T | 0 | " + desc);
            return ("Got it. I've added this task: \n" + t + "\nNow you have " + numOfTask + " task(s) in the list.");

        } else if (command.equals("deadline")) {
            String[] arr = input.split(" /by ");
            String desc = arr[0].replace("deadline ", "");
            String time = arr[1];
            Task t = new DeadlineTask(desc, LocalDate.parse(time));
            taskList.list.add(t);
            storage.writeToFile("D | 0 | " + desc + " | " + time);
            numOfTask = taskList.list.size();
            return ("Got it. I've added this task: \n" + t + "\nNow you have " + numOfTask + " task(s) in the list.");

        } else if (command.equals("event")) {
            String[] arr = input.split(" /at ");
            String desc = arr[0].replace("event ", "");
            String time = arr[1];
            Task t = new EventTask(desc, LocalDate.parse(time));
            taskList.list.add(t);
            numOfTask = taskList.list.size();
            storage.writeToFile("E | 0 | " + desc + " | " + time);
            return ("Got it. I've added this task: \n" + t + "\nNow you have " + numOfTask + " task(s) in the list.");

        } else if (command.equals("find")) {
            if (split.length == 1) {
                return ("Please enter a keyword after \"find\"!");
            }
            String keyword = split[1];
            tasks = storage.readFromFile();
            String found = storage.findTask(tasks, keyword);
            TaskList foundList = Parser.storageToTaskList(found);
            return ("Here are the matching tasks in your list: \n" + foundList.toString());

        } else if (command.equals("sort")) { 
            if (split[1].equals("deadlines")) { // sort deadlines chronologically
                TaskList sortedList = taskList.sortDeadlineTask();
                return ("Here are your deadlines sorted chronologically: \n" + sortedList.toString());

            } else if (split[1].equals("events")) { // sort events chronologically
                TaskList sortedList = taskList.sortEventTask();
                return ("Here are your events sorted chronologically: \n" + sortedList.toString());

            } else if (split[1].equals("todos")) {
                TaskList sortedList = taskList.sortTodoTask();
                return ("Here are your todos sorted by description: \n" + sortedList.toString());
            } else {
                return ("That is not a valid sort command!");
            }

        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-( \n";
        }
    }

    
}
