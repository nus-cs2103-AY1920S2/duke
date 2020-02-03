public class Done extends Command {
    String num;

    Done(String num) {
        this.num = num;
    }

    /**
     * Executes the done command.
     *
     * @param tasks Task object.
     * @param ui UI object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int number = Integer.valueOf(num);
        Task ob = tasks.getList().get(number - 1);
        ob.setDone();
        return "Nice! I've marked this task as done:\n"
                + ui.printTask(number,tasks);
    }

    boolean isExit() {
        return false;
    }
}