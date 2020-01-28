public class ToDoTask extends Task {
    private String task;

    public ToDoTask(String task) {
        this.task = task;
    }

    /**
     * Produces a String of an appropriate format to store into the txt file when saving later on
     * @return a String of the form "todo TASKDESCRIPTION / isDONE"
     *
     */
    public String formatToStore() {
        String format = "todo " + task + " /";
        if(isDone) {
            format += " 1";
        } else {
            format += " 0";
        }
        return format;
    }

    public String toString() {
        String taskWords =  "[T]";
        if(isDone) {
            taskWords += tick;
        } else {
            taskWords += cross;
        }
        taskWords += " " + task;

        return taskWords;
    }
}
