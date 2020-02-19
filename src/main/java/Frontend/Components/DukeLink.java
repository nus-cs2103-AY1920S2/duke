package Frontend.Components;

import Backend.Exceptions.DukeException;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DukeLink extends Hyperlink{

    String url;
    String title;

    public DukeLink( String title, String url ){
        super(title);
        this.title = title;
        this.url = url;
    }

    public void openLink() throws DukeException{

        try {
            URI uri = new URI(url);
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e ){
            throw new DukeException( e );
        }

    }

}
