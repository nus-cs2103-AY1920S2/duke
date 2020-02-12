import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public SaveToFile saveToFile;
    public Ui ui;
    public TaskList tasks;
    /**
     * Constructor for duke.
     *
     * @param filePath File path for duke's task list.
     */
    
    public Duke(String filePath) {
        assert filePath.isEmpty() : "File does not exist!"; 
        this.ui = new Ui();
        this.saveToFile = new SaveToFile();
        this.tasks = new TaskList(saveToFile.loadList(filePath));
    }
}

