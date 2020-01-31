public class DoneCommand extends Command {

    public DoneCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    @Override
    public void execute(UI ui, TaskList list, Storage storage) throws DukeException {
        try {
            String[] inputParsed = this.getInputCommand().trim().split(" ");
            if (inputParsed.length <= 1) {
                throw new DukeException("Done command cannot be empty!");
            }
            Task task = list.getTask(Integer.parseInt(inputParsed[1]));
            if (task.getDone()) {
                ui.prettyPrinting("Task already set done!");
            } else {
                task.setDone();
                ui.prettyPrinting("Task set to done!");
            }
            storage.writeToFile(list.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I believe you have an incorrect task number, try again!");
        }
    }
}
