public class Task{
    protected int id;
    protected Complete complete;
    protected String task;

    public Task(String task, int id){
        this.id = id;
        this.complete = new Complete(false);
        this.task = task;
    }

    public void completeTask() {
        this.complete = new Complete(true);
        System.out.println("Nice! I've marked this task as done:" + "\n" + this.toString());
    }

    @Override
    public String toString(){
        return id + ". " + "[ ] " + complete + task;
    }
}