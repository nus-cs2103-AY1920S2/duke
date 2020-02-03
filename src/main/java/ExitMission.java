public class ExitMission extends Mission {
    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showExit();
    }
}
