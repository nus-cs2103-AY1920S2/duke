package cathulhu;

import java.io.File;
import java.io.IOException;

import cathulhu.tasks.Task;

public class Cathulhu {

    private static final File TASKS_FILE = new File("./data/tasksFile.txt");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Cathulhu() {
        ui = new Ui();
        storage = new Storage();
        try{
            this.tasks = storage.loadTasksFile(TASKS_FILE);
        } catch (Exception e) {
            ui.printError(e);
        }
    }


    private void interact() {

        boolean byebye = false;
        ui.showWelcome();

        while (!byebye) {
            ui.printLine1();
            try {
                byebye = Parser.parse(tasks, ui);
                storage.writeTasksFile(TASKS_FILE, tasks);
            } catch (CathulhuException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.err.println(e);
                break;
            }
            ui.printLine2();
        }
        ui.showGoodbye();
    }


    public static void main(String[] args) {
        new Cathulhu().interact();
    }
}
