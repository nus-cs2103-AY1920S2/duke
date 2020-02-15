public class AddDeadlineCommand extends Command {

    private Parser parser;

    public AddDeadlineCommand(String inputCommand, boolean isExit, Parser parser) {
        super(inputCommand, isExit);
        this.parser = parser;
    }

    @Override
    public String execute(UI ui, TaskList list, Storage storage, HistoryManager historyManager) throws DukeException {
        try {
            String[] inputParsed = this.getInputCommand().trim().split(" ");
            // to check if the command violates any rules
            if (!this.getInputCommand().contains(" /by ")) {
                throw new DukeException("Deadline command must contain [/by] as stated!");
            }
            if (inputParsed.length == 1) {
                throw new DukeException("Deadline command description cannot be empty");
            }

            // else extract out the important details and create new deadline task
            StringBuilder taskName = new StringBuilder();
            StringBuilder dateTime = new StringBuilder();
            int indexFound = parser.grabTaskName(taskName, inputParsed, "/by");
            parser.grabDateTime(indexFound, inputParsed, dateTime);
            Deadline.validDate(dateTime.toString());
            Deadline deadline = new Deadline(taskName.toString(), dateTime.toString());
            historyManager.addState(list);
            list.addTask(deadline);
            //write to storage and print out output (for debugging)
            storage.writeToFile(list.getTaskList());
            return ui.prettyPrinting(taskName.toString() + " added!");
        } catch (DukeException e) {
            throw new DukeException("Deadline description cannot be empty or must conatins [ /by ]!");
        } catch (Exception e) {
            throw new DukeException("Incorrect date format! Please refer to following example: 31-12-2020 23:59");
        }
    }
}
