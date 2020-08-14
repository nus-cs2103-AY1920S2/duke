import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Gui ui;

    /**
     * Constructor for the Duke Class.
     */
    public Duke() {
        String filepath = "duke.txt";
        ui = new Gui();
        //ui.printStartUp();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs the code for the Duke program.
     */
    public String getResponse(String in) {
        Parser parser = new Parser(in);

        String toReturn = parser.parseCommand(ui, tasks);
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            toReturn += "\nAn error occurred while saving, please try again!";
        }
        assert !(toReturn.equals("")) : "Duke's response is empty!";
        return toReturn;
    }
}
