public class Task {
    private String task;
    private boolean isDone;

    public Task(String taskName) {
        this.task = taskName;
        this.isDone = false;
    }

    public Task() {
        task = "";
    }

    public String getStatusIcon() {
        // return tick or cross symbols respectively.
        return (isDone ? ":)" : ":(");
    }

    public String getStatusNumber() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTask() {
        return task;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.task);
    }
}
