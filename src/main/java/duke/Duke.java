/*
 * @author leslieharland
 */

package duke;


import duke.command.Operation;
import duke.storage.Storage;
import duke.task.SearchTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;
import java.util.Scanner;


/**
 * The Class Duke.
 */
public class Duke {

    /**
     * The i.
     */
    static int i;

    /**
     * The storage.
     */
    private Storage storage;

    /**
     * The tasks.
     */
    private TaskList tasks;

    private TaskList temp;

    private TaskList findTasks;

    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     *
     * @param filePath the file path
     */
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

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }

    /**
     * Run.
     */
    public void run() {
        tasks = new TaskList();
        temp = new TaskList();


        ui.welcome();
        String command = "";
        Scanner sc = ui.getScanner();

        while (sc.hasNextLine() && !(command).equalsIgnoreCase(Operation.BYE.toString())) {
            String[] current = sc.nextLine().split(" ");
            command = current[0];
            if ((command).equalsIgnoreCase(Operation.BYE.toString())) {
                ui.clean();
                break;
            }

            if (command.equalsIgnoreCase(Operation.LIST.toString())) {
                temp.getTasks().clear();
                tasks = storage.loadTasks();
                ui.showTasks(tasks, false);

            } else if (command.equalsIgnoreCase(Operation.TODO.toString())
                    || command.equalsIgnoreCase(Operation.DEADLINE.toString())
                    || command.equalsIgnoreCase(Operation.EVENT.toString())) {
                try {
                    tasks.addTask(current, storage);
                } catch (DukeException ex) {
                    ui.showMessage(Arrays.asList(ex.getMessage()));
                }

            } else if (command.equalsIgnoreCase(Operation.FIND.toString())) {
                temp = storage.findTasks(current[1]);
                ui.showTasks(temp, true);
            } else if (command.equalsIgnoreCase(Operation.DONE.toString())) {
                int value = Integer.parseInt(current[1]);
                try {
                    Task cur = tasks.get(value - 1);
                    StringBuilder sb = new StringBuilder();
                    if (!temp.getTasks().isEmpty()) {
                        cur = tasks.get(((SearchTask) temp.get(value - 1)).getKey());


                    }
                    cur.markAsDone();
                    tasks.deleteTask(value, storage);
                    tasks.addTask(value - 1, cur);
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
                    int keyToDelete = Integer.parseInt(current[1]);
                    if (tasks.get(0) instanceof SearchTask) {
                        keyToDelete = ((SearchTask) tasks.get(keyToDelete)).getKey();
                    }
                    tasks.deleteTask(keyToDelete, storage);
                } catch (IndexOutOfBoundsException ex) {
                    ui.taskNumberError();
                }

            } else {
                try {
                    throw new DukeException(
                            " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (Exception ex) {
                    ui.showMessage(Arrays.asList(ex.getMessage()));
                }
            }
        }

    }

}
