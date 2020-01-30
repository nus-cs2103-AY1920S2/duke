public class Todo extends Task {
    /**
     * This method updates the action to be taken.
     * @param description This is the action to be taken.
     */
    public Todo (String description){
        super(description);
    }

    @Override
    /*Override the toString method for To do*/
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * This method indicates that a new Task has been added to the ArrayList in Store.
     */
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [T]" + super.toString());
    }
}