package duke;

import java.util.ArrayList;

/**
 * The type MyList.
 */
public class MyList {

    /**
     * Instantiates a new list for tasks.
     */
    public MyList(){}

    private ArrayList<Task> listArray = new ArrayList<>();
    private int counter = 0;

    /**
     * Sets list array.
     *
     * @param task the task
     */
    public void setListArray(Task task){
        this.listArray.add(task);
        counter++;
    }

    /**
     * Gets array size.
     *
     * @return the array size
     */
    public int getArraySize() {
        return listArray.size();
    }

    /**
     * Gets task.
     *
     * @param num the num
     * @return the task
     */
    public Task getTask(int num) {
        return listArray.get(num-1);
    }

    /**
     * Deletes task.
     *
     * @param num the num
     */
    public void deleteTask(int num) {
        listArray.remove(num-1);
        counter--;
    }

    /**
     * Prints list.
     */
    public String printList() {
        String result = "";
        for(int i=0; i<counter; i++){
            result += i+1 + ". " + listArray.get(i) + "\n";
        }
        return result;
    }

}