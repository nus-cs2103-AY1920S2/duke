public class ToDoTask extends Task {
    private String task;

    public ToDoTask(String task) {
        this.task = task;
    }

    @Override
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
