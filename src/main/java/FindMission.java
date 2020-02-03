public class FindMission extends Mission {
    public FindMission(String input) {
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showFind(input.substring(5), tasks);
    }
}
