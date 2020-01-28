import commands.Command;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * The class representation of the AI bot that helps keep track of the user's todo list.
 */
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

	/**
	 * The main program execution that will take in the user's input and act on it.
	 */
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

	/**
	 * Main program that runs the Duke program.
	 *
	 * @param args Relevant command line arguments.
	 */
	public static void main(String[] args) {
		new Duke("data/tasks.txt").run();
	}
}


