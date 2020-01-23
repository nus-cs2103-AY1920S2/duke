public class Task{
    protected Complete complete;
    protected String task;

    public Task(String task){
        this.complete = new Complete(false);
        this.task = task;
    }

    public Task completeTask() {
        this.complete = new Complete(true);
        System.out.println("Nice! I've marked this task as done:" + "\n" + this.toString());
        return this;
    }

    @Override
    public String toString(){
        return ". " + "[ ] " + complete + task;
    }
}