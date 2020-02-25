package duke;

import java.util.List;
import java.util.ArrayList;

public class Duke {

    List<String> log;
    TaskList masterList;

    public Duke() {
        this.log = new ArrayList<String>();
        this.masterList = Storage.load();
    }

    /**
     * Processes the user input and returns a result.
     * @return the result after processing the user input.
     */
    public String getResponse(String input) {
        return Ui.readNextCommand(input, this.masterList);
    }

}