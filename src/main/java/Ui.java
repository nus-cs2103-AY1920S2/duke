import java.io.IOException;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a Ui object to interact with the user.
     * @param tasks TaskList containing the user's tasks.
     * @param storage Storage for loading and saving the TaskList to.
     */
    public Ui(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Gives the correct output for the given input.
     * @param input String input by the user.
     * @return String response from the program.
     */
    public String getOutput(String input) {
        int numTasks = tasks.getSize();
        String output = "";

        try {
            Parser decoded = new Parser(input, numTasks);
            Commands command = decoded.getCommand();
            int index = decoded.getIndexAffected();

            switch (command) {
            case BYE:
                output = UiDesign.BYE.getString();
                try {
                    storage.save(tasks);
                } catch (DukeException e) {
                    throw e;
                }
                break;

            case LIST_TASKS:
                output = UiDesign.LIST_TOP_PART.getString();

                for (int i = 1; i <= numTasks; i++) {
                    String item = i + "." + tasks.getTask(i - 1);
                    output += item + "\n";
                }
                output += UiDesign.BORDER.getString();
                break;

            case DONE:
                tasks.markDone(index);
                output = UiDesign.DONE_TOP_PART.getString()
                        + tasks.getTask(index) + "\n"
                        + UiDesign.BORDER.getString();
                break;

            case DEL_TASK:
                numTasks--;
                output = UiDesign.REMOVE_TOP_PART.getString()
                        + tasks.getTask(index) + "\n"
                        + "Now you have " + numTasks + " tasks in the list.\n"
                        + UiDesign.BORDER.getString();
                tasks.remTask(index);
                break;

            case NEW_TASK:
                Task newTask = decoded.getTask();
                tasks.addTask(newTask);
                numTasks++;
                output = UiDesign.ADD_TOP_PART.getString()
                        + newTask + "\n"
                        + "Now you have " + numTasks + " tasks in the list.\n"
                        + UiDesign.BORDER.getString();
                break;

            case FIND:
                String keyword = decoded.getKeyWord();
                output = UiDesign.FIND_TOP_PART.getString();

                for (int i = 1; i <= numTasks; i++) {
                    Task task = tasks.getTask(i - 1);
                    if (task.getDetails().contains(keyword)) {
                        output += i + "." + task + "\n";
                    }
                }
                output += UiDesign.BORDER.getString();
                break;

            default:
                output = "";
            }
        } catch (DukeException e) {
            output = e.toString();
        }
        return output;
    }
}
