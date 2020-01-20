public class Task {
    private String taskName;
    protected boolean isDone = false;

    public static String tick = "[" + (char) 10003 + "]";
    public static String cross = "[" + (char) 10060 + "]";

    public Task(String taskName) {
        this.taskName = taskName;
    }
    public Task() {}

    public String toString() {
        String taskDescription = "";
        if(isDone) {
            taskDescription += tick;
        } else {
            taskDescription += cross;
        }

        taskDescription += taskName;
        return taskDescription;
    }

    public void setDone() {
        isDone = true;
    }

    public static void main(String[] args) {
        Task hey = new Task("mynamekeff");
        System.out.println(hey);
    }
}
