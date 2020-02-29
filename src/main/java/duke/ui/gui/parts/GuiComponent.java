package duke.ui.gui.parts;

import static java.util.Objects.requireNonNull;

import duke.DukeMain;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public abstract class GuiComponent<T> {
    /** Resource folder where FXML files are stored. */
    private static final String FXML_FILE_FOLDER = "/fxml/";
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    private void loadFxmlFile(URL location, T root) {
        requireNonNull(location);
        this.fxmlLoader.setLocation(location);
        this.fxmlLoader.setController(this);
        this.fxmlLoader.setRoot(root);
        try {
            this.fxmlLoader.load();
        } catch (IOException e) {
            //System.out.println(location);
            throw new AssertionError(e);
        }

    }

    public GuiComponent(URL fileUrl, T root) {
        loadFxmlFile(fileUrl, root);
    }

    public GuiComponent(URL fileUrl) {
        this(fileUrl, null);
    }

    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        String fxmlFileNameWithFolder = FXML_FILE_FOLDER + fxmlFileName;
        URL fxmlFileUrl = DukeMain.class.getResource(fxmlFileNameWithFolder);
        return requireNonNull(fxmlFileUrl);
    }

    public GuiComponent(String fxmlFileName, T root) {
        this(getFxmlFileUrl(fxmlFileName), root);
    }

    public GuiComponent(String fxmlFileName) {
        this(fxmlFileName, null);
    }

    public T getRoot() {
        return this.fxmlLoader.getRoot();
    }
}
