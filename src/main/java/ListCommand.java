class ListCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("list" + "\n" + Duke.LINE + "\n" + "Here are your tasks!");
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTaskList().get(i));
        }
    }
}
