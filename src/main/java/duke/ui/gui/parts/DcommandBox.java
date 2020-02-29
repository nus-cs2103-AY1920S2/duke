package duke.ui.gui.parts;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;


public class DcommandBox extends GuiComponent<Region> {
    private static final String FXML = "commandbar.fxml";
    private CommandFeeder feeder;

    @FXML
    private FlowPane commandArea;

    public DcommandBox(CommandFeeder cmdFeeder) {
        super(FXML);
        this.feeder = cmdFeeder;
        fillAreas();
    }

    DDtextBar textRegionController;
    DDbutton buttonController;

    private void fillAreas() {
        textRegionController = new DDtextBar(this::feedCommand);
        textRegionController.setDimension(325.0);
        buttonController = new DDbutton("Send!", this::feedCommand);
        buttonController.setDimension(55.0);
        this.commandArea.getChildren().addAll(textRegionController.getRoot(), buttonController.getRoot());
    }

    private void feedCommand() {
        this.feeder.execute(getTextBarContent());
    }

    private String getTextBarContent() {
        return this.textRegionController.getText();
    }

    @FunctionalInterface
    public interface CommandFeeder {
        void execute(String cmd);
    }

}
