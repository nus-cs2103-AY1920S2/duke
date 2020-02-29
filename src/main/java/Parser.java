import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class handles all input from the user.
 */

public class Parser {
    private static TaskList currentList;

    /**
     * Creates a new instance of the class Parser.
     */

    public Parser() {
    }

    /**
     * Associates a parser with a task list.
     * @param tasks This is the TaskList to be associated with the current parser.
     */

    public static void addList(TaskList tasks) {
        currentList = tasks;
    }

    /**
     * Parses the command string.
     * @param command The command string that the user input.
     * @return The response of Duke to the user
     */

    public static String parse(String command) {
        String output = "";
        if (command.equals("hi")) {
            //Exit
            output += "What's up my dude???";
        } else if (command.equals("list")) {
            //List all tasks
            if (currentList.getSize() == 0) {
                output += "Your list is empty";
            } else {
                output += "Here are the tasks in your list:"
                        + System.lineSeparator();
                for (int i = 0; i < currentList.getSize(); i++) {
                    output += i + 1 + ". " + currentList.getTask(i)
                            + System.lineSeparator();
                }
            }
        } else if ((command.length() > 3) && (command.substring(0, 4).equals("done"))) {
            //Mark task as done
            try {
                int taskNumber = Integer.parseInt(command.substring(5));
                currentList.getTask(taskNumber - 1).markAsDone();
                output += "Nice! I've marked this task as done:"
                        + System.lineSeparator();
                output += currentList.getTask(taskNumber - 1)
                        + System.lineSeparator();
            } catch (StringIndexOutOfBoundsException e) {
                output += Ui.emptyError("Done");
            } catch (IndexOutOfBoundsException e) {
                output += Ui.invalidNumber("mark as done");
            }
        } else if ((command.length() > 5) && (command.substring(0, 6).equals("delete"))) {
            //Delete task
            try {
                int taskNumber = Integer.parseInt(command.substring(7));
                Task temp = currentList.removeTask(taskNumber - 1);
                output += "Nice! I have removed this task:"
                        + System.lineSeparator();
                output += temp + System.lineSeparator();
            } catch (StringIndexOutOfBoundsException e) {
                output += Ui.emptyError("Delete");
            } catch (IndexOutOfBoundsException e) {
                output += Ui.invalidNumber("delete");
            }
        } else if ((command.length() > 3) && (command.substring(0, 4).equals("find"))) {
            //Find task
            try {
                if (command.length() == 4) throw new DukeException("No info find");
                boolean isMatch = false;
                String keyWord = command.substring(5);
                ArrayList<Task> matchedTask = new ArrayList<Task>();
                for (int i = 0; i < currentList.getSize(); i++) {
                    Task currentTask = currentList.getTask(i);
                    if (currentTask.toString().contains(keyWord)) {
                        matchedTask.add(currentTask);
                        isMatch = true;
                    }
                }
                if (isMatch) {
                    output += "Here are the matching tasks in your list:"
                            + System.lineSeparator();
                    for (int j = 0; j < matchedTask.size(); j++) {
                        output += (j + 1) + "." + matchedTask.get(j)
                                + System.lineSeparator();
                    }
                } else {
                    output += "There are no tasks that match your description"
                            + System.lineSeparator();
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (DukeException e) {
                output += Ui.emptyError("Find");
            }
        } else if ((command.length() > 2) && (command.substring(0, 3).equals("tag"))) {
            //Tag a task
            try {
                if (command.length() == 3) throw new DukeException("No tag found");
                int breakPos = command.indexOf("/");
                String keyword = command.substring(4, breakPos - 1).replaceAll("\\s","");
                int taskPos = Integer.parseInt(command.substring(breakPos + 4));
                currentList.getTask(taskPos - 1).updateTag(keyword);
                output += "Nice! I have tagged this task:"
                        + System.lineSeparator();
                output += currentList.getTask(taskPos - 1);
            } catch (DukeException e) {
                output += Ui.emptyError("Tag");
            } catch (StringIndexOutOfBoundsException e) {
                output += "Please re-enter a valid tag";
            } catch (IndexOutOfBoundsException e) {
                output += Ui.invalidNumber("tag");
            }
        } else {
            //Add task
            Task newTask = new Task("placeholder");
            if (command.contains("todo")) {
                try {
                    newTask = new ToDo(command.substring(5));
                    currentList.addTask(newTask);
                    assert (currentList.getSize() != 0);
                    output += Ui.gotIt(newTask, currentList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    output += Ui.emptyError("ToDo");
                }
            } else if (command.contains("deadline")) {
                try {
                    int breakPos = command.indexOf("/");
                    if ((breakPos == -1) && (command.length() == 8)) {
                        throw new DukeException("No desc Deadline");
                    }
                    LocalDate date = LocalDate.parse(command.substring(breakPos + 4));
                    newTask = new Deadline(command.substring(9, breakPos - 1), date);
                    currentList.addTask(newTask);
                    assert (currentList.getSize() != 0);
                    output += Ui.gotIt(newTask, currentList.getSize());

                } catch (DukeException e) {
                    output += Ui.emptyError("Deadline");
                } catch (IndexOutOfBoundsException e) {
                    output += Ui.reEnterDate("Deadline");
                } catch (DateTimeParseException e) {
                    output += Ui.wrongDate();
                }
            } else if (command.contains("event")) {
                try {
                    int breakPos = command.indexOf("/");
                    if ((breakPos == -1) && (command.length() == 5)) {
                        throw new DukeException("No desc Event");
                    }
                    LocalDate date = LocalDate.parse(command.substring(breakPos + 4));
                    newTask = new Event(command.substring(6, breakPos - 1), date);
                    currentList.addTask(newTask);
                    assert (currentList.getSize() != 0);
                    output += Ui.gotIt(newTask, currentList.getSize());
                } catch (DukeException e) {
                    output += Ui.emptyError("Event");
                } catch (IndexOutOfBoundsException e) {
                    output += Ui.reEnterDate("Event");
                } catch (DateTimeParseException e) {
                    output += Ui.wrongDate();
                }
            } else {
                output += Ui.showInputError();
            }
        }
        assert (!output.equals(""));
        return output;
    }
}
