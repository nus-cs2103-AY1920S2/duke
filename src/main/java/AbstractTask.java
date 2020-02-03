
public abstract class AbstractTask implements Task {

    String taskName;
    boolean isDone;

    public AbstractTask(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    protected String taskStateString(){
        return (this.isDone) ? "[✓]" : "[✗]";

    }

    public boolean markDone(){
        this.isDone = true;
        return this.isDone;
    }

}
