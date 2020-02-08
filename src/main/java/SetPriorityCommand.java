public class SetPriorityCommand extends Command {

    public SetPriorityCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    @Override
    public String execute(UI ui, TaskList list, Storage storage) throws DukeException {
        String[] parsedInput = this.getInputCommand().trim().split(" ");
        if (parsedInput.length < 3) {
            throw new DukeException("Set priority command incomplete");
        }
        try {
            Task task = list.getTask(Integer.parseInt(parsedInput[1]));
            int priority = extractPriority(parsedInput[2]);
            boolean isSuccessful = task.setPriority(priority);
            if (isSuccessful) {
                ui.prettyPrinting("Priority set!");
                storage.writeToFile(list.getTaskList());
                return "Priority set!";
            } else {
                ui.prettyPrinting("Priority is already set as stated!");
                return "Priority is already set as stated";
            }

        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I believe you gave an incorrect number");
        }
    }

    public int extractPriority(String inputPriority) throws DukeException {
        String toLower = inputPriority.trim().toLowerCase();
        if (toLower.contains("high")) {
            return 3;
        } else if (toLower.contains("medium")) {
            return 2;
        } else if (toLower.contains("low")){
            return 1;
        } else {
            throw new DukeException("Invalid input of priority!");
        }
    }
}
