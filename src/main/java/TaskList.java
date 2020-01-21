class TaskList {
    // List of tasks represented as a String array; assume no more than 100 tasks.
    private String[] lTasks = new String[100];
    // boolean representing state of each task
    private boolean[] lDone = new boolean[100];
    // int representing number of tasks in the list
    private int nTasks = 0;

    public String add(String s) {
        lTasks[nTasks] = s;
        nTasks++;
        return "added: " + s;
    }
    
    public String[] list() {
        String[] tasks = new String[nTasks];
        for (int i = 0; i < nTasks; i++) {
            tasks[i] = String.format("%d.%s %s", i + 1, lDone[i] ? "[✓]" : "[X]", lTasks[i]);
        }
        return tasks;
    }

    public String[] done(int i) {
        lDone[i - 1] = true;
        return new String[]{ "Nice! I've marked this task as done:", String.format("[✓] %s", lTasks[i - 1]) };
    }
}