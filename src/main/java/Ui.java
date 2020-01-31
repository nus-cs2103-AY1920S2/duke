import java.io.IOException;

public class Ui {
    String userInput;
    Parser parser;

    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String DONE = "done";
    public static final String FIND = "find";


    /**
     * Constructor for Ui.
     */
    public Ui() {
        //deals with interactions with the user
        //this.userInput = userInput;

        parser = new Parser();
    }

    /**
     * Greet the user.
     */
    public void greetings() {
        String intro = "Hello! I'm Grapie! \n"
                + "   _____                 _      \n"
                + "  / ____|               (_)     \n"
                + " | |  __ _ __ __ _ _ __  _  ___ \n"
                + " | | |_ | '__/ _` | '_ \\| |/ _ \\ \n"
                + " | |__| | | | (_| | |_) | |  __/ \n"
                + "  \\_____|_|  \\__,_| .__/|_|\\___| \n"
                + "                  | |           \n"
                + "                  |_|           \n"


                + "What do ya need from me?\n";

        TaskList.formattingDivider(intro);
    }

    /**
     * Returns goodbye to the user.
     */
    public void sayonara() {
        TaskList.formattingDivider("Okie!! Goodbye!");
    }

    /**
     * Use Parser to read user's command to call correct method, and prints out any error encountered.
     *
     * @param command User's input.
     * @param tasks TaskList class created in Duke.
     * @throws IOException Throws away the exception.
     */
    public void readCommand(String command, TaskList tasks) throws IOException {
        String commandArr = parser.makeSenseOfUserCommand(command);

        if (commandArr.equals(LIST)) {
            tasks.listTheList();
        } else if (commandArr.equals(DONE)) {
            try {
                tasks.completeTask(command);
            } catch (GrapieExceptions grapieExceptions) {
                System.out.println("    #__________________________________________________________# \n");
                System.out.println("      " + grapieExceptions);
                System.out.println("    #__________________________________________________________#");
            }
        } else if (commandArr.equals(ADD)) {
            try {
                tasks.addToList(command);
            } catch (GrapieExceptions grapieExceptions) {
                System.out.println("    #__________________________________________________________# \n");
                System.out.println("      " + grapieExceptions);
                System.out.println("    #__________________________________________________________#");
            }
        } else if (commandArr.equals(DELETE)) {
            try {
                tasks.deleteTask(command);
            } catch (GrapieExceptions grapieExceptions) {
                System.out.println("    #__________________________________________________________# \n");
                System.out.println("      " + grapieExceptions);
                System.out.println("    #__________________________________________________________#");
            }
        } else if (commandArr.equals(FIND)) {
            //find
            try {
                tasks.findFromList(command);
            } catch (GrapieExceptions grapieExceptions) {
                System.out.println("    #__________________________________________________________# \n");
                System.out.println("      " + grapieExceptions);
                System.out.println("    #__________________________________________________________#");
            }
        }
    }

}
