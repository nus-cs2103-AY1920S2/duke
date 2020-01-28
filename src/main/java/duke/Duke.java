package duke;

import tool.*;

public class Duke {
    public Duke(){ }

    public void run(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/data/tasks.txt", taskList);
        UI ui = new UI(storage);

        storage.readFromFile();

        Parser parser = new Parser(ui, taskList);

        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.printLine(); // show the divider line ("_______")
            Command c = parser.parse(fullCommand);
            isExit = c.execute(taskList, ui);
            ui.printLine();
            storage.saveToFile();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

