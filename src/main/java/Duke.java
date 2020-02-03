import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;


public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke(Paths.get("data", "duke.txt"));
        newDuke.run();
    }

    public void run() {
        ui.showGreeting();

        loop:
        while (true) {
            try {
                String input = ui.getInput();
                Parser instruction = new Parser(input);
                Command command = instruction.getCommand();

                switch (command) {
                case dateE:
                    break loop;
                case DONE:
                    int doneTaskNum = Integer.parseInt(instruction.getParameter());
                    tasks.setAsDone(doneTaskNum);
                    ui.showDoneTask(tasks.getTask(doneTaskNum));
                    break;
                case TODO:
                    Task newToDo = new ToDo(instruction.getDescription());
                    tasks.addToTasks(newToDo);
                    ui.showAddedTask(tasks, newToDo);
                    break;
                case DEADLINE:
                    Task newDeadline = new Deadline(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newDeadline);
                    ui.showAddedTask(tasks, newDeadline);
                    break;
                case EVENT:
                    Task newEvent = new Event(instruction.getDescription(), instruction.getDate());
                    tasks.addToTasks(newEvent);
                    ui.showAddedTask(tasks, newEvent);
                    break;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case DELETE:
                    int delTaskNum = Integer.parseInt(instruction.getParameter());
                    Task toBeDeleted = tasks.getTask(delTaskNum);
                    tasks.deleteFromTasks(delTaskNum);
                    ui.showDeletedTask(tasks, toBeDeleted);
                    break;
                default:
                    ;
                }

            } catch (DukeException e) {
                ui.showError(e);
                System.out.print("> ");
            }
        }

        storage.update(tasks);
        ui.showExitMessage();
    }

//    public static void doInstructions(String input) throws DukeException {
//        String command = getCommand(input);
//
//        if (command.equals("list")) {
//            // show list from ui
//        } else if (command.equals("done")) {
//            int taskNum = Integer.parseInt(input.split(" ")[1]);
//            // check for
//            // 1. invalid task num
//            // 2. empty task num
//            // 3. if task is already done?
//            // implement ui class? if have time to print out done statement
//            tasks.get(taskNum - 1).markAsDone();
//            ui.showDone(tasks.get(taskNum - 1));
//        } else if (command.equals("delete")) {
//            int taskNum = Integer.parseInt(input.split(" ")[1]);
//            String desc = tasks.get(taskNum - 1).toString();
//            tasks.remove(taskNum - 1);
//            numTasks -= 1;
//            // show deleted from ui
//        } else {
//            Task newTask;
//            // check for
//            // 1. no description
//            // 2. no time
//            if (command.equals("todo")) {
//                newTask = new ToDo(getDescription(input));
//            } else if (command.equals("deadline")) {
//                newTask = new Deadline(getDescription(input), getTime(input));
//            } else { // (command.equals("event")) {
//                newTask = new Event(getDescription(input), getTime(input));
//            }
//
//            tasks.add(newTask);
//            // show added from ui
//        }
//    }
}
