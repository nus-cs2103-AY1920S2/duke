public class ListMission extends Mission {
    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showList(tasks);
    }
}
