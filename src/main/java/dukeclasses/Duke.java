package dukeclasses;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Contains a product name dukeClasses.Duke, a personal assistant chat bot that keeps track of various tasks that needs,
 * to be done. It classifies tasks into deadline, todo and event.
 * It can add, delete, list all the tasks, find task with a keyword and mark them as done.
 */
public class Duke {

    PrintStream helper;

    /**
     * The main method to start Duke.
     *
     * @param args args to pass in.
     */
    public static void main(String[] args) {

        FastReader fr = new FastReader();//Handles input by the user
        TaskManager manager = new TaskManager();
        Ui ui = new Ui(manager);

        ui.introduction();
        manager.loadExistingData();

        String textEntered = fr.nextLine();

        while (!ui.hasEnded) {
            ui.handleInputs(textEntered);
            if (textEntered.equals("bye")) {
                break;
            }
            textEntered = fr.nextLine();
        }

        ui.printGoodbyeMessage();

        //This line is needed after I add javaFX. I need to force the System.exit
        System.exit(0);

    }


    protected String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        helper = System.out;
        System.setOut(ps);
        //FastReader fr = new FastReader();//Handles input by the user
        TaskManager manager = new TaskManager();
        Ui ui = new Ui(manager);

        manager.loadExistingData();

        if (input.contains("bye")) {
            ui.printGoodbyeMessage();

        } else {
            ui.handleInputs(input);
        }

        System.out.flush();
        System.setOut(helper);

        return baos.toString();

    }


}






