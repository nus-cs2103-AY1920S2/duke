public class InvalidMission extends Mission {
    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
