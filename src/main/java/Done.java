public class Done extends Command {
    String num;

    Done(String num){

        this.num=num;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showLine();
        int number=Integer.valueOf(num);
        Task ob = tasks.getList().get(number-1);
        ob.setDone();
        ui.printString("Nice! I've marked this task as done:");
        ui.printTask(number,tasks);
        ui.showLine();
    }

    boolean isExit(){
        return false;
    }
}