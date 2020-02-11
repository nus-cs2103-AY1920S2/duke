public class Done extends Command {
    String num;

    Done(String num) {
        this.num = num;
    }

    /**
     * Executes the done command by marking the respective task as done.
     *
     * @param tasks Task object.
     * @param storage Storage object.
     * @return Returns the affirmation that task has been marked as completed.
     */
    public String execute(TaskList tasks, Storage storage) {
        int number = Integer.valueOf(num);
        Task ob = tasks.getList().get(number - 1);
        String k = ob.toString();
        ob.setDone();
        return "Nice! I've marked this task as done:\n"
                + k;
    }
}