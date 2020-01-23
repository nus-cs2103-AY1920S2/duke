public class Task{
    private int id;
    private boolean completed;
    private String task;

    public Task(String task, int id){
        this.id = id;
        this.completed = false;
        this.task = task;
    }

    public void completeTask() {
        this.completed = true;
        System.out.println("Nice! I've marked this task as done:" + "\n" + this.toString());
    }

    @Override
    public String toString(){
        String box = "";
        if(completed){
            box = "[Finished!] ";
        }else{
            box = "[Not Done yet] ";
        }
        return id + ". " + box + task;
    }
}