public class Task {
    private String taskName;
    private boolean isDone = false;

    public static String tick = Character.toString((char) 10003);
    public static String cross = Character.toString((char) 10060);

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String toString() {
        String taskDescription = "[";
        if(isDone) {
            taskDescription += tick;
        } else {
            taskDescription += cross;
        }

        taskDescription += "] " + taskName;
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
