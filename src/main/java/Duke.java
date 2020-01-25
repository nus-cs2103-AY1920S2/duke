public class Duke {
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    public TaskList getTaskList() {
        return tasks;
    }

    private void run() {
        ui.printWelcome();
        while (true) {
            String input;
            try {
                input = ui.readInput();
            } catch (DukeNoSuchInputException e) {
                break;
            }
            Command command = new Command(input);
            if (command.getCommandName().equals("bye")) {
                break;
            }
            try {
                String message = command.execute(this);
                ui.print(message);
            } catch (DukeNoCommandException e) {
                continue;
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
        ui.printGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
        System.exit(0);
    }
}
