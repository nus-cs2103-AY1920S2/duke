public class TasksList {
    protected Task[] tasks = new Task[100];
    protected int nextIndex = 0;

    public Task getTask(int index) {
        return tasks[index];
    }

    public void markDone(int num) {
        int index = num - 1;
        tasks[index].markDone();
        System.out.println("Nice! I've marked this task as done: \n  " + tasks[index]);
    }

    public void addTodo(String todo) {
        String description = todo.substring(5);
        tasks[nextIndex] = new Todo(description);
        nextIndex++;
        System.out.println("Got it. I've added this task:\n  " + tasks[nextIndex-1]
                + "\nNow you have " + nextIndex + " tasks in the list.");
    }

    public void addDeadline(String deadline) {
        String temp = deadline.substring(9);
        String[] splitted = temp.split("/", 2);
        tasks[nextIndex] = new Deadline(splitted[0], splitted[1]);
        nextIndex++;
        System.out.println("Got it. I've added this task:\n  " + tasks[nextIndex-1]
                + "\nNow you have " + nextIndex + " tasks in the list.");
    }

    public void addEvent(String event) {
        String temp = event.substring(6);
        String[] splitted = temp.split("/", 2);
        tasks[nextIndex] = new Event(splitted[0], splitted[1]);
        nextIndex++;
        System.out.println("Got it. I've added this task:\n  " + tasks[nextIndex-1]
                + "\nNow you have " + nextIndex + " tasks in the list.");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < nextIndex; i++) {
            System.out.println(i+1 + "." + tasks[i]);
        }
    }
}
