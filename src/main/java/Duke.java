import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public SaveToFile saveToFile;
    public Ui ui;
    public TaskList tasks;
    /**
     * Constructor for duke.
     *
     * @param filePath File path for duke's task list.
     */

//    public Duke() {
//
//    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.saveToFile = new SaveToFile();
        this.tasks = new TaskList(saveToFile.loadList(filePath));
    }

    /**
     * Run duke.
     */
//    public void run() {
//        ui.showWelcome();
//        Scanner sc = new Scanner(System.in);
//
//        while (sc.hasNext()) {
//            String userInput = sc.nextLine();
//            String[] arrString = userInput.split(" ", 2);
//            try {
//                if (arrString[0].equalsIgnoreCase("bye")) {
//                    ui.goodbyeMessage();
//                    break;
//                } else if (arrString[0].equalsIgnoreCase("list")) {
//                    if (tasks.getTaskListSize() == 0) {
//                        ui.emptyList();
//                    } else {
//                        StringBuilder sb = new StringBuilder();
//                        ui.listCommand(tasks, sb);
//                        saveToFile.usingFileWriter(sb.toString());
//                    }
//                } else if (arrString[0].equalsIgnoreCase("done")) {
//                    try {
//                        int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
//                        if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
//                            ui.doneTask(tasks, taskNumber);
//                        } else {
//                            ui.invalidTask();
//                        }
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        throw new DukeException(ui.missingTaskNumber());
//                    }
//
//                } else if (arrString[0].equalsIgnoreCase("delete")) {
//                    try {
//                        int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
//                        if (taskNumber >= 0 && taskNumber < tasks.getTaskListSize()) {
//                            ui.deletedTask(tasks, taskNumber);
//                        } else {
//                            ui.invalidTask();
//                        }
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        throw new DukeException(ui.missingTaskNumber());
//                    }
//
//                } else if (arrString[0].equalsIgnoreCase("todo")) {
//                    try {
//                        Todo todo = new Todo(arrString[1]);
//                        tasks.addTask(todo);
//                        ui.taskInList(tasks.getTaskListSize());
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        throw new DukeException(ui.incompleteCommand("Todo"));
//                    }
//                } else if (arrString[0].equalsIgnoreCase("event")) {
//                    try {
//                        String[] eventString = arrString[1].split("/");
//                        Event event = new Event(eventString[0].strip(), eventString[1].substring(2).strip());
//                        tasks.addTask(event);
//                        ui.taskInList(tasks.getTaskListSize());
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        throw new DukeException(ui.incompleteCommand("Event"));
//                    }
//                } else if (arrString[0].equalsIgnoreCase("deadline")) {
//                    try {
//                        String[] deadlineString = arrString[1].split("/");
//                        Deadline deadline = new Deadline(deadlineString[0].strip(),
//                                deadlineString[1].substring(2).strip());
//                        tasks.addTask(deadline);
//                        ui.taskInList(tasks.getTaskListSize());
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        throw new DukeException(ui.incompleteCommand("Deadline"));
//                    }
//                } else if (arrString[0].equalsIgnoreCase("Find")) {
//                    try {
//                        tasks.findTask(arrString[1]);
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        throw new DukeException(ui.incompleteCommand("Find"));
//                    }
//                } else {
//                    throw new DukeException(ui.invalidCommand());
//                }
//
//
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//
//        new Duke("./out.txt").run();
//    }
}

