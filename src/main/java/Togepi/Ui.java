package Togepi;

public class Ui {

    /** Produces the greeting message.
     * @return a string of the greeting message.
     */
    public String greet() {
        return "Pii! Do you need Togepi for something? \n" +
                "Type 'help' for my list of commands!";
    }

    /** Produces the list message.
     * @return a string that lists all the current tasks.
     */
    public String showListMsg() {
        return "Togepi discovered: \n";
    }

    /** Produces the done message.
     * @return a string that shows the task has been completed.
     */
    public String showDoneMsg() {
        return "Togepi used Metronome! It's super effective \n";
    }

    /** Produces the add task message.
     * @return a string that shows the task has been added.
     */
    public String showAddTaskMsg() {
        return "Togepi found something and added it to her storage: \n";
    }

    /** Produces the remove message.
     * @return a string that shows the task has been removed.
     */
    public String showRemoveTaskMsg() {
        return "The opponent has fainted. ";
    }

    /** Produces the counter message.
     * @return a string that shows the number of tasks in the lists.
     */
    public String showCounterMsg(int counter) {
        return "Now you have " + counter + " tasks in your list. \n";
    }

    /** Produces the find task message.
     * @return a string that shows all the tasks that contains the keyword.
     */
    public String showFindTaskMsg() {
        return "Togepi has found: \n";
    }

    /** Produces the error message.
     * @return a string that shows the error message.
     */
    public String showErrorMsg() {
        return "Togepi is confused. \n";
    }

    /** Produces the help message.
     * @return a string that shows the list of available commands.
     */
    public String showHelp() {
        return "Togepi's moves: \n" +
                "list: show current list of tasks \n" +
                "done: mark a specific task as complete \n" +
                "find (keyword): filter list of tasks to find any tasks that contains the keyword \n" +
                "todo (name): create a new todo task \n" +
                "deadline (name) /by (dd-mm-yyyy): create a new deadline task \n" +
                "event (name) /at (dd-mm-yyyy): create a new event task \n" +
                "delete (index): deletes the task at that index \n" +
                "filter (todo/deadline/event): filter list of tasks by task type \n" +
                "percent (todo/deadline/event/done: returns the percentage of the tasks based on the second half of the input \n" +
                "help: display all the available commands \n";
    }
}
