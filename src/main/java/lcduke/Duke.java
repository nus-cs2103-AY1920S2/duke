package lcduke;

import java.io.FileNotFoundException;
import java.text.ParseException;

/** Ths creates a Duke object.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    public Ui ui;

    /** This is the constructor to create the Deadline Object.
     *
     * @param filePath File path of user's hard disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage.getStorageNo());
        } catch (FileNotFoundException | ParseException e) {
            tasks = new TaskList();
        }
    }

    public void run(){
            String userInput;
        while(!ui.userInput.equals("bye")){
            userInput = ui.input();
            ui.inputProcess(userInput, storage, tasks);
        }
        ui.bye();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        if(!ui.userInput.equals("bye")){
            response = ui.inputProcess(input, storage, tasks);
        } else {
            response = ui.bye();
            assert response == "     Bye. Hope to see you again soon!\n"
                    : "ui should have correct bye message\n";
        }
        return response;
    }
}
