public class List{

    public List(){}

    private Task[] listArray = new Task[100];
    private int counter = 0;

    public void setListArray(Task task){
        this.listArray[counter] = task;
        System.out.println("added: " + task);
        counter++;
    }

    public Task getTask(int num){
        return listArray[num-1];
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
            System.out.println(i+1 + ". " + listArray[i]);
        }
    }

}