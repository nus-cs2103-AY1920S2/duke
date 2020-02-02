public class ListCommand extends Command{

    @Override
    public boolean execute (Storage storage, TaskList taskList, Squirtle ui) { // print out the task list
        ui.listMsg(taskList.getTaskList());
        return true;
    }
}
