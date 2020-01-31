import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }


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
    public void handle(String string) throws DukeException{
        if (string.equals("list")) {
            ui.printList();
        } else {
            String type = string.split(" ")[0];
            if (type.equals("done") || type.equals("delete")) {
                int taskNo = Integer.parseInt(string.split(" ")[1]);
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
                Task task = tasks.createAndAddTask(type, string);
                ui.addMessage(task);
            }
        }
    }
}
