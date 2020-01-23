import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();

    // Simply adds a separator above and below an intended output.
    public static void replyUser(String output) {
        System.out.println("\n------------------------------------------");
        System.out.println(output);
        System.out.println("------------------------------------------\n");
    }

    // Add Task to list with specified type (i.e. "T", "D", "E").
    public static void addTask(String input, String type) throws DukeTaskException {
        String str = "\nCurrent number of task(s): ";

        if (type.equals("T")) {
            Todo todo = new Todo(input);
            taskList.add(todo);
            replyUser("The following ToDo has been added:\n    " + todo.toString() + str + taskList.size());

        } else if (type.equals("D")) {
            String[] arr = input.split("/", 2);
            if (arr.length > 1) {
                String description = arr[0];
                String by = arr[1].split(" ", 2)[1];
                Deadline deadline = new Deadline(description, by);
                taskList.add(deadline);
                replyUser("The following task has been added:\n    " + deadline.toString() + str + taskList.size());
            } else {
                throw new DukeTaskException("\'/by\' field is missing.");
            }

        } else if (type.equals("E")) {
            String[] arr = input.split("/", 2);
            if (arr.length > 1) {
                String description = arr[0];
                String at = arr[1].split(" ", 2)[1];
                Event event = new Event(description, at);
                taskList.add(event);
                replyUser("The following task has been added:\n    " + event.toString() + str + taskList.size());
            } else {
                throw new DukeTaskException("\'/at\' field is missing.");
            }
        }
    }

    // Prints the user's task list.
    public static void showList() {
        if (taskList.isEmpty()) {
            replyUser("Your task list is empty.");
        } else {
            System.out.println("\n------------------------------------------");
            System.out.println("Here is your list of tasks: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + "." + task.toString());
            }
            System.out.println("------------------------------------------\n");
        }
    }

    // Mark the specified task as done.
    public static void markTaskAsDone(String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            replyUser("There is no task in your list to be marked as done.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            replyUser("As per requested, the following task has been marked as done:\n" + "    " + t.toString());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
    }

    // Delete the specified task from the list.
    public static void deleteTask(String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            replyUser("There is no task in your list to be deleted.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            taskList.remove(index);
            replyUser("As per requested, the following task has been deleted:\n" + "    " + t.toString() + "\nCurrent number of task(s): " + taskList.size());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Booting up...\n" + logo);
        System.out.println("How can I serve you?\n");

        while (true) {
            try {
                String command = sc.nextLine();
                String[] commandArray = command.split(" ", 2);

                if (command.toLowerCase().equals("bye")) {
                    replyUser("I believe this is farewell, my friend.");
                    break;
                } else if (command.toLowerCase().equals("list")) {
                    showList();
                } else if (commandArray.length < 2) {
                    throw new DukeArgumentException("Missing field detected in your '" + commandArray[0] + "' command.");
                } else if (commandArray[0].toLowerCase().equals("done")) {
                    markTaskAsDone(commandArray[1]);
                } else if (commandArray[0].toLowerCase().equals("delete")) {
                    deleteTask(commandArray[1]);
                } else if (commandArray[0].toLowerCase().equals("todo")) {
                    addTask(commandArray[1], "T");
                } else if (commandArray[0].toLowerCase().equals("deadline")) {
                    addTask(commandArray[1], "D");
                } else if (commandArray[0].toLowerCase().equals("event")) {
                    addTask(commandArray[1], "E");
                } else {
                    throw new DukeUnknownException("Apologies, I do not recognise this command.");
                }
            } catch (DukeException err) {
                replyUser(err.getMessage());
            }
        }
    }
}