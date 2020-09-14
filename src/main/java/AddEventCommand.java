/**
 * Class to present Adding event command.
 */
public class AddEventCommand extends Command {

    private Parser parser;

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public AddEventCommand(String inputCommand, boolean isExit, Parser parser) {
        super(inputCommand, isExit);
        this.parser = parser;
    }

    /**
     * Execute the given task.
     * @param ui the Ui class in charge of aesthetics.
     * @param list tasklist that stores all the task.
     * @param storage storage that writes to file.
     * @throws DukeException exception.
     */
    @Override
    public String execute(UI ui, TaskList list, Storage storage, HistoryManager historyManager) throws DukeException {
        try {
            String[] inputParsed = this.getInputCommand().trim().split(" ");
            if (!this.getInputCommand().contains(" /at ")) {
                throw new DukeException("Event command must contain [ /at ] as stated!");
            }
            if (inputParsed.length == 1) {
                throw new DukeException("Event command description cannot be empty");
            }
            StringBuilder taskName = new StringBuilder();
            StringBuilder dateTime = new StringBuilder();
            int indexFound = parser.grabTaskName(taskName, inputParsed, "/at");
            parser.grabDateTime(indexFound, inputParsed, dateTime);
            // create new event class
            Event event = new Event(taskName.toString(), dateTime.toString());
            historyManager.addState(list);
            list.addTask(event);
            storage.writeToFile(list.getTaskList()); //save to file
            return ui.prettyPrinting(taskName.toString() + " added!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("/at must be followed by a whitespace!");
        }
    }

}
