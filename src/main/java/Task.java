public class Task {
    protected String instruction;
    protected boolean isDone;

    public Task(String instruction){
        this.instruction = instruction;
        this.isDone = false;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713]" + instruction;
        } else {
            return "[\u2718]" + instruction;
        }
    }
}
