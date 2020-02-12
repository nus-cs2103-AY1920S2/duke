import java.time.LocalDate;

public class Task {

    public String description;
    public int status = 0;


    /**
     * Constructor for Task class
     * Takes in a description and sets it as the Task description
     * Task class is extended by ToDo, deadline and event classes
     *
     * @param description description of the task
     */

    public Task (String description) {

        this.description = description;

    }

    /**
     * Method returns the task description
     *
     */

    public String getDescription() {

        return description;
    }

    /**
     * Method returns the task status 0 as not done,
     * or 1 as done.
     *
     */
    public int getStatus() {

        return status;
    }

    /**
     * Method returns sets the task status to done,
     * which in this case is reflected as a 0 or 1.
     *
     */

    public void setStatusDone() {

        status = 1;
    }

    /**
     * Method returns sets the task status to undone,
     * which in this case is reflected as a 0 or 1.
     *
     */

    public void setStatusUndone() {

        status = 0;
    }

    /**
     * Method returns sets the task type, where in this
     * Task class there is no type and this function is overridden
     * in the event, deadline and todo class.
     *
     */

    public String getType() {

        return "Weirdly, this is just a normal task.";
    }

    /**
     * Method returns sets the task completion date, where in this
     * Task class there is no completion date and this function is overridden
     * in the event, deadline and todo class.
     *
     */

    public LocalDate getBy() {
        return LocalDate.parse("0000-00-00");
    }


    @Override
    public String toString() {

        return description;
    }

}
