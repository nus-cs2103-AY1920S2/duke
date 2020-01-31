public class AddDeadlineCommand extends Command {

    private Parser parser;

    public AddDeadlineCommand(String inputCommand, boolean isExit, Parser parser) {
        super(inputCommand, isExit);
        this.parser = parser;
    }

    @Override
    public void execute(UI ui, TaskList list, Storage storage) throws DukeException {
        try {
            String[] inputParsed = this.getInputCommand().trim().split(" ");
            if (!this.getInputCommand().contains("/by")) {
                throw new DukeException("Deadline command must contain [/by] as stated!");
            }
            if (inputParsed.length == 1) {
                throw new DukeException("Deadline command description cannot be empty");
            }
            StringBuilder taskName = new StringBuilder();
            StringBuilder dateTime = new StringBuilder();
            int indexFound = parser.grabTaskName(taskName, inputParsed, "/by");
            parser.grabDateTime(indexFound, inputParsed, dateTime);
            Deadline.validDate(dateTime.toString());
            Deadline deadline = new Deadline(taskName.toString(), dateTime.toString());
            list.addTask(deadline);
            ui.prettyPrinting(taskName.toString() + " added!");
            storage.writeToFile(list.getTaskList());
        } catch (DukeException e) {
            throw new DukeException("Deadline description cannot be empty or must conatins [/by]!");
        } catch (Exception e) {
            throw new DukeException("Incorrect date format! Please refer to following example: 31-12-2020 23:59");
        }
    }
}
