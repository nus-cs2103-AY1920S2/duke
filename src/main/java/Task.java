public class Task{
    protected Complete complete;
    protected String task;

    public Task(String task){
        this.complete = new Complete(false);
        this.task = task;
    }

    public Task(String task, boolean bool){
        this.complete = new Complete(bool);
        this.task = task;
    }

    public Task completeTask() {
        this.complete = new Complete(true);
        return this;
    }

    public boolean getComplete(){
        return complete.isCompleted();
    }

    public String getTask(){
        return task;
    }

    @Override
    public String toString(){
        return ". " + "[ ] " + complete + task;
    }
}