package dukebot;

import dukebot.command.Command;
import dukebot.command.Parser;
import dukebot.contactlist.ContactList;
import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;

import java.util.ArrayList;

/**
 * Main logic.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private AppStorage appStorage;

    /**
     * Initialises Duke.
     *
     * @param hasGui Whether GUI mode is used.
     */
    public Duke(boolean hasGui) {
        storage = new Storage();
        ui = new Ui(hasGui);

        // Load Aliases
        Parser parserTemp;
        try {
            parserTemp = new Parser(storage.loadAlias());
        } catch (DukeException e) {
            // Can ignore the exception message
            parserTemp = new Parser();
        }
        parser = parserTemp;

        // Load Contacts
        ContactList contactList;
        try {
            contactList = new ContactList(storage.loadContactArrayList());
        } catch (DukeException e) {
            // Can ignore the exception message
            contactList = new ContactList(new ArrayList<>());
        }

        TaskList tasks = loadTaskList(ui, storage, hasGui);
        appStorage = new AppStorage(tasks, contactList);
    }

    /**
     * Loads the task list from storage.
     */
    private TaskList loadTaskList(Ui ui, Storage storage, Boolean hasGui) {
        TaskList tasks;
        try {
            ArrayList<Task> taskArrayList = storage.loadTaskArrayList();
            tasks = new TaskList(taskArrayList);
            if (hasGui) {
                ui.showWelcomeGui();
                ui.getDukeVoice().playVoice();
            } else {
                ui.showWelcome();
            }
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
            ArrayList<Task> taskArrayList = new ArrayList<>();
            tasks = new TaskList(taskArrayList);
            try {
                storage.saveTaskList(tasks);
            } catch (DukeException g) {
                ui.sayLine(g.getErrorLineName());
            }
        }
        return tasks;
    }

    /**
     * Gets the current UI for text output.
     *
     * @return The Ui being used.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Generates Response for GUI.
     *
     * @param input The input string to parse.
     * @return String to say.
     */
    public String getResponse(String input) {
        ui.resetGuiOutput();
        Command c = parser.parse(input);
        c.execute(appStorage, ui, storage);

        if (c.isExit()) {
            // Hack to delay program close.
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ie) {
                    System.exit(0);
                }
                System.exit(0);
            }).start();
        }

        ui.getDukeVoice().playVoice();
        return ui.getGuiOutput();
    }

    /**
     * Runs duke without GUI.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parse(fullCommand);
            c.execute(appStorage, ui, storage);
            isExit = c.isExit();
        }
        System.exit(0);
    }
}