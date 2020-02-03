public class HelpMission extends Mission {
    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showHelp();
    }
}
