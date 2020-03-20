package dukeclasses;

import java.time.DateTimeException;

/**
 * Handles all the user inputs.
 */
public class Ui {

    protected boolean hasEnded;
    protected TaskManager manager;
    protected String horizontalLine = "**************************************************";

    public Ui(TaskManager manager) {
        this.hasEnded = false;
        this.manager = manager;
    }

    /**
     * Prints out the introduction text.
     */
    public void introduction() {

        System.out.println(horizontalLine);
        //To run using gradle go and search in Help > Delegate Run > build tools > gradle
        String logo = " _____        .      _     |\n"
                +     "|         |  | |"
                +                     "   _| |_   |\n"
                +      "|_____   _|  | |  |_   _|  |__\n"
                +      "|_____  |_|  |_|    |_|    |  |";

        System.out.println("" + logo);

        try {
            Storage store = new Storage();
            store.checkFileDir();
            store.checkFile();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("Hello!! My name is Edith. Tony Stark's Personal Assistant" + "\n"
                + "What can I do for you?");
        System.out.println(horizontalLine);

    }

    /**
     * Handles all the different kinds of inputs the user can have.
     * For C-Priority I added the function to setTaskAsHighPriority, changed the way,
     * I save my tasks so when it's saved into data.txt it retains the "isHighPriority".
     * In that way when i reload my data when i start up my code again I wont lose,
     * those high priority tasks.
     * I also added a function "list urgent task" which lists all your urgent task.
     * To make a task as high priority type "highpriority (index)".
     *
     * @param textEntered textEntered represents whatever the user types
     */
    public void handleInputs(String textEntered) {

        System.out.println(horizontalLine);

        if (textEntered.equals("list")) {
            manager.listAllTasks();
        } else if (textEntered.contains("done")) {
            Parser parser = new Parser();
            int indexOfTaskDone = 0;
            try {
                indexOfTaskDone = parser.handleDoneCommands(textEntered);
            } catch (DukeException ex) {
                System.out.println("done must be followed by a number.");

            }
            try {
                manager.setTaskAsDone(indexOfTaskDone);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Done must be followed by a number.");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("There is no task with that index! ");
            }
        } else if (textEntered.contains("todo") || (textEntered.contains("deadline"))
                || textEntered.contains("event")) { //create a task
            try {
                manager.addTask(textEntered);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } catch (DateTimeException ex) {
                System.out.println("Please enter dates in this format YYYY-MM-DD");
            }
        } else if (textEntered.contains("delete")) {

            try {
                Parser parse = new Parser();
                int indexOfTaskDeleted = parse.handleDeleteCommands(textEntered);
                manager.deleteTask(indexOfTaskDeleted);
            } catch (DukeException ex) {
                System.out.println(ex);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex);
            }
        } else if (textEntered.contains("bye")) {
            manager.saveExistingData();
            this.hasEnded = true;
        } else if (textEntered.contains("find")) { //contains 1 keyword only, as stated in the question
            Parser parse = new Parser();
            String[] temp = parse.handleFindCommands(textEntered);
            try {
                if (temp.length == 1) {
                    throw new DukeException("Find must be followed by a keyword and cannot be empty.");
                }
                manager.findTask(temp[1]);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        } else if (textEntered.contains("highpriority")) {

            int indexOfTaskToBeHighPriority = 0;

            Parser parser = new Parser();
            try {
                indexOfTaskToBeHighPriority = parser.handleHighPriorityCommands(textEntered);
            } catch (DukeException ex) {
                System.out.println("highpriority must be followed by a number.");
            }

            try {
                manager.markTaskAsHighPriority(indexOfTaskToBeHighPriority);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("highpriority must be followed by a number");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("There is no task with that index! ");
            }

        } else if (textEntered.contains("list urgent task")) {
            manager.listHighPriority();
        } else { //nonsense input
            try {
                manager.nonsenseInput();
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }

        if (!textEntered.contains("bye")) {
            System.out.println(horizontalLine);
        }
    }

    /**
     * Prints the goodbye message for the user.
     */
    public void printGoodbyeMessage() {
        System.out.println("Data has been saved! Goodbye Mr.Stark!!!!!!");
        System.out.println(horizontalLine);
    }
}
