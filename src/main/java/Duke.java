import java.util.ArrayList;
import dukebot.*;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;

/**
 * Main class.
 */
public class Duke {
    private static final String PATH = "./dukeStore.txt";

    private void run() {

        Storage storage = new Storage(PATH);
        ArrayList<Task> savedTasks = storage.loadFromFile();
        TaskList tasks = new TaskList(savedTasks);
        Ui ui = new Ui();

        ui.showWelcome();
        boolean running = true;
        while (running) {
            String[] inpArr = ui.readCommand();
            switch (inpArr[0]) {
            case "":
                ui.sayLine(LineName.NO_INPUT);
                break;
            case "Duke":
            case "duke":
            case "Master":
            case "master":
                ui.sayLine(LineName.DUKE);
                break;
            case "bye":
                running = false;
                break;
            case "list":
                ui.sayLine(LineName.LIST);
                if (tasks.size() == 0) {
                    ui.sayLine(LineName.LIST_EMPTY);
                } else {
                    ui.sayTasks(tasks.taskList);
                }
                break;
            case "done":
                if (inpArr.length == 1) {
                    ui.sayLine(LineName.DONE_EMPTY);
                } else {
                    try {
                        int taskInd = Integer.parseInt(inpArr[1]) - 1;
                        if (taskInd >= tasks.size() || taskInd < 0) {
                            ui.sayLine(LineName.DONE_OUT_OF_INDEX);
                        } else {
                            Task doneTask = tasks.getTask(taskInd);
                            if (doneTask.getDone()) {
                                ui.sayLine(LineName.DONE_ALREADY);
                            } else {
                                ui.doneSuccess(doneTask);
                                doneTask.setDone();
                                storage.saveToFile(tasks);
                            }
                        }
                    } catch (NumberFormatException e) {
                        ui.sayLine(LineName.NOT_A_NUMBER);
                    }
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    Task newTask = tasks.addNewTask(inpArr);
                    storage.saveToFile(tasks);
                    ui.newTask(newTask);
                } catch (DukeException e) {
                    ui.sayLine(e.getErrorLineName());
                }
                break;
            case "delete":
                if (inpArr.length == 1) {
                    ui.sayLine(LineName.DELETE_EMPTY);
                } else {
                    try {
                        int taskInd = Integer.parseInt(inpArr[1]) - 1;
                        Task task = tasks.deleteTask(taskInd);
                        if (task == null) {
                            ui.sayLine(LineName.DELETE_OUT_OF_INDEX);
                        } else {
                            ui.deleteSuccess(task);
                            storage.saveToFile(tasks);
                        }
                    } catch (NumberFormatException e) {
                        ui.sayLine(LineName.NOT_A_NUMBER);
                    }
                }
                break;
            default:
                ui.sayLine(LineName.INVALID_COMMAND);
                break;
            }
        }
        ui.sayLine(LineName.EXIT);
        System.exit(0);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}