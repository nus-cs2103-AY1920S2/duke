class Duchess {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    Duchess(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DuchessException e) {
            this.ui.printError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    void run() {
        this.ui.printWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command command = Parser.parse(fullCommand);
                command.execute.apply(fullCommand, this.taskList, this.ui, this.storage);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (DuchessException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }
}
