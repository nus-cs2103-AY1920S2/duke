public class Add extends Command {
    Task ob;
    int saved;

    Add(Task ob, int saved) {
        this.ob = ob;
        this.saved = saved;
    }

    String execute(TaskList tasks, Storage storage) {
        tasks.getList().add(ob);
        String k = ob.toString();
        if (saved == 0) {
            return "Got it. I've added this task:\n  "
                            + k + "\nNow you have " + tasks.getList().size()
                            + " tasks in the list.";
        } else {
            return "";
        }
    }

    boolean isExit() {
        return false;
    }

}