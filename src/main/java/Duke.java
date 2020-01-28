import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Represents main body for Duke to run
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     *
     * @param listPath Relative file path for where the task list is stored
     * @param arrayPath Relative file path for where the task array is stored
     */
    public Duke(String listPath, String arrayPath) {
        ui = new Ui();
        storage = new Storage(listPath, arrayPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Sets up and runs Duke to begin accepting user commands. Send 'bye' to close the program.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        String input;
        while (!isBye) {
            input = scanner.nextLine();
            isBye = Parser.isBye(input);

            try {
                String command = Parser.parseCommand(input);
                String[] inputArr = input.split(" ");
                try {
                    if (command.equals("list")) {
                        ui.showList(storage.loadList());
                    } else if (command.equals("done")) {
                        int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                        Task selected = tasks.getTaskList().get(taskIndex);
                        tasks.markDone(taskIndex);
                        ui.showDoneTask(selected);
                        storage.save(tasks.getTaskList());

                    } else if (command.equals("delete")) {
                        int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                        Task selected = tasks.getTaskList().get(taskIndex);
                        tasks.deleteTask(taskIndex);
                        ui.showDeleteTask(selected, tasks.getTaskList());
                        storage.save(tasks.getTaskList());

                    } else {
                        if (command.equals("todo")) {
                            Todo added = tasks.createTodo(inputArr);
                            ui.showAddTask(added, tasks.getTaskList());
                            storage.save(tasks.getTaskList());

                        } else if (command.equals("event")) {
                            Event added = tasks.createEvent(input);
                            ui.showAddTask(added, tasks.getTaskList());
                            storage.save(tasks.getTaskList());

                        } else if (command.equals("deadline")) {
                            Deadline added = tasks.createDeadline(input);
                            ui.showAddTask(added, tasks.getTaskList());
                            storage.save(tasks.getTaskList());
                        }
                    }
                } catch (IOException e) {
                    ui.showError("Honk! Something went wrong.");
                } catch (GooseTaskExistenceException | GooseEmptyDescriptionException | GooseIllegalFormatException e) {
                    ui.showError(e.getMessage());
                }
            } catch (GooseUnrecognisedException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        new Duke("../data/duke.txt", "../data/mainList.txt").run();
    }
}
