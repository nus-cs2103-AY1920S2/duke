//import javafx.application.Application;

public class Launcher {
    /**
     * Constructs and runs an instance of the Duke application.
     * @see <a href="https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174">
     *  Include dependencies in jar #236
     * </a>
     */
    public static void main(String[] args) {
        //
        Duke duke = new Duke();
        /*
        Application.launch(Gui.class, duke, args); //does this accept arbitrary arguments?
        
        //after GUI terminates??
        duke.saveTasks();
        */
        duke.run();
        duke.saveTasks();
    }
}
