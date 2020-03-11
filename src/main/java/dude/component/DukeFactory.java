package dude.component;

public class DukeFactory {
    /**
     * Creates a new Duke chat bot instance and injects Gui, TextStorage and TaskList instances into it.
     *
     * @param mainWindow the actual GUI that Gui will connect to.
     * @return a new Duke chat bot.
     */
    public static Duke createDuke(MainWindow mainWindow) {
        IUserInterface ui = new Gui(mainWindow);
        IStorage storage = new TextStorage();
        TaskList tasks = storage.restoreSession(ui);
        return new Duke(storage, tasks, ui);
    }
}
