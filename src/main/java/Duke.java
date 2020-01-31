import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage store;
    private TaskList taskList;
    private Parser parser;

    public Duke(String fileName) {
        // Initialize Ui
        this.ui = new Ui();
        // Initialize storage
        this.store = new Storage(fileName);
        // Initialize taskList
        this.taskList =  new TaskList(this.store.loadArrayListStringFromFile());
        this.parser = new Parser();
    }

    public void run() {
        ui.sayHello();
        boolean endInput = false;
        while (!endInput) {
            try {
                String input = ui.readInput();
                Command command = parser.parse(input);
                command.execute(taskList, ui, store);
                endInput = command.isExit();
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
        // Create Scanner object to read for user input
        Scanner sc = new Scanner(System.in);

        // Only read and write to the file whenever there is a modification to the task

        ui.sayBye();
    }
    public static void main(String[] args) {
        new Duke("list.txt").run();
    }

}

