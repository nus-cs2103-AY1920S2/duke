import DukeException.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showHello();
        while (true) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                ui.showBye();
                break;
            }
            String commandType = parser.getCommandType(command);
            try {
                switch (commandType) {
                case "list":
                    ui.showList(tasks);
                    break;
                case "todo":
                    String description = parser.todoDescription();
                    ui.showAdded(tasks.addTodo(description), tasks.getLength());
                    break;
                case "deadline":
                    String[] descByWhen = parser.deadlineParams();
                    ui.showAdded(tasks.addDeadline(descByWhen[0], descByWhen[1]), tasks.getLength());
                    break;
                case "event":
                    String[] descAtWhen = parser.eventParams();
                    ui.showAdded(tasks.addEvent(descAtWhen[0], descAtWhen[1]), tasks.getLength());
                    break;
                case "done":
                    int doneNum = parser.markDoneNum();
                    ui.showMarkedDone(tasks.markDone(doneNum));
                    break;
                case "delete":
                    int deleteNum = parser.markDoneNum();
                    ui.showDeleted(tasks.delete(deleteNum), tasks.getLength());
                    break;
                default:
                    throw new DukeUnknownInputException("Sorry but I do not recognise your command.");
                }
                storage.updateFile(tasks, tasks.getLength());
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Users/Min Suk/IdeaProjects/duke/data/tasks.txt").run();
    }
}