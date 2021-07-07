package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.FindTask;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The Class Duke.
 */
public class Duke {

    /**
     * The file path.
     */
    public String filePath;

    /**
     * The storage.
     */
    private Storage storage;

    /**
     * The task list.
     */
    private TaskList taskList;

    /**
     * The different previous version.
     */
    private Undo versionControl;

    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     */
    public Duke() {
        ui = new Ui();
        this.filePath = "data/duke.txt";
        this.storage = new Storage(filePath);
        this.versionControl = new Undo();
        this.storage.setVersionControl(versionControl);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
            storage.createFile();
            versionControl.startFresh();

        }
    }

    /**
     * process the command and output String.
     *
     * @param input the command by user
     * @return string which is the response to the command
     */
    public String processCommand(String input) {
        input = input.trim();
        assert input.length() > 0 : "Invalid command";
        try {
            input = input.toLowerCase();
            if (input.equalsIgnoreCase("bye")) {
                return this.ui.bye();
            } else if (input.equalsIgnoreCase("undo")) {
                if (versionControl.getSize() >= 2) {
                    versionControl.undo(this.taskList, this.storage);
                    return this.taskList.list();
                } else {
                    return new DukeException("OOPS, this is the latest version\n").toString();
                }
            } else if (input.equalsIgnoreCase("list")) {
                return this.taskList.list();
            } else if (input.trim().startsWith("done ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5));
                    return this.taskList.done(taskNumber, this.storage);
                } catch (StringIndexOutOfBoundsException e) {
                    return new DukeException("OOPS!!! Done format is wrong\n").toString();
                } catch (NumberFormatException e) {
                    return new DukeException("OOPS!!! Done format is wrong\n").toString();
                }
            } else if (input.startsWith("delete ")) {
                try {
                    assert Character.isDigit(input.substring(7).toCharArray()[0]) : "Wrong Input";
                    int taskNumber = Integer.parseInt(input.substring(7));
                    assert taskNumber >= 0 : "Invalid task number";
                    return this.taskList.delete(taskNumber, this.storage);
                } catch (StringIndexOutOfBoundsException e) {
                    return new DukeException("OOPS!!! Delete format is wrong").toString();
                } catch (NumberFormatException e) {
                    return new DukeException("OOPS!!! Delete format is wrong").toString();
                }
            } else if (input.startsWith("find ")) {
                String[] keyWords = input.substring(5).trim().split(" ");
                assert keyWords.length <= 0 : "Invalid input keywords";
                FindTask findTask = new FindTask(this.taskList.getTaskList());
                findTask.search(keyWords);
                return findTask.list();
            } else {
                if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    if (input.startsWith("todo ")) {
                        return this.taskList.addTodo(input, this.storage);
                    } else if (input.startsWith("deadline ")) {
                        return this.taskList.addDeadline(input, this.storage);
                    } else if (input.startsWith("event ")) {
                        return this.taskList.addEvent(input, this.storage);
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            }
        } catch (DukeException e) {
            return e.toString();
        }
        return "";
    }

    /**
     * This method will be running throughout the entire session
     * It will listen to the incoming users's command and process the comment accordingly.
     *
     * @return a greeting string
     */
    public String greet() {
        return this.ui.greetLogo() + this.ui.greet();
    }


}
