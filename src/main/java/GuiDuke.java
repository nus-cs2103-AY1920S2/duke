import java.io.FileNotFoundException;

public class GuiDuke extends Duke {
    public GuiDuke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            // fail silently
            tasks = new TaskList();
        }
    }

    public GuiDuke() {
        this(expectedStoragePath);
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);
        return ui.getResponse();
    }
}
