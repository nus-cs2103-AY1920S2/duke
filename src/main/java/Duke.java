import java.nio.file.Paths;

public class Duke {
    static private Ui ui;
    static private Storage storage;
    static private TaskList taskList;
    static private Parser parser;
    static String dataFilePath = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "data/task_data.txt").toString();

    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage(dataFilePath);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ui.print("Hello from\n" + logo);
        ui.print("NOTE: For all date/time input, please use the DD-MM-YYYY HH:MM format.");

        try {
            taskList.setList(storage.getList());
        } catch (Exception e) {
            taskList = new TaskList(ui, storage);
        }
        parser = new Parser(taskList);

        ui.print("Hello! I'm Duke the dude.\nHow can I serve you?");
        ui.setInput(System.in);
        String input;
        while (true) {
            input = ui.getLine();
            try {
                if (input.equals("bye")) {
                    exit();
                    break;
                } else {
                    parser.parseInput(input);
                }
            } catch (Exception ex) {
                ui.showError(ex);
            }
        }
    }

    public static void exit() {
        ui.print("Bye. Hope to serve you again soon!");
    }
}