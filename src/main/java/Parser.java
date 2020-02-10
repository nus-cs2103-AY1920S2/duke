import javax.print.DocFlavor;
import java.util.ArrayList;

public class Parser {


    /**
     * Method that parses user input into instructions to be followed.
     * @param input the user input
     * @param ui current instance of ui so that ui can interact with user
     * @param tasks the tasks available in current Duke program
     */
    public String parse(String input, Ui ui, TaskList tasks) {


        if (input.equals("list")) {
            return ui.printList();  // may need to update
        }

        String command = "";

        try {
            command = getCommand(input);
        } catch(DukeException d) {
            return d.toString();
        }

        switch (command) {
            case "todo": {
                // extract the task and


                String task = input.substring(input.indexOf(' ') + 1);
                Todo todo = new Todo(task);

                tasks.add(todo);
                return "Got it, I've added the following task:\n" + "  " + todo + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            }
            case "deadline": {
                String by = input.substring(input.indexOf('/') + 4);
                String task = input.substring(input.indexOf(' ') + 1, input.indexOf('/') - 1);
                Deadline deadline = new Deadline(task, by);
                tasks.add(deadline);
                return "Got it, I've added the following task:\n" + "  " + deadline + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            }
            case "event": {
                String at = input.substring(input.indexOf('/') + 4);
                String task = input.substring(input.indexOf(' ') + 1, input.indexOf('/') - 1);
                Event event = new Event(task, at);
                tasks.add(event);
                return "Got it, I've added the following task:\n" + "  " + event + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            }
            case "delete": {
                int toDelete = Integer.parseInt(input.substring(input.indexOf(' ') + 1, input.length())) - 1;
                Task task = tasks.get(toDelete);
                tasks.remove(toDelete);
                return "Noted, I've removed the following task:\n" + "  " + task + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            }
            case "done": {
                int toEdit = Integer.parseInt(input.substring(input.indexOf(' ') + 1, input.length())) - 1;
                Task task = tasks.get(toEdit);
                task.markAsDone();
            }
            case "find": {
                String keyword = input.substring(input.indexOf(' ') + 1);
                StringBuilder output = new StringBuilder();

                ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

                output.append("Here are the matching tasks in your list: ");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    String currTask = i+1 + "." + matchingTasks.get(i);
                    output.append(System.lineSeparator());
                    output.append(currTask);
                }
                return output.toString();
            }

        }

        // if it reaches here without returning means exception did not catch and its an invalid command
        assert false;
        return "Invalid Command";

    }



    /**
     * Gets the command out from user input
     * @param input is the user input
     * @return a String that is only the command
     * @throws DukeException is thrown when there is an invalid command by user
     */
    public String getCommand(String input) throws DukeException {
        if (!input.contains(" ")) {
            // check if the command is correct
            if (!input.equals("todo") &&
                    !input.equals("deadline") &&
                    !input.equals("event") &&
                    !input.equals("delete") &&
                    !input.equals("done") &&
                    !input.equals("find")) {
                throw new DukeException("OOPS! I'm sorry but I dont't know what that means :(");
            } else {
                // command is not valid
                throw new DukeException("OOPS! The description of a " + input + " cannot be empty");
            }
        } else {
            if (!input.substring(0, input.indexOf(' ')).equals("todo") &&
                    !input.substring(0, input.indexOf(' ')).equals("deadline") &&
                    !input.substring(0, input.indexOf(' ')).equals("event") &&
                    !input.substring(0, input.indexOf(' ')).equals("delete") &&
                    !input.substring(0, input.indexOf(' ')).equals("done") &&
                    !input.substring(0, input.indexOf(' ')).equals("find")) {
                throw new DukeException("OOPS! I'm sorry but I dont't know what that means :(");
            } else {
                return input.substring(0, input.indexOf(' '));
            }
        }
    }


    /**
     * A class for user-specific exceptions
     */
    class DukeException extends Exception {
        String errorMsg = "Error is not specified";
        DukeException(String s) {
            errorMsg = s;
        }

        @Override
        public String toString() {
            return errorMsg;
        }
    }
}
