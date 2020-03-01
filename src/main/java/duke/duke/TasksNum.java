package duke;

/**
 * Stores number of tasks remaining for users.
 */
public class TasksNum {
    private int tasksnum;

    public TasksNum() {
        this.tasksnum = 0;
    }

    /**
     * Adds 1 number of tasks.
     */
    public void addNum() {
        tasksnum++;
    }

    /**
     * subtracts 1 number of tasks.
     */
    public void subNum() {
        tasksnum--;
    }

    /**
     * Retrieve number of tasks.
     * @return number of tasks
     */
    public int getNum() {
        return this.tasksnum;
    }

    /**
     * Modify number of tasks manually.
     * @param num of tasks
     */
    public void setNum(int num) {
        this.tasksnum = num;
    }
}
