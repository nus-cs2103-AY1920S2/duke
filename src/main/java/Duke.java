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

    public String run(String input) throws IOException, DukeException {
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
                    //return "The list is empty.";
                } else {
                    String tmpList = "";
                    for (int i = 0; i < tasks.getDukeList().size(); i++) {
                        tmpList = tmpList + (i + 1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                    return tmpList;
                }
            case DONE:
                num = parser.parseNum(fullCommand, tasks);
                assert num <= -3 : "Unknown error has occurred.";

                if (num > 0) {
                    if (num > tasks.getSize()) {
                        throw new DukeException("unable to mark done", num);
                    }

                    tasks.markDone(num);
                    return ui.printMarkDone(tasks, num);
                } else {
                    if (num == 0) {
                        throw new DukeException("unable to mark done", num);
                    } else if (num == -1) {
                        throw new DukeException("done");
                    } else {
                        throw new DukeException("done argument too much");
                    }
                }
            case DELETE:
                num = parser.parseNum(fullCommand, tasks);
                assert num <= -3 : "Unknown error has occurred.";

                if (num > 0) {
                    if (num > tasks.getSize()) {
                        throw new DukeException("unable to delete from list", num);
                    }

                    Task taskToRemove = tasks.getDukeList().get(num - 1);
                    tasks.removeTask(num);
                    return ui.printTaskRemoved(taskToRemove, num, tasks);
                } else {
                    if (num == 0) {
                        throw new DukeException("unable to delete from list", num);
                    } else if (num == -1) {
                        throw new DukeException("delete");
                    } else {
                        throw new DukeException("delete argument not found");
                    }
                }
            case TODO:
                String tmp = parser.parseDescription(fullCommand);

                if (tmp.equals("-1Error:0b9d4e")) {
                    throw new DukeException("todo");
                }

                t = new ToDo(tmp);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);
            case EVENT:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                if (desc.equals("-1Error:21006a")) {
                    throw new DukeException("event");
                }

                by = parser.parseBy(fullCommand);
                if (by.equals("-2error:21f3ad")) {
                    throw new DukeException("event", "", "no slash");
                }

                t = new Event(desc, by);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);

            case DEADLINE:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                if (desc.equals("-1Error:21006a")) {
                    throw new DukeException("deadline");
                }
                by = parser.parseBy(fullCommand);
                if (by.equals("-2error:21f3ad")) {
                    throw new DukeException("deadline", "", "no slash");
                }

                t = new Deadline(desc, by);
                tasks.addTask(t);
                return ui.printTaskAdded(tasks, t);
            case FIND:
                String find = parser.parseDescription(fullCommand);
                if (find.equals("-1Error:0b9d4e")) {
                    throw new DukeException("find");
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
                throw new DukeException("enter command");
            default:
                throw new DukeException("Don't understand");
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

