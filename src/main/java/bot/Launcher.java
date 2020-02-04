package bot;

import bot.gui.Main;

import javafx.application.Application;

/**
 * Launcher helps 4LC3N-BOT to start, avoiding
 * classpath issues
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
