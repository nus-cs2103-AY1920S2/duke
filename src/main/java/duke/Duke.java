package duke;

/**
 * Creates Duke Object with filepath
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TasksNum tasksnum;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasksnum = new TasksNum();
        try {
            this.tasks = new TaskList(storage.readFile());
            tasksnum.setNum(tasks.getSize() - 1);
            tasks.deleteTask(tasks.getTask(tasksnum.getNum()));
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Executes the Duke object
     */
    public void run() {
        System.out.println(ui.greet());
        boolean isExit = false;
        while (isExit == false) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, storage, ui, tasksnum));
                //System.out.println(tasksnum);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
        //System.out.println("Hi");
    }
}

