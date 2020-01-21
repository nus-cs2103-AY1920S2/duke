class TaskList {
    // List of tasks; assume no more than 100 tasks.
    private Task[] lTasks = new Task[100];
    // int representing number of tasks in the list
    private int nTasks = 0;

    public String add(String s) {
        lTasks[nTasks] = new Task(s);
        nTasks++;
        return "added: " + s;
    }
    
    public String[] list() {
        String[] tasks = new String[nTasks];
        for (int i = 0; i < nTasks; i++) {
            tasks[i] = String.format("%d.%s", i + 1, lTasks[i].toString());
        }
        return tasks;
    }

    public String[] done(int i) {
        return new String[]{ "Nice! I've marked this task as done:", lTasks[i - 1].done() };
    }
}