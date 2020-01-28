public class Delete extends Command {
    String s;

    Delete(String s){
        super();
        this.s=s;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showLine();
        int num=Integer.valueOf(s);
        ui.printString("Noted. I've removed this task:");
        ui.printTask(num,tasks);
        ui.printString("Now you have "+ (tasks.getList().size() - 1) + " tasks in the list.");
        ui.showLine();
        tasks.list.remove(num-1);
    }

    boolean isExit(){
        return false;
    }
}