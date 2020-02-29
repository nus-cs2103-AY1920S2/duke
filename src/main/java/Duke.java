public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new instance of the class Duke.
     */

    public Duke() {
        storage = new Storage("/duke/out/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    String getResponse(String input) {
        Parser.addList(tasks);
        return Parser.parse(input);
    }

    public void saveFile() {
        storage.writeBack("/duke/out/duke.txt", tasks);
    }
}
