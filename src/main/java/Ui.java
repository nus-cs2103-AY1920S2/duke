/**
 * This class contains Duke's response to user's input, including error messages.
 */
public class Ui {
    String space = "     ";
    String line = space + "____________________________________________________________";

    public Ui() {}

    /**
     * This method prints the greeting string at the start of the program.
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = line + "\n" + space + " Hello! I'm Duke\n" + space + " What can I do for you?\n" + line + "\n";
        System.out.print(greeting);
    }

    /**
     * This method prints the string at the end of the program.
     */
    public void bye() {
        String bye = line + "\n" + space + " Bye. Hope to see you again soon!\n" + line;
        System.out.print(bye);
    }

    public String removeTask(TaskList list, int index, int count) {
        return (line + "\n" + space + "Noted. I've removed this task:\n" + space + list.items.get(index) + space + "Now you have " + count + " task");
    }
    public String searchTask() {
        return (line + "\n" + space + "Here are the matching tasks in your list:\n");
    }
    public String addTask(TaskList list, int count) {
        return (line + "\n" + space + " Got it. I've added this task:\n" + space + list.items.get(list.items.size()-1)
                + space + " Now you have " + count + " task");
    }

    public void markDone(Item item) {
        System.out.println(line + "\n" + space + "Nice! I've marked this task as done:\n" + space + item + line);
    }

    public void throwErr(String err) {
        throw new IllegalInstructionException(space + err);
    }

    public void throwDescriptionErr() {
        throw new IllegalInstructionException(space+"☹ OOPS!!! The description of a task cannot be empty.");
    }

    public void throwTimeErr() {
        throw new IllegalInstructionException(space+"☹ OOPS!!! The time/description of a task cannot be empty.");
    }
    public void printIOErr() {
        System.err.println(space+"☹ OOPS!!! The format of IO is wrong.");
    }
    public void printDateErr() {
        System.err.println(space+"☹ OOPS!!! The format of date is wrong (yyyy-MM-dd).");
    }
    public void printLoad() {
        System.out.println(space+"Past data successfully loaded.");
    }
    public void printIndexErr() {
        System.err.println(space + "☹ OOPS!!! The format of index is wrong.");
    }

}
