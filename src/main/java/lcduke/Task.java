package lcduke;

/** Ths creates a task object.
 */
public abstract class Task {
    private String description;
    protected boolean isDone;
    protected static int taskNo = 0;

    /** This is the constructor to create the Task Object.
     *
     * @param description Description of user's input.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        taskNo++;
    }

    /** This generates the icon to show if the task is done.
     *
     * @return icon of the task.
     */
    protected String getStatusIcon() {
        return (isDone ? Character.toString((char)43) : Character.toString((char)45)); //return tick or X symbols
    }

    /** This updates the status of the isDone.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /** This returns the status of isDone.
     *
     * @return isDone.
     */
    protected boolean getDone() {
        return isDone;
    }

    /** This returns the description of task.
     *
     * @return description of task
     */
    protected String getDescription() {
        return description;
    }

    protected abstract String printInit();

    /** This generates the description in the list.
     * @return the description in the list
     */
    public String toString() {
        String markSymbol;
        if (isDone) {
            markSymbol = Character.toString((char)43);
        } else {
            markSymbol = Character.toString((char)45);
        }
        return ("[" + markSymbol + "] " + description); //return tick or X symbols
    }
}
