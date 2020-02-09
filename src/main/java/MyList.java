import java.util.ArrayList;

public class MyList {

    public MyList(){}

    private ArrayList<Task> listArray = new ArrayList<>();
    private int counter = 0;

    public void setListArray(Task task){
        this.listArray.add(task);
        counter++;
    }

    public int getArraySize() {
        return listArray.size();
    }

    public Task getTask(int num) {
        return listArray.get(num-1);
    }

    public void deleteTask(int num) {
        listArray.remove(num-1);
        counter--;
    }

    public void printList() {
        for(int i=0; i<counter; i++){
            System.out.println(i+1 + ". " + listArray.get(i));
        }
    }

}