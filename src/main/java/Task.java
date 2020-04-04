import java.time.LocalDate;

public class Task {

    public String description;
    public int status = 0;
    public String tag = "";
    public String at;


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
     * Method sets the task status to done,
     * which in this case is reflected as a 0 or 1.
     *
     */

    public void setStatusDone() {

        status = 1;
    }

    /**
     * Method sets the task status to undone,
     * which in this case is reflected as a 0 or 1.
     *
     */

    public void setStatusUndone() {

        status = 0;
    }

    /**
     * Method sets the task type, where in this
     * Task class there is no type and this function is overridden
     * in the event, deadline and todo class.
     *
     */

    public String getType() {

        return "Weirdly, this is just a normal task.";
    }

    /**
     * Method sets the task completion date, where in this
     * Task class there is no completion date and this function is overridden
     * in the event, deadline and todo class.
     *
     */

    public LocalDate getBy() {
        return LocalDate.parse("0000-00-00");
    }

    /**
     * Method sets the location associated with
     * the task.
     *
     */


    public String getAt() {
        return at;
    }


    /**
     * Method sets the name of the tag associated with
     * the task. Task tag will be displayed as #tagname
     * in the display messages
     *
     */

    public void setTag(String tagName) {

        tag += tagName;
    }

    /**
     * Method returns the name of the tag associated with
     * the task. Task tag will be displayed as #tagname
     * in the display messages
     *
     */

    public String getTag() {

        return tag;
    }


    @Override
    public String toString() {

        if (tag.trim().equals("")) {

            return description;

        } else {

            return description + " [" + tag + "]";

        }

    }

}
