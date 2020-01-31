import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class TaskList {
    /** List of tasks; assume no more than 100 tasks. */
    private ArrayList<Task> lTasks = new ArrayList<>();

    public boolean load(ArrayList<Task> tasks) {
        if (lTasks.isEmpty()) {
            lTasks.addAll(tasks);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> save() {
        return ((ArrayList<Task>) lTasks.clone());
    }

    // todo: make static date and time formatters to task
    // Stretch goal: add a command to print deadlines/events occurring on a specific date.

    /**
     * Adds a Todo task to the Task List.
     * @param desc description of the Todo Task.
     * @return A string describing the Task that was added.
     * @throws IncorrectArgumentException
     */
    public String[] addTodo(String desc) {
        return add(new Todo(desc));
    }
    
    public String[] addDeadline (String desc, LocalDate date, LocalTime time) 
        throws IncorrectArgumentException {
        return add(new Deadline(desc, date, time));
    }
    
    public String[] addEvent (String desc, LocalDate date, LocalTime time) 
    throws IncorrectArgumentException {
        return add(new Event(desc, date, time));
    }

    private String[] add(Task s) {
        lTasks.add(s);
        return new String[]{
                "Got it. I've added this task:", 
                "  " + s.toString(),
                String.format("Now you have %d tasks in the list.", lTasks.size())
            };
    }

    public String[] list() {
        String[] tasks = new String[lTasks.size() + 1];
        tasks[0] = lTasks.size() == 0 
            ? "You have no tasks in your list." 
            : "Here are the tasks in your list:";
        for (int i = 1; i <= lTasks.size(); i++) {
            tasks[i] = String.format("%d.%s", i, lTasks.get(i - 1).toString());
        }
        return tasks;
    }

    public String[] done(int i) {
        return new String[]{ "Nice! I've marked this task as done:", lTasks.get(i - 1).done() };
    }

    public String[] delete(int i) {
        Task rem = lTasks.remove(i - 1);
        return new String[]{
                "Noted. I've removed this task:", 
                rem.toString(),
                String.format("Now you have %d tasks in the list.", lTasks.size())
            };
    }
}