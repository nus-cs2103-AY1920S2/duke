import java.util.Arrays;
import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static int i;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        tasks = new TaskList();

        ui.welcome();
        String command = "";
        Scanner sc = ui.getScanner();

        while (!(command).equalsIgnoreCase(Operation.BYE.toString())) {
            String[] current = sc.nextLine().split(" ");
            command = current[0];
            if ((command).equalsIgnoreCase(Operation.BYE.toString())) {
                ui.clean();
                break;
            }

            if (command.equalsIgnoreCase(Operation.LIST.toString())) {
                ui.showTasks(tasks);

            } else if (command.equalsIgnoreCase(Operation.TODO.toString()) || command.equalsIgnoreCase(Operation.DEADLINE.toString())
                    || command.equalsIgnoreCase(Operation.EVENT.toString())) {
                try {
                    tasks.addTask(current, storage);
                } catch (DukeException ex) {
                    ui.showMessage(Arrays.asList(ex.getMessage()));
                }

            } else if (command.equalsIgnoreCase(Operation.DONE.toString())) {
                int value = Integer.parseInt(current[1]);
                try {
                    Task cur = tasks.get(value - 1);
                    cur.markAsDone();
                    tasks.deleteTask(value, storage);
                    tasks.addTask(value - 1, cur);
                    StringBuilder sb = new StringBuilder();
                    for (Task t : tasks.getTasks()) {
                        sb.append(t.print() + "\n");
                    }
                    storage.writeToFile(sb.toString());
                    ui.taskMarkDone(cur);
                } catch (IndexOutOfBoundsException ex) {
                    ui.taskNumberError();
                }

            } else if (command.equalsIgnoreCase(Operation.DELETE.toString())) {
                try {
                    tasks.deleteTask(Integer.parseInt(current[1]), storage);
                } catch (IndexOutOfBoundsException ex) {
                    ui.taskNumberError();
                }

            } else {
                try {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (Exception ex) {
                    ui.showMessage(Arrays.asList(ex.getMessage()));
                }
            }
        }

    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }

}
