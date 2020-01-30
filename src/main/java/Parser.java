import java.time.LocalDate;
import java.util.List;

public class Parser {
    private static final String BYE_CMD = "bye";
    private static final String TODO_CMD = "todo";
    private static final String EVENT_CMD = "event";
    private static final String DEADLINE_CMD = "deadline";
    private static final String LIST_CMD = "list";
    private static final String DELETE_CMD = "delete";
    private static final String DONE_CMD = "done";

    public Parser() {
    }

    private  boolean canSplitStr(String str, String regex) {
        String[] strArr = str.split(regex);

        return (strArr.length == 2);
    }

    private  boolean validId(String strId, TaskList tasks) {
        int id;
        try {
            id = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return false;
        }

        if ((id - 1) > tasks.getNumTasks() - 1 || (id - 1) < 0) {
            return false;
        }

        return true;
    }

    public Command parse(String cmd, TaskList tasks) throws DukeException {
        String[] strArr;
        String str1;
        Task t;
        if (cmd.equals(LIST_CMD)) {
            return new Command(CommandType.LIST_CMD, null);
        } else if (cmd.startsWith(DONE_CMD)) {
            if (!canSplitStr(cmd, "done\\s+")) {
                throw new DukeException("The task to mark done cannot be empty");
            }

            str1 = cmd.split("done\\s+")[1];

            if (!validId(str1, tasks)) {
                throw new DukeException("The task to mark done is not in the list");
            }

            return new Command(CommandType.DONE_CMD, new String[] { str1 });
        } else if (cmd.startsWith(DELETE_CMD)) {
            if (!canSplitStr(cmd, "delete\\s+")) {
                throw new DukeException("The task to delete cannot be empty");
            }

            str1 = cmd.split("delete\\s+")[1];

            if (!validId(str1, tasks)) {
                throw new DukeException("The task to mark delete is not in the list");
            }

            return new Command(CommandType.DELETE_CMD, new String[] { str1 });
        } else if (cmd.startsWith(TODO_CMD)) {
            if (!canSplitStr(cmd, "todo\\s+")) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            str1  = cmd.split("todo\\s+")[1];

            return new Command(CommandType.TODO_CMD, new String[] { str1 });
        } else if (cmd.startsWith(DEADLINE_CMD)) {
            if (!canSplitStr(cmd, "deadline\\s+")) {
                throw new DukeException("The description and timing of a deadline cannot be empty");
            }

            str1 = cmd.split("deadline\\s+")[1];

            if (!canSplitStr(str1, "\\s+/by\\s+")) {
                throw new DukeException("Both the description and timing of a deadline cannot be empty");
            }

            strArr = str1.split("\\s+/by\\s+");

            String name = strArr[0];
            String by = strArr[1];

            return new Command(CommandType.DEADLINE_CMD, new String[] { name, by });
        } else if (cmd.startsWith(EVENT_CMD)) {
            if (!canSplitStr(cmd, "event\\s+")) {
                System.out.println("went here");
                throw new DukeException("The description and timing of an event cannot be empty");
            }

            str1 = cmd.split("event\\s+")[1];

            if (!canSplitStr(str1, "\\s+/at\\s+")) {
                System.out.println("went here 2");
                throw new DukeException("Both the description and timing of an event cannot be empty");
            }

            strArr = str1.split("\\s+/at\\s+");

            String name = strArr[0];
            String at = strArr[1];

            return new Command(CommandType.DEADLINE_CMD, new String[] { name, at });
        } else if (cmd.equals(BYE_CMD)) {
            return new Command(CommandType.BYE_CMD, null);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
