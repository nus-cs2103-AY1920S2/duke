package duke;

import java.time.LocalDate;
import java.io.IOException;

public class Duke {
    private static Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    private static final String DUKE_TXT_FILE_PATH = "data/duke.txt";

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        parser = new Parser();

        storage = new Storage(filePath);
        taskList = storage.load();
    }

    private  void addAndPrintTask(Task t, TaskList taskList) {
        taskList.addTask(t);

        ui.printLine("Got it! I've added this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }

    public void run() throws IOException {
        ui.printWelcomeMsg();

        String str1;

        String[] strArr;

        Task t;
        int index;

        while (true) {
            try {
                t = null;
                Command command = parser.parse(ui.readCmd(), taskList);

                switch (command.getCommandType()) {
                    case LIST_CMD:
                        ui.printLine("Here are your task(s):");

                        int len = taskList.getNumTasks();

                        for (int i = 0; i < len; ++i) {
                            t = taskList.getTask(i);
                            ui.printLine("    " + (i + 1) + ". " + t);
                        }
                        ui.printLine();
                        break;
                    case DONE_CMD:
                        index = Integer.parseInt(command.getParams()[0]) - 1;

                        t = taskList.getTask(index);
                        taskList.markAsDone(index);
                        ui.printLine("Nice! I've marked this task as done:" + Ui.LF + "    " + t + Ui.LF);
                        storage.saveTasksToFile(taskList);
                        break;
                    case DELETE_CMD:
                        index = Integer.parseInt(command.getParams()[0]) - 1;

                        t = taskList.getTask(index);
                        taskList.deleteTask(index);

                        ui.printLine("Noted! I've removed this task:" + Ui.LF + "    " + t + Ui.LF
                                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);

                        storage.saveTasksToFile(taskList);

                        break;
                    case TODO_CMD:
                        t = new Todo(command.getParams()[0]);
                        addAndPrintTask(t, taskList);
                        storage.saveTasksToFile(taskList);
                        break;
                    case DEADLINE_CMD:
                        t = new Deadline(command.getParams()[0], LocalDate.parse(command.getParams()[1]));
                        addAndPrintTask(t, taskList);
                        storage.saveTasksToFile(taskList);
                        break;
                    case EVENT_CMD:
                        t = new Event(command.getParams()[0], LocalDate.parse(command.getParams()[1]));
                        addAndPrintTask(t, taskList);
                        storage.saveTasksToFile(taskList);
                        break;
                    case BYE_CMD:
                        ui.printByeMsg();
                        break;
                }
            } catch (DukeException e) {
                ui.printLine(e + Ui.LF);
            }
        }
    }


    public static void main(String[] args) {
        try {
            System.out.println("edited");
            new Duke(DUKE_TXT_FILE_PATH).run();
        } catch (IOException e) {
            ui.printLine("Sorry, an IO error has occurred:");
        }
    }
}
