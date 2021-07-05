package duke;


public class Ui {

    /***
     * Print the opening menu
     */
    protected String opening() {
        String output = "Hello from Doraemon\n" ;
        output += TaskList.printList();
        output += printLine();

        output += "What can I do for you?\n";
        output += printLine();
        return output;

    }

    /***
     * Print the closing statements
     */

    protected String ending() {
        String output = printLine();
        output += "Bye. Hope to see you again soon!\n";
        output += printLine();
        return output;
    }

    /***
     * Print the straight lines
     */
    protected String printLine() {
        return "____________________________________________________________\n";
    }

    /***
     * Print error if command not recognised
     */
    protected String printErrorUnderstanding() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( \n";
    }

    /***
     * Print error if time not specified
     */
    protected String printErrorNoTime() {
        return "OOPS!!! I'm sorry, but you need to specify the time \n";
    }

    /***
     * Print error if task not found
     */
    protected String printErrorNotFound() {
        return "OOPS!!! I'm sorry, I can't find that task \n";
    }

    /***
     * Print error if date entered in wrong format
     */
    protected String printErrorWrongDateFormat() {
        return "OOPS!!! I'm sorry, wrong date format \n";
    }

    /***
     * Print error if task name not specified
     */
    protected String printErrorNoTaskName() {
        return "OOPS!!! The description of a task cannot be empty \n";
    }
}
