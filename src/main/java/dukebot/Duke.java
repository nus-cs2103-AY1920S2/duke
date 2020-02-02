package dukebot;

import dukebot.command.Command;
import dukebot.command.Parser;
import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class Duke {
    private static final String PATH = "./dukeStore.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Initialises Duke.
     */
    public Duke(boolean withGui) {
        storage = new Storage();
        ui = new Ui(withGui);

        HashMap<String, String> loadedAliasMap = null;
        try {
            loadedAliasMap = storage.loadAlias();
        } catch (DukeException e) {
            // Can ignore
            // ui.sayLine(e.getErrorLineName());
        }
        if (loadedAliasMap == null) {
            parser = new Parser();
        } else {
            parser = new Parser(loadedAliasMap);
        }

        try {
            ArrayList<Task> taskArrayList = storage.loadTaskArrayList();
            tasks = new TaskList(taskArrayList);
            if (withGui) {
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
    }

    /**
     * Gets the current UI for text output.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Generates Response for GUI.
     */
    public String getResponse(String input) {
        ui.resetGuiOutput();
        Command c = parser.parse(input);
        c.execute(tasks, ui, storage);

        if (c.isExit()) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ie) {
                        System.exit(0);
                    }
                    System.exit(0);
                }
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
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        System.exit(0);
    }
}