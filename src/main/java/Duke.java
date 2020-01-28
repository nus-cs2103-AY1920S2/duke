import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        
        // load saved tasks from file
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() {
        ui.showGreeting();

        label:
        while (true) {
            String input = ui.getInput();
            
            try {
                Parser instruction = new Parser(input);
                Command command = instruction.getCommand();

                switch (command) {
                case BYE:
                    break label;
                case DONE:
                    setDoneAndShow(tasks, ui, Integer.parseInt(instruction.getParameters()));
                    break;
                case TODO:
                    addTaskAndShow(tasks, ui, new Todo(instruction.getParameters()));
                    break;
                case DEADLINE:
                    addTaskAndShow(tasks, ui,
                            new Deadline(instruction.getParameters(), instruction.getDate()));
                    break;
                case EVENT:
                    addTaskAndShow(tasks, ui,
                            new Event(instruction.getParameters(), instruction.getDate()));
                    break;
                case LIST:
                    tasks.showTasks();
                    break;
                case DELETE:
                    deleteTaskAndShow(tasks, ui, Integer.parseInt(instruction.getParameters()));
                    break;
                }
            } catch (InvalidInstructionException e) {
                System.out.format("\tError: %s. Please try again.%n%n", e.getMessage());
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("\tError: Unable to write to file.\n");
        }
        
        ui.showFarewell();
    }
    
    private static void setDoneAndShow(
            TaskList tasks, Ui ui, int taskNum) throws InvalidInstructionException {
        tasks.setAsDone(taskNum);
        ui.showSetAsDone(tasks, tasks.getTask(taskNum));
    }
    
    private static void addTaskAndShow(TaskList tasks, Ui ui, Task task) {
        tasks.addTask(task);
        ui.showAddTask(tasks, task);
    }    

    private static void deleteTaskAndShow(
            TaskList tasks, Ui ui, int taskNum) throws InvalidInstructionException {
        Task delTask = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        ui.showDeleteTask(tasks, delTask);
    }
}
