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
        try {
            return tasks.tag(index, tagDescription);
        } catch (InvalidIndexException e){
            return e.toString();
        }
    }
}
