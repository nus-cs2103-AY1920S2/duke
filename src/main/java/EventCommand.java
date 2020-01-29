public class EventCommand extends TaskCommand {
    public static boolean run(TaskList taskList, String param) throws MissingDescriptionException, MissingDeadlineParamException {
        if (param.equals("")) {
            throw new MissingDescriptionException();
        } else if (!param.contains(" /at ")) {
            throw new MissingDeadlineParamException();
        }
        String[] params = param.split(" /at ");
        Task t = new Event(params[0], params[1]);
        if (addTask(taskList, t)) {
            Ui.taskAdded(t, taskList);
        }
        return true;
    }
}
