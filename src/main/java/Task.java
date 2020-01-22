public class Task{

    private String item;
    private boolean isCompleted;

    public Task(String item){
        this.item = item;
        isCompleted = false;
        System.out.println(item + " has been added to list");
    }

    public String getItem(){
        return this.item;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + item;
    }

}