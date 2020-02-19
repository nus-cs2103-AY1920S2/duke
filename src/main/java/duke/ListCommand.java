package duke;

public class ListCommand extends Command {

    ListCommand(String str) {
        super(str);
    }

    String execute(TaskList lst,Storage storage,Ui ui,int tasks) {
        for (int i = 0; i < lst.getSize(); i++) {
            str += lst.getTask(i) + "\n";
        }
        return str;
    }

}