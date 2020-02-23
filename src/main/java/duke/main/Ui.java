package duke.main;

public class Ui {

    private TaskList tasks;

    /**
     * Constructor for duke.main.Ui class to run UI events
      * @param tasks is the tasks that this current instance of duke.main.Duke has
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public static String getWelcomeMessage() {
        return "Welcome to Kim Jong Un's Secret Chat. You can call me RocketMan and please let me know how to help!";
    }




    /**
     * Prints the basic goodbye message when user has no more input
     */
    public static String getGoodbyeMessage() {
        return "GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA";
    }




}
