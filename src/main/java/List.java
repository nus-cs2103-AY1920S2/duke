import java.util.ArrayList;

public class List{

    public List(){}

    private ArrayList<Task> listArray = new ArrayList<>();
    private int counter = 0;

    public void setListArray(Task task){
        this.listArray.add(task);
        System.out.println("added: " + task);
        counter++;
    }

    public Task getTask(int num){
        return listArray.get(num-1);
    }

    public void deleteTask(int num){
        System.out.println("Noted! The task " + listArray.get(num-1).getItem() + " will be removed");
        listArray.remove(num-1);
        counter--;
    }

    public void printCounter(){
        if(counter <= 1){
            System.out.println("You have " + counter + " task on the agenda");
        } else {
            System.out.println("You have " + counter + " tasks on the agenda");
        }
    }

    public void printList(){
        for(int i=0; i<counter; i++){
            System.out.println(i+1 + ". " + listArray.get(i));
        }
    }

}