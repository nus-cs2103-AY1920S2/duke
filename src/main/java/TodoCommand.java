public class TodoCommand extends TaskCommand {
    public static boolean run(TaskList taskList, String param) throws MissingDescriptionException {
        if (param.equals("")) {
            throw new MissingDescriptionException();
        }
        Task t = new ToDo(param);
        if (addTask(taskList, t)) {
            Ui.taskAdded(t, taskList);
        }
        return true;
    }
}
