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
            try {
                String input = ui.readInput();
                Command command = Command.createCommand(input);
                String message = command.execute(this);
                ui.print(message);
            } catch (DukeNoSuchInputException | DukeProgramTerminatedException e) {
                break;
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
