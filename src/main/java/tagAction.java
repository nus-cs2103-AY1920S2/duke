public class tagAction implements Action {
    private int index;
    private String tagDescription;

    public tagAction(int index, String tagDescription){
        this.index = index;
        this.tagDescription = tagDescription;
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }

    @Override
    public String doSomething(TaskList tasks) {
        Task currentTask = tasks.getList().get(this.index - 1);
        Tag myTag = currentTask.addTag(tagDescription);
        return "I have added this tag: '" + myTag + "' to this task:\n" + currentTask;
    }
}
