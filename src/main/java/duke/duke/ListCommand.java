package duke;

/**
 * Lists the tasks from tasklist from Duke.
 */
public class ListCommand extends Command {

    ListCommand(String str) {
        super(str);
    }

    /**
     *Lists out the tasks from tasklist.
     * @param lst from TaskList from Duke
     * @param storage from Storage from Duke
     * @param ui from UI from Duke from Duke
     * @param tasks from TaskNum from Duke
     * @return empty string
     */
    String execute(TaskList lst,Storage storage,Ui ui,TasksNum tasks) {
        for (int i = 0; i < lst.getSize(); i++) {
            str += lst.getTask(i) + "\n";
        }
        System.out.println(tasks.getNum() + "undone");
        return str;
    }

}