public class ListCommand extends Command {

    ListCommand() {
        super();
    }

    /**
     * Executes list command by displaying the list.
     *
     * @param tasks Task object.
     * @param storage Storage object.
     * @return list of tasks.
     */
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder output = new StringBuilder("Here is the list of tasks:\n");
        for (int i = 0; i < tasks.getList().size(); i++) {
            output.append((i + 1) + ". " + tasks.getList().get(i));
            if (i != tasks.getList().size() - 1) {
                output.append("\n");
            }
        }
        String s = output.toString();
        return s;
    }
}
