public class DoneCommand extends Command {

    public DoneCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    @Override
    public String execute(UI ui, TaskList list, Storage storage, HistoryManager historyManager) throws DukeException {
        try {
            String[] inputParsed = this.getInputCommand().trim().split(" ");
            if (inputParsed.length <= 1) {
                throw new DukeException("Done command cannot be empty!");
            }
            Task task = list.getTask(Integer.parseInt(inputParsed[1]));
            if (task.getDone()) {
                return ui.prettyPrinting("Task already set done!");
            } else {
                historyManager.addState(list);
                task.setDone();
                storage.writeToFile(list.getTaskList());
                return ui.prettyPrinting("Task set to done!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I believe you have an incorrect task number, try again!");
        }
    }
}
