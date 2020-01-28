public class Add extends Command {
    Task ob;
    int saved;

    Add(Task ob, int saved) {
        this.ob = ob;
        this.saved = saved;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list.add(ob);
        String k = ob.toString();
        if (saved == 0) {
            ui.printString("Got it. I've added this task:\n  "
                            + k + "\nNow you have " + tasks.list.size()
                            + " tasks in the list.");
        }
    }

    boolean isExit() {
        return false;
    }

}