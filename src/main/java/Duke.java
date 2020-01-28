import commands.Command;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class Duke {
	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	public Duke(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			tasks = storage.load();
		} catch (Exception e) {
			ui.showLoadingError();
			tasks = new TaskList();
		}
	}

	public void run() {
		ui.showWelcomeMessage(tasks);
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				ui.showLine(); // show the divider line ("_______")
				Command c = Parser.parse(fullCommand);
				c.execute(tasks, storage);
				isExit = c.isExit();
			} /*catch (Exception e) {
                ui.showError(e.getMessage());
            } */ finally {
				ui.showLine();
			}
		}
		ui.showGoodbyeMessage();
	}

	public static void main(String[] args) {
		new Duke("data/tasks.txt").run();
	}
}


