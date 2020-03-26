import javafx.application.Application;

import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Duke
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * <p>The Duke class is the main class of the bot,
 * where the application starts.</p>
 * @author Daniel Alfred Widjaja
 */
public class Duke extends Application {

    private String fileLoc;


    /**
     * Creates a Duke instance.
     * @param stage The stage for Duke to load.
     */
    @Override
    public void start(Stage stage) throws IOException {

        fileLoc = "./src/main/data/duke.txt";
        if (!isFileExist(fileLoc)) {
            createFile(fileLoc);
        }

        Parser parser = new Parser(fileLoc);
        Handler handler = new Handler(fileLoc);

        Ui ui = new Ui(stage, parser, handler);
        ui.start();

    }

    boolean isFileExist(String fileLoc) {
        File file = new File(fileLoc);
        return file.exists();
    }

    void createFile(String fileLoc) throws IOException {
        File file = new File(fileLoc);
        file.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(file);
    }

}

