package dukebot.gui;

import javafx.scene.image.Image;

public enum DukeExpression {
    HAPPY("duke_happy.png"),
    BLUSH("duke_blush.png"),
    SAD("duke_sad.png"),
    SURPRISED("duke_surprised.png");

    private final Image dukeImage;

    DukeExpression(String fileName) {
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/" + fileName));
    }

    /**
     * Gets the Image corresponding to the expression.
     *
     * @return An image representing Duke;
     */
    public Image getDukeImage() {
        return dukeImage;
    }
}