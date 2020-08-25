package main.java;

public class myList {

    private String[] things;
    private int size;


    public myList(){
        this.size = 0;
        this.things = new String[100];

    }

    public void addItem(String item) {
        this.things[this.size] = item;
        this.size++;
        System.out.printf("\nadded: %s\n", item);
    }

    public int getSize(){
        return this.size;
    }

    public void printList(){

        for (int i = 0; i < this.getSize(); i++){
            System.out.printf("\n%d. %s", i+1, this.things[i]);
        }
        System.out.println("");
    }
}
