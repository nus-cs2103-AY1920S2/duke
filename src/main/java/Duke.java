import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private SaveToFile saveToFile;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for duke.
     *
     * @param filePath File path for duke's task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        saveToFile = new SaveToFile();
        ArrayList<Task> arr = new ArrayList<>();
        tasks = new TaskList(saveToFile.loadList(filePath));
    }

    /**
     * Run duke
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] arrString = userInput.split(" ", 2);
            try {
                if (arrString[0].equalsIgnoreCase("bye")) {
                    ui.goodbyeMessage();
                    break;
                } else if (arrString[0].equalsIgnoreCase("list")) {
                    ui.showLine();
                    if (tasks.getTaskListSize() == 0) {
                        ui.emptyList();
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < tasks.getTaskListSize(); i++) {
                            System.out.println(i + 1 + ". " + tasks.getTask(i).toString());
                            sb.append(tasks.getTask(i).saveToList() + "\n");
                        }
                        saveToFile.usingFileWriter(sb.toString());
                        ui.showLine();
                    }
                } else if (arrString[0].equalsIgnoreCase("done")) {
                    try {
                        int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
                            tasks.getTask(taskNumber).doneTask();
                            ui.doneTask();
                            System.out.println(tasks.getTask(taskNumber).toString());
                        } else {
                            ui.invalidTask();
                        }
                        ui.showLine();
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(ui.missingTaskNumber());
                    }

                } else if (arrString[0].equalsIgnoreCase("delete")) {
                    try {
                        int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
                            ui.deletedTask();
                            System.out.println(tasks.getTask(taskNumber).toString());
                            tasks.deleteTask(taskNumber);
                            ui.taskInList(tasks.getTaskListSize());
                        } else {
                            ui.invalidTask();
                        }
                        ui.showLine();
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(ui.missingTaskNumber());
                    }

                } else if (arrString[0].equalsIgnoreCase("todo")) {
                    try {
                        Todo todo = new Todo(arrString[1]);
                        tasks.addTask(todo);
                        ui.addedCommand();
                        System.out.println(todo.toString());
                        int listSize = tasks.getTaskListSize();
                        ui.taskInList(listSize);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(ui.incompleteCommand("Todo"));
                    }
                } else if (arrString[0].equalsIgnoreCase("event")) {
                    try {
                        String[] eventString = arrString[1].split("/");
                        Event event = new Event(eventString[0].strip(), eventString[1].substring(2).strip());
                        tasks.addTask(event);
                        ui.addedCommand();
                        System.out.println(event.toString());
                        ui.taskInList(tasks.getTaskListSize());
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(ui.incompleteCommand("Event"));
                    }
                } else if (arrString[0].equalsIgnoreCase("deadline")) {
                    try {
                        String[] deadlineString = arrString[1].split("/");
                        Deadline deadline = new Deadline(deadlineString[0].strip(), deadlineString[1].substring(2).strip());
                        tasks.addTask(deadline);
                        ui.addedCommand();
                        System.out.println(deadline.toString());
                        ui.taskInList(tasks.getTaskListSize());
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(ui.incompleteCommand("Deadline"));
                    }
                } else {
                    throw new DukeException(ui.invalidCommand());
                }


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        new Duke("./out.txt").run();
    }
}

