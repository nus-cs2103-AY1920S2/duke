public class Todo extends Task{

    public Todo(String description) {
        super(description);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + this.toString());
        System.out.println("     Now you have " + super.taskNo + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
