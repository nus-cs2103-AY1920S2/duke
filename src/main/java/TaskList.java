class TaskList {
    // List of tasks; assume no more than 100 tasks.
    private Task[] lTasks = new Task[100];
    // int representing number of tasks in the list
    private int nTasks = 0;

    public String[] addTodo(String... ss) {
        return add(new Todo(ss[0]));
    }
    
    public String[] addDeadline(String... ss) throws IncorrectArgumentException {
        if (ss.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: ".concat(ss.length == 1 ? "task" : "task, by.."));
        }
        return add(new Deadline(ss[0], ss[1]));
    }
    
    public String[] addEvent(String... ss) throws IncorrectArgumentException {
        if (ss.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments".concat(ss.length == 1 ? "task" : "task, at.."));
        }
        return add(new Event(ss[0], ss[1]));
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