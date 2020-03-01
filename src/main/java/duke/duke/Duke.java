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
            //System.out.println(tasks.getSize());
            tasksnum.setNum(Integer.parseInt(tasks.getTask(tasks.getSize() - 1).getD()));
            //System.out.println(tasksnum.getNum());
            tasks.deleteTask(tasks.getTask(tasks.getSize()-1),tasksnum);
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
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine()); // show the divider line ("_______")
                //System.out.println(tasksnum.getNum());
                Command c = Parser.parse(fullCommand);
                //System.out.println(tasks.getSize());
                //System.out.println(tasks.getTask(0) + "HEY");
                System.out.println(c.execute(tasks, storage, ui, tasksnum));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("...duke/data/tasks.txt").run();
        //System.out.println("Hi");
    }
}


