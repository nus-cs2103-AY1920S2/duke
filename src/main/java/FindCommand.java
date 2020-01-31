import java.util.ArrayList;

/**
 * Class to present find commands.
 */
public class FindCommand extends Command {

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public FindCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    /**
     * Execute the given task.
     * @param ui the Ui class in charge of aesthetics.
     * @param list tasklist that stores all the task.
     * @param storage storage that writes to file.
     * @throws DukeException exception.
     */
    @Override
    public void execute(UI ui, TaskList list, Storage storage) throws DukeException {
        String[] inputParsed = this.getInputCommand().trim().split(" ");
        if (inputParsed.length == 1) {
            throw new DukeException("Keyword cannot be empty!");
        }
        String keyWord = inputParsed[1].trim();
        ArrayList<Task> matchingTasks = list.findTasks(keyWord);
        String result = "";
        if (matchingTasks.size() == 0) {
            // no matches
            result += "No results were found :(";
        } else {
            result += "Here are the results your search!\n\t";
            int count = 1;
            for (Task t: matchingTasks) {
                result += count + "." + t.toString();
                if (count != matchingTasks.size()) {
                    result += "\n\t";
                }
                count++;
            }
        }
        ui.prettyPrinting(result);
    }
}
