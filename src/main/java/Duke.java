import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Start the duke program
        System.out.println("Hello from\n" + logo);
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hey friend! I'm Duke V2.0.0 at your service\n" +
                "     What can I do for you today?\n" +
                "    ____________________________________________________________\n"
        );

        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        String[] inputTokens;
        String input;
        List<Task> tasks = new ArrayList<>();

        // Parse each command by user
        while (true) {
            // Get command entered by user
            try {
                 input = bufferedReader.readLine();
                 inputTokens = input.split(" ");
            } catch (IOException e) {
                System.out.println("Error in reading input");
                break;
            }

            switch (inputTokens[0]) {
                case "bye":
                    // Exit program
                    prettyPrint("Sorry to see you go. Hope to see you again soon! (＾▽＾)／");
                    break;
                case "list":
                    String tasksString = "";
                    Integer size = tasks.size();

                    // Print out all items in list
                    for(int i = 0; i < size; i++) {
                        tasksString += (i + 1) + "." + tasks.get(i);
                        if (i != size - 1) {
                            tasksString += "\n     ";
                        }
                    }
                    tasksString = tasksString.equals("") ? "There is nothing on your list." : tasksString;
                    prettyPrint(tasksString);
                    break;
                case "done":
                    // Mark the task with given index as done
                    Integer index = Integer.parseInt(inputTokens[1]) - 1;
                    if (index < tasks.size()) {
                        Task task = tasks.get(index);
                        task.markAsDone(true);
                        prettyPrint("Nice! I've marked this task as done: \n" +
                                "       " + task);
                    } else {
                        System.out.println("No such task index");
                    }
                    break;
                default:
                    // Add new task to task list
                    Task newTask = new Task(input);
                    tasks.add(newTask);
                    prettyPrint("You have added " + input);
            }
        }
    }

    /**
     * Format the given line into a pretty format and print it
     *
     * @param  line   the line to be formatted
     */
    public static void prettyPrint(String line) {
        System.out.println(
                "    _____________________________DUKE___________________________\n" +
                "     " + line + "\n" +
                "    _________★゜・。。・゜゜・。。・゜☆゜・。。・゜゜・。。・゜★_______\n"
        );
    }
}
