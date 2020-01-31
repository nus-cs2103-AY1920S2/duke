import java.io.IOException;
import java.util.Scanner;

/**
 * The Duke class contains the main method.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * The constructor for the Duke class.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * The main method for Duke.
     * @param args For the main method.
     * @throws DukeException Throws DukeException.
     * @throws IOException Throws IOException.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }


    /**
     * To run the duke program.
     * @throws DukeException Throws DukeException.
     * @throws IOException Throws IOException.
     */
    public void run() throws DukeException, IOException {
        storage.loadData();
        ui.welcomeMessage();
        ui.reply("    What can I do for you");

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                handle(command);
            }
            catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }

        }

        storage.saveData();
        ui.reply("    Bye. Hope to see you again soon!");
    }

    /**
     * Handles the command provided by the user.
     * @param command The user command.
     * @throws DukeException Throws DukeException.
     */
    public void handle(String command) throws DukeException{
        if (command.equals("list")) {
            ui.printList();
        } else {
            String type = parser.getType(command);
            if (type.equals("done") || type.equals("delete")) {
                int taskNo = parser.getTaskNum(command);
                if (taskNo > tasks.numOfTasks() || taskNo <= 0) {
                    throw new DukeException("☹ OOPS!! Not a valid number");
                } else {
                    if (type.equals("done")) {
                        tasks.getTask(taskNo - 1).markAsDone();
                        ui.doneMessage(tasks.getTask(taskNo - 1));
                    } else {
                        ui.deleteMessage(tasks.getTask(taskNo - 1));
                        tasks.removeTask(taskNo - 1);
                        Task.totalTasks--;
                    }
                }
            } else {
                // create new task -> add to tasks -> reply
                Task task = tasks.createAndAddTask(type, command);
                ui.addMessage(task);
            }
        }
    }
}
