package duke.main;

public class Ui {

    private TaskList tasks;

    /**
     * Constructor for Ui class to run UI events.
      * @param tasks is the tasks that this current instance of duke.main.Duke has
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Provides the welcome message.
     * @return the welcome message string
     */
    public static String getWelcomeMessage() {
        return "Welcome to Kim Jong Un's Secret Chat. You can call me RocketMan and please let me know how to help!";
    }


    /**
     * Provides the goodbye message.
     * @return the goodbye message string
     */
    public static String getGoodbyeMessage() {
        return "GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA";
    }


}
