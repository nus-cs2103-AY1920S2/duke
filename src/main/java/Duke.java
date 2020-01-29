/**
 * Contains a product name Duke, a personal assistant chat bot that keeps track of various tasks that needs
 * to be done. It classifies tasks into deadline, todo and event.
 * It can add, delete, list all the tasks, find task with a keyword and mark them as done
 */
public class Duke {

    public static void main(String[] args) {

        FastReader fr = new FastReader();//Handles input by the user
        TaskManager manager = new TaskManager();
        Ui ui = new Ui(manager);

        ui.introduction();
        manager.loadExistingData();

        String textEntered = fr.nextLine();

        while(!ui.hasEnded){
            ui.handleInputs(textEntered);
            if (textEntered.equals("bye")){
                break;
            }
            textEntered = fr.nextLine();
        }

        ui.printGoodbyeMessage();

    }
}




