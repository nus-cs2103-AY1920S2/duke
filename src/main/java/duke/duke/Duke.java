package duke;

/**
 * Creates Duke Object with filepath.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TasksNum tasksnum;

    /**
     * Initialise Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasksnum = new TasksNum();
        try {
            this.tasks = new TaskList(storage.readFile());
            //System.out.println(tasks.getSize());
            tasksnum.setNum(Integer.parseInt(tasks.getTask(tasks.getSize() - 1).getD()));
            //System.out.println(tasksnum.getNum());
            tasks.deleteTask(tasks.getTask(tasks.getSize() - 1),tasksnum);
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Executes the Duke object.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui, tasksnum);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}


