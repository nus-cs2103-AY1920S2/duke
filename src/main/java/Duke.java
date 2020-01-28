import java.io.IOException;

/**
 * Duke is a task management assistant. It accepts user commands with the appropriate parameters and time
 * information (if any). It can add/delete tasks, mark tasks as done, and list all tasks. Upon exit, the
 * tasks will be saved to a data file. 
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the file specified. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     * 
     * @param filePath Path of file containing saved tasks.
     */
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

    /**
     * Starts the Duke task management assistant. The user will input commands and the appropriate actions
     * will be performed. Enter "bye" to exit the program.
     */
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
                    ui.showTasks(tasks);
                    break;
                case DELETE:
                    deleteTaskAndShow(tasks, ui, Integer.parseInt(instruction.getParameters()));
                    break;
                case FIND:
                    ui.showFound(tasks.findTasks(instruction.getParameters()));
                default:
                    ;
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
