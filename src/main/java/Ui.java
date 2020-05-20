/**
 * This class contains Duke's response to user's input, including error messages.
 */
public class Ui {
    String space = "     ";

    public Ui() {
    }

    /**
     * This method prints the greeting string at the start of the program.
     */
    public String greeting() {
        String greeting = "Hello! I'm Duke\n" + " What can I do for you?\n";
        return greeting;
    }

    /**
     * This method prints the string for instruction.
     */
    public String instruction() {
        String instruction = "Try add a task by:\n" + space + "1. todo xxxx\n" + space
                + "2. deadline xxxx /by YYYY-MM-DD\n"
                + space + "3. event xxxx /at YYYY-MM-DD\n" + "or enter 'list' to display all items\n"
                + "or 'delete/done index' for deletion/marked as done\n"
                + "or find xxxx for search\n"
                + "or sort existing deadlines/events by enter 'sortAsc' or 'sortDes'";
        return instruction;
    }

    public String removeTask(int count, String task) {
        return ("Noted. I've removed this task:\n" + space + task
                + "Now you have " + count + " task" + (count > 1 ? "s " : " "));
    }

    public String searchTask() {
        return "Here are the matching tasks in your list:\n";
    }

    public String addTask(TaskList list) {
        return (" Got it. I've added this task:\n" + space + list.items.get(list.items.size() - 1)
                + " Now you have " + list.count + " task" + (list.count > 1 ? "s " : " ") + "in the list.");
    }

    public String markDone(Item item) {
        return "Nice! I've marked this task as done:\n" + space + item;
    }

    public String printIOerr() {
        return (space + "The format of io is wrong.");
    }

    public String printDateErr() {
        return (space + "OOPS!!! The format of date is wrong (yyyy-MM-dd).");
    }
    
    public String printIndexErr() {
        return (space + "OOPS!!! The format of index is wrong.");
    }
}
