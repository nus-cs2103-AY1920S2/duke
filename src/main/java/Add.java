public class Add extends Command {
    Task ob;
    int saved;

    Add(Task ob, int saved) {
        this.ob = ob;
        this.saved = saved;
    }

    /**
     * This function executes the add command for tasks of all types.
     * It initially calls a function from DetectDuplicates class to detect and remove an duplicates in the list.
     *
     * @param tasks TaskList object.
     * @param storage Storage object.
     * @return Returns affirmation that add has been done.
     */
    String execute(TaskList tasks, Storage storage) {
        DetectDuplicates detect;
        if (ob.getType().equals("todo")) {
            detect = new DetectDuplicates(ob.getTaskName(), ob.getType());
        } else {
            if (ob.getType().equals("deadline")) {
                detect = new DetectDuplicates(ob.getTaskName(), ob.getType(), ((Deadline)ob).getTime());
            } else {
                detect = new DetectDuplicates(ob.getTaskName(), ob.getType(), ((Event)ob).getTime());
            }
        }
        detect.removeDuplicates(tasks, storage);
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
}