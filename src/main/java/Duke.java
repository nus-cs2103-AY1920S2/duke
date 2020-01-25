public class Duke {

    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) {
        // Init Duke
        Duke d = new Duke();
        // Greet user on start
        Ui.Greet();
        // Duke's behaviour loop
        d.Loop();
    }

    Duke() {
        storage = new Storage();
        taskList = new TaskList();
        storage.Load(taskList);
    }

    private void Exit() {
        Ui.SayBye();
        storage.SaveTaskListToFile(taskList);
    }

    private void Loop() {
        boolean is_exiting;
        Command c;
        do {
            String fullCommand = Ui.ReadCommand();
            c = Parser.parse(fullCommand);
            c.execute(taskList, storage);
            is_exiting = c.isExit();
        } while (!is_exiting);

        Exit();
    }
}
