public class Todo extends Task {

    public Todo (String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [T]" + super.toString());
    }
}