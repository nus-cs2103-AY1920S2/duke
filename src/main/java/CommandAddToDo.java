public class CommandAddToDo extends Command {
    private String name;

    public CommandAddToDo(String name) {
        this.name = name;
    }

    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {
        ToDo newToDo = new ToDo(name);
        String message = "Add new todo: " + newToDo.getName();
        tasks.add(newToDo);
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
