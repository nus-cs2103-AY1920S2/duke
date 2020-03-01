package duke;

public class TasksNum {
    int tasksnum;

    TasksNum() {}

    public void addNum() {tasksnum++;}

    public void subNum() {tasksnum--;}

    public void setNum(int num) {tasksnum = num;}

    public int getNum() {return tasksnum;}

    @Override
    public String toString() {return Integer.toString(tasksnum);}

}