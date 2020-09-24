package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TasksList {
    public static final String TODO_FORMAT = "The todo format is: todo <desc>";
    public static final String EVENT_FORMAT = "The event format is: event <desc> /at <time>";
    public static final String DEADLINE_FORMAT = "The deadline format is: deadline <desc> /by <time>";
    public static final String TASK_NOT_FOUND = "Task not found";

    public static final int NUM_ARGS_DEADLINE = 2;
    public static final int NUM_ARGS_EVENT = 2;

    public  ArrayList<Task> tasks = new ArrayList<>();

    public int size(){
        return tasks.size();
    }

    public Todo addTodo(String description, boolean isDone) throws DukeException{
        if (description == null) {
            throw new DukeException(TODO_FORMAT);
        }

        Todo newTask = new Todo(description, isDone);
        tasks.add(newTask);

        return newTask;
    }

    public Deadline addDeadline(String description, boolean isDone) throws DukeException{
        String[] taskDetails = description.split(" /by ");
        if(taskDetails.length < NUM_ARGS_DEADLINE) {
            throw new DukeException(DEADLINE_FORMAT);
        }

        Deadline newTask = new Deadline(taskDetails[0], taskDetails[1], isDone);
        tasks.add(newTask);
        return newTask;
    }

    public Event addEvent(String description, boolean isDone) throws DukeException{
        String[] taskDetails = description.split(" /at ");
        if(taskDetails.length < NUM_ARGS_EVENT) {
            throw new DukeException(EVENT_FORMAT);
        }

        Event newTask = new Event(taskDetails[0], taskDetails[1], isDone);
        tasks.add(newTask);
        return newTask;
    }

    public Task delete(String description) throws DukeException{
        for (Task task: tasks) {
            if (task.description.equals(description)) {
                tasks.remove(task);
                return task;
            }
        }
        throw new DukeException(TASK_NOT_FOUND);
    }

}
