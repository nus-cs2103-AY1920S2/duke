package Duke.command;

import Duke.Storage;
import Duke.TaskList;
import Duke.exception.DukeException;
import Duke.task.Task;
import java.io.Serializable;
import java.util.List;

public abstract class Command implements Serializable {
    protected TaskList tl;
    protected List<Task> list; // derived from tl
    protected Storage storage; // derived from tl
    protected String taskDesc; // derived from tl
    protected List<Command> stats; // derived from tl
    protected Storage statStorage; // derived from tl

    /**
     * Default constructor for empty, exit and unknown command, which are not recorded in statistics.
     */
    public Command() {
    }

    /**
     * Constructor for command class with associated taskList.
     * @param tl taskList which command will operate on
     */
    public Command(TaskList tl) {
        this.tl = tl;
        this.list = tl.getTaskList();
        this.storage = tl.getTaskStorage();
        this.stats = tl.getStats();
        this.statStorage = tl.getStatStorage();
    }

    /**
     * Constructor for command class with associated taskList and task description.
     * @param tl taskList which command will operate on
     * @param taskDesc task description
     */
    public Command(TaskList tl, String taskDesc) {
        this.tl = tl;
        this.taskDesc = taskDesc;
        this.list = tl.getTaskList();
        this.stats = tl.getStats();
        this.storage = tl.getTaskStorage();
        this.statStorage = tl.getStatStorage();
    }

    public abstract String execute() throws DukeException;
}
