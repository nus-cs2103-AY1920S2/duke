package main.java;

public class MyList {

    private Task[] things;
    private int size;


    public MyList(){
        this.size = 0;
        this.things = new Task[100];
    }

    public void addItem(Task item) {
        this.things[this.size] = item;
        this.size++;
        System.out.printf("\nadded: %s\n", item.getName());
    }

    public int getSize(){
        return this.size;
    }

    public void printList(){

        for (int i = 0; i < this.getSize(); i++){
            Task item = this.things[i];
            System.out.printf("\n%d.", i+1);
            if (item.getStatus()) {
                System.out.printf("[✓] ");
            } else {
                System.out.printf("[✗] ");
            }
            System.out.printf("%s", item.getName());


        }
        System.out.println("");
    }

    public void completeTask(int index){

        if (index-1 < 0 || index-1 > this.getSize()){
            System.out.println("Error! No such task exists!");
            return;
        }
        this.things[index-1].setStatus(true);
        System.out.printf("\nNice! I've marked this task as done:");
        System.out.printf("\n[✓] %s\n", this.things[index-1].getName());
    }
}
