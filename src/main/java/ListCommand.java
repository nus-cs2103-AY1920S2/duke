class ListCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
