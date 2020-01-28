package task;

import java.time.LocalDate;

/**
 * This is the main class for the Task object. It is represented by a
 * description of the activity and whether the object has been completed or not.
 */
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

    /**
     * This method sets the Task to be done.
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * This method loads a single specific task based on the information provided by the data.
     * @param s The task String that the method will reference to to create the relevant Task object.
     * @return the relevant Task object.
     */
    public static Task load(String s){
        String[] info = s.split(" \\| ");
        String actionLetter = info[0];
        String isDone = info[1];
        String taskDesc = info[2];
        LocalDate additional;
        Task t;

        switch (actionLetter) {
            case "T":
                Todo todo = new Todo(taskDesc);
                if(isDone.equals("1")){
                    todo.taskDone();
                }
                return todo;
            case "E":
                additional = LocalDate.parse(info[3]);
                Event event = new Event(taskDesc, additional);
                if(isDone.equals("1")){
                    event.taskDone();
                }
                return event;
            case "D":
                additional = LocalDate.parse(info[3]);
                Deadline deadline = new Deadline(taskDesc, additional);
                if(isDone.equals("1")){
                    deadline.taskDone();
                }
                return deadline;
            default:
                return new Task(taskDesc);
        }
    }

    /**
     * This method formats the Task object based on its representation into a format suitable for
     * writing to a file. It is not actually used in principle.
     *
     * @return A String object that can be easily written and retrieved from the data file.
     */
    public String format() {
        return "TASK" + " | " + (this.isDone?"1":"0") + " | " + description;
    }

    /**
     * A specialised toString() method based on implementations of the object.
     *
     * @return A specialised String representation of the generic Task object.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
