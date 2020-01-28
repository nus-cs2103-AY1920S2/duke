package dude;

import dude.command.Command;
import dude.command.CommandExecutionException;

import dude.component.IStorage;
import dude.component.TextStorage;

import dude.component.IUserInterface;
import dude.component.UI;

import dude.component.Parser;
import dude.component.ParsingException;

import dude.component.TaskList;

public class Dude {
    public static void main(String[] args) {
        Dude chatbot = new Dude();
        chatbot.serve();
    }

    private IStorage storage;
    private TaskList tasks;
    private IUserInterface ui;

    /** 
     * Initializes dude.Dude chatbot.
     * Greets the user.
     */
    public Dude() {
        this.ui = new UI();
        this.storage = new TextStorage();
        this.tasks = this.storage.restoreSession(this.ui);
    }

    /**
     * Repeatedly takes user input and responds to commands appropriately
     * until "bye" is given as input.
     */
    public void serve() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String msg = ui.readCommand();
                Command command = Parser.parse(msg);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ParsingException e) {
                ui.respondParsingError(e.getMessage(), e.getUsageMsgs());
            } catch (CommandExecutionException e) {
                ui.respond(e.getMessage());
            }
        }
    }
}