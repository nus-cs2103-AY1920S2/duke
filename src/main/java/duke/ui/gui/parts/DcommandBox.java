package duke.ui.gui.parts;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;


public class DcommandBox extends GuiComponent<Region> {
    private static final String FXML = "commandbar.fxml";
    private CommandFeeder feeder;

    @FXML
    private AnchorPane commandArea;

    public DcommandBox(CommandFeeder cmdFeeder, Region root) {
        super(FXML);
        this.feeder = cmdFeeder;
        setAttr(root);
        fillAreas();
    }

    private void setAttr(Region root) {
        this.commandArea.prefWidthProperty().bind(root.widthProperty());
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
