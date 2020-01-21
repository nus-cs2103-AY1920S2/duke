class TaskList {
    // List of tasks; assume no more than 100 tasks.
    private Task[] lTasks = new Task[100];
    // int representing number of tasks in the list
    private int nTasks = 0;

    public String[] addTodo(String s) {
        return add(new Todo(s));
    }
    
    public String[] addDeadline(String s, String by) {
        return add(new Deadline(s, by));
    }
    
    public String[] addEvent(String s, String at) {
        return add(new Event(s, at));
    }

    private String[] add(Task s) {
        lTasks[nTasks] = s;
        nTasks++;
        return new String[]{"Got it. I've added this task:", "  " + s.toString(), String.format("Now you have %d tasks in the list.", nTasks) };
    }

    public String[] list() {
        String[] tasks = new String[nTasks + 1];
        tasks[0] = nTasks == 0 ? "You have no tasks in your list." : "Here are the tasks in your list:";
        for (int i = 1; i <= nTasks; i++) {
            tasks[i] = String.format("%d.%s", i, lTasks[i - 1].toString());
        }
        return tasks;
    }

    public String[] done(int i) {
        return new String[]{ "Nice! I've marked this task as done:", lTasks[i - 1].done() };
    }
}