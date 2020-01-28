public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void taskDone() {
        this.isDone = true;
    }

    public static Task load(String s){
        String[] info = s.split(" \\| ");
        String actionLetter = info[0];
        String isDone = info[1];
        String taskDesc = info[2];
        String additional = "";
        Task t;

        switch (actionLetter) {
            case "T":
                Todo todo = new Todo(taskDesc);
                if(isDone.equals("1")){
                    todo.taskDone();
                }
                return todo;
            case "E":
                additional = info[3];
                Event event = new Event(taskDesc, additional);
                if(isDone.equals("1")){
                    event.taskDone();
                }
                return event;
            case "D":
                additional = info[3];
                Deadline deadline = new Deadline(taskDesc, additional);
                if(isDone.equals("1")){
                    deadline.taskDone();
                }
                return deadline;
            default:
                return new Task(taskDesc);
        }
    }

    public String format() {
        return "TASK" + " | " + (this.isDone?"1":"0") + " | " + description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
