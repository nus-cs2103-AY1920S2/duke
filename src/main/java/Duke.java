import java.io.*;

/**
 * Duke is the main class of the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            //Load from storage
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/tasks.txt").run();
    }

    public void run() throws IOException, DukeException {
        ui.welcome();
        String fullCommand = ui.readCommand();
        String newString = "";
        String[] splitBySpace;
        int num;
        Task t;
        String desc;
        String by;

        while (!fullCommand.equals("bye")) {
            try {
                Command cmd = parser.parseCommand(fullCommand);
                newString = "";
                desc = "";
                by = "";

                if (cmd != null) {
                    switch (cmd) {
                        case LIST:
                            if (tasks.getSize() == 0) {
                                System.out.println("The list is empty.");
                            } else {
                                for (int i = 0; i < tasks.getDukeList().size(); i++) {
                                    System.out.println((i + 1) + "." + tasks.getDukeList().get(i));
                                }
                            }
                            break;
                        case DONE:
                            num = parser.parseNum(fullCommand, tasks);

                            if (num != -1) {
                                tasks.markDone(num);
                                ui.printMarkDone(tasks, num);
                            }
                            break;
                        case DELETE:
                            num = parser.parseNum(fullCommand, tasks);

                            if (num != -1) {
                                ui.printTaskRemoved(tasks, num);
                                tasks.removeTask(num);
                            }

                            break;
                        case TODO:
                            String tmp = parser.parseDescription(fullCommand);
                            t = new ToDo(tmp);
                            tasks.addTask(t);
                            ui.printTaskAdded(tasks, t);

                            break;
                        case EVENT:
                            desc = parser.parseDescOfEventDeadline(fullCommand);
                            by = parser.parseBy(fullCommand);

                            if (desc != null && by != null) {
                                t = new Event(desc, by);
                                tasks.addTask(t);
                                ui.printTaskAdded(tasks, t);
                            }

                            break;
                        case DEADLINE:
                            desc = parser.parseDescOfEventDeadline(fullCommand);
                            by = parser.parseBy(fullCommand);

                            if (desc != null && by != null) {
                                t = new Deadline(desc, by);
                                tasks.addTask(t);
                                ui.printTaskAdded(tasks, t);
                            }

                            break;
                        case FIND:
                            String find = parser.parseDescription(fullCommand);
                            String taskL = "";

                            for (int i = 0; i < tasks.getSize(); i++) {
                                Task cur = tasks.getDukeList().get(i);

                                if (cur.getDescription().contains(find)) {
                                    taskL = taskL + (i+1) + "." + tasks.getDukeList().get(i) + "\n";
                                }
                            }

                            ui.printMatchingTask(taskL.trim(), find);

                            break;
                        default:
                            break;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

            //Get next input
            fullCommand = ui.readCommand();
        }

        storage.writeData(tasks);
        ui.goodbye();
    }
}

