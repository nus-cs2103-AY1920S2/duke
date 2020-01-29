import java.util.ArrayList;

public class Parser {


    /**
     * Method that parses user input into instructions to be followed.
     * @param input the user input
     * @param ui current instance of ui so that ui can interact with user
     * @param tasks the tasks available in current Duke program
     */
    public void parse(String input, Ui ui, TaskList tasks) {
        if (input.equals("list")) {
            ui.printList();
        }

        String command = "";

        try {
            command = getCommand(input);
        } catch(DukeException d) {
            System.out.println(d.getMessage());
        }

        switch (command) {
            case "todo": {
                String task = input.substring(input.indexOf(' ') + 1);
                Todo todo = new Todo(task);

                tasks.add(todo);
                System.out.println("Got it, I've added the following task:\n" + "  " + todo + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
                break;
            }
            case "deadline": {
                String by = input.substring(input.indexOf('/') + 4);
                String task = input.substring(input.indexOf(' ') + 1, input.indexOf('/') - 1);
                Deadline deadline = new Deadline(task, by);
                tasks.add(deadline);
                System.out.println("Got it, I've added the following task:\n" + "  " + deadline + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
                break;
            }
            case "event": {
                String at = input.substring(input.indexOf('/') + 4);
                String task = input.substring(input.indexOf(' ') + 1, input.indexOf('/') - 1);
                Event event = new Event(task, at);
                tasks.add(event);
                System.out.println("Got it, I've added the following task:\n" + "  " + event + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
                break;
            }
            case "delete": {
                int toDelete = Integer.parseInt(input.substring(input.indexOf(' ') + 1, input.length())) - 1;
                Task task = tasks.get(toDelete);
                tasks.remove(toDelete);
                System.out.println("Noted, I've removed the following task:\n" + "  " + task + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
                break;
            }
            case "done": {
                int toEdit = Integer.parseInt(input.substring(input.indexOf(' ') + 1, input.length())) - 1;
                Task task = tasks.get(toEdit);
                task.markAsDone();
                break;
            }
            case "find": {
                String keyword = input.substring(input.indexOf(' ') + 1);
                ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                System.out.println("Here are the matching tasks in your list: ");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    System.out.println(i+1 + "." + matchingTasks.get(i));
                }
            }
        }

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
                    !input.substring(0, input.indexOf(' ')).equals("list") &&
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
        DukeException(String s) {
            super(s);
        }
    }
}
