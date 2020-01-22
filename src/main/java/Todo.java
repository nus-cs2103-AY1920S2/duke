public class Todo extends Task {

    public Todo (String description, int index){
        super(description, index);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [T]" + super.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", index));
        System.out.println(super.line);
    }
}