package task;

/**
 * The simple implementation of task.Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of task.Todo.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {

        super(description);
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Todo)) {
            return false;
        }

        Todo todo = (Todo) object;
        return compareTodo(todo);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    /**
     * Compares this deadline with another
     *
     * @param todo the deadline to compare with
     * @return boolean whether this deadline is the same as the parameter
     */
    private boolean compareTodo(Todo todo) {

        boolean hasSameDescription = (description.equals(todo.description));

        return hasSameDescription;
    }

}
