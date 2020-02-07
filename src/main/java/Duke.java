import java.io.IOException;

/**
 * Duke is the main class of the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean isGoodbye = false;

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

    public String run(String input) throws IOException {
        String fullCommand = input;
        String newString = "";
        String[] splitBySpace;
        int num;
        Task t;
        String desc;
        String by;

        if (fullCommand.equals("bye")) {
            storage.writeData(tasks);
            isGoodbye = true;
            return ui.goodbye();
        }

        try {
            Command cmd = parser.parseCommand(fullCommand);
            newString = "";
            desc = "";
            by = "";

            switch (cmd) {
            case LIST:
                if (tasks.getSize() == 0) {
                    return "The list is empty.";
                } else {
                    String listOfTasks = "";
                    for (int i = 0; i < tasks.getDukeList().size(); i++) {
                        listOfTasks = listOfTasks + (i + 1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                    return listOfTasks;
                }
            case DONE:
                num = parser.parseNum(fullCommand, tasks);
                assert num <= -3 : "Unknown error has occurred.";

                if (num > 0) {
                    if (num > tasks.getSize()) {
                        throw new DukeException("Unable to mark task #" + num +
                                " as done. Please try again with a valid task number.");
                    }

                    tasks.markDone(num);
                    return ui.printMarkDone(tasks, num);
                } else {
                    if (num == 0) {
                        throw new DukeException("Unable to mark task #" + num +
                                " as done. Please try again with a valid task number.");
                    } else if (num == -1) {
                        throw new EmptyDescriptionException("done");
                    } else {
                        throw new DukeException("Please only provide one argument to mark as done.");
                    }
                }
            case DELETE:
                num = parser.parseNum(fullCommand, tasks);
                assert num <= -3 : "Unknown error has occurred.";

                if (num > 0) {
                    if (num > tasks.getSize()) {
                        throw new DukeException("Unable to delete " + num +
                                " from the task. Please try again with a valid task number.");
                    }

                    Task taskToRemove = tasks.getDukeList().get(num - 1);
                    tasks.removeTask(num);
                    return ui.printTaskRemoved(taskToRemove, num, tasks);
                } else {
                    if (num == 0) {
                        throw new DukeException("Unable to delete " + num +
                                " from the task. Please try again with a valid task number.");
                    } else if (num == -1) {
                        throw new EmptyDescriptionException("delete");
                    } else {
                        throw new DukeException("Please provide a valid number to delete.");
                    }
                }
            case TODO:
                String tmp = parser.parseDescription(fullCommand);

                if (tmp.equals("-1Error:0b9d4e")) {
                    throw new EmptyDescriptionException("todo");
                }

                t = new ToDo(tmp);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);
            case EVENT:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                if (desc.equals("-1Error:21006a")) {
                    throw new EmptyDescriptionException("event");
                }

                by = parser.parseBy(fullCommand);
                if (by.equals("-2error:21f3ad")) {
                    throw new DukeException("Please provide a valid deadline. " +
                            "For example, 「event read book /by 2020-09-20」.");
                }

                t = new Event(desc, by);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);

            case DEADLINE:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                if (desc.equals("-1Error:21006a")) {
                    throw new EmptyDescriptionException("deadline");
                }
                by = parser.parseBy(fullCommand);
                if (by.equals("-2error:21f3ad")) {
                    throw new DukeException("Please provide a valid deadline. " +
                            "For example, 「deadline read book /by 2020-09-20」.");
                }

                t = new Deadline(desc, by);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);
            case FIND:
                String find = parser.parseDescription(fullCommand);
                if (find.equals("-1Error:0b9d4e")) {
                    throw new EmptyDescriptionException("find");
                }
                String taskL = "";

                for (int i = 0; i < tasks.getSize(); i++) {
                    Task cur = tasks.getDukeList().get(i);

                    if (cur.getDescription().contains(find)) {
                        taskL = taskL + (i+1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                }

                return ui.printMatchingTask(taskL.trim(), find);
            case ENTERCOMMAND:
                throw new DukeException("Please enter a command");
            default:
                throw new DukeException("Sumimasen, I can't understand what chu talking about. Try again?");
            }

        } catch (DukeException e) {
            return e.toString();
        }

    }

    public Ui getUi() {
        return ui;
    }

    public boolean toClose() {
        if (isGoodbye) {
            return true;
        } else {
            return false;
        }
    }
}

