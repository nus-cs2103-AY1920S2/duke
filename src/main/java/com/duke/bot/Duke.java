package com.duke.bot;

import java.io.IOException;
import java.lang.String;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents the Duke bot that manages the tasks of the users.
 */
public class Duke {
    private DukeUi ui;
    private Storage storage;

    /**
     * Creates a Duke Bot object.
     */
    public Duke() {
        storage = Storage.createSrorageFile();
        this.ui = new DukeUi(System.in, System.out);
    }

    /**
     * Creates a Duke object.
     *
     * @param ui the UI that duke will be running on
     */
    public Duke(DukeUi ui) {
        storage = Storage.createSrorageFile();
        this.ui = ui;
    }

    /**
     * Returns the response from parsing the user command to the JavaFX GUI.
     *
     * @param input The user command word.
     * @return The response to be printed on the GUI.
     */
    public String getResponse(String input) {
        String output = "";
        assert output.isEmpty() : "output should be empty initially";
        Scanner sc = new Scanner(input);
        String commandWord = sc.next();
        String restOfStr = sc.hasNext() ? sc.nextLine() : "";
        try {
            output = Parser.parse(commandWord, new DukeUi(restOfStr), storage, storage.getTasks());
        } catch (DukeException e) {
            return e.getMessage();
        }

        return output;
    }

    /**
     * Takes the user's input and decides the action to be taken by Duke bot.
     *
     * @throws DukeException When the user input is invalid.
     */
    public void echo() throws DukeException {
        String userCommand = ui.getNext();
        assert !userCommand.equals("") : "user command cannot be empty";
        String output = Parser.parse(userCommand, ui, storage, storage.getTasks());
        ui.print(output);
        if (!userCommand.equals("bye")) {
            echo();
        }
    }

    /**
     * Loads the data from hard disk.
     *
     * @throws IOException When the file path on the hard disk is invalid.
     */
    public void loadData() throws IOException{
        this.storage.loadData();
    }

}
