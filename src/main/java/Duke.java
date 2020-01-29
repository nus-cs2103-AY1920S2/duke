
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage taskStorage;
    private TaskList tasks;

    public Duke() {

        this.taskStorage = new Storage();
        this.tasks = new TaskList(taskStorage);
        this.ui = new Ui(tasks);
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        boolean trigger = true;

        while (trigger) {
            if (!ui.readLine(sc)) {
                trigger = false;
            }

        }
        taskStorage.storeToStorage(tasks.getList());

    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        new Duke().run();
    }


}












