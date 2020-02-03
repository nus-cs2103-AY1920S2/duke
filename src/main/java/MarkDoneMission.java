public class MarkDoneMission extends Mission {
    public MarkDoneMission(String input){
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        String[] words = input.split(" ");
        String str;
        try {
            str = ui.ShowMarkDone(tasks.getTask(Integer.valueOf(words[1]) - 1));
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            str = ui.showException("OOP!!! The number of tasks you have is only "
                    + tasks.getTaskNumber());
        }
        return str;
    }
}
