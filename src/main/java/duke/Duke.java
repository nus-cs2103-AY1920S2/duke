package duke;

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
        new Duke("data/tasks.txt").runCli();
    }

    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the default file. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     */
    public Duke() {
        // load saved tasks from file
        try {
            storage = new Storage("data/tasks.txt");
            tasks = new TaskList(storage.loadTasks());
            ui = new Ui(tasks);
        } catch (IOException e) {
            System.out.println("Error: Cannot read or write to file");
            tasks = new TaskList();
            ui = new Ui(tasks);
        }
    }
    
    /**
     * Constructs a Duke object. An attempt will be made to load in tasks from the file specified. If the file
     * does not exist or cannot be accessed, an empty task list will be loaded.
     * 
     * @param filePath Path of file containing saved tasks.
     */
    public Duke(String filePath) {
        // load saved tasks from file
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasks());
            ui = new Ui(tasks);
        } catch (IOException e) {
            System.out.println("Error: Cannot read or write to file");
            tasks = new TaskList();
            ui = new Ui(tasks);
        }
    }

    /**
     * Starts the Duke task management assistant in CLI mode. The user will input commands and the appropriate
     * actions will be performed. Enter "bye" to exit the program.
     */
    public void runCli() {
        System.out.println(ui.showGreeting());

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
                    System.out.println(setDone(Integer.parseInt(instruction.getParameters())));
                    break;
                case TODO:
                    System.out.println(addTask(new Todo(instruction.getParameters())));
                    break;
                case DEADLINE:
                    System.out.println(addTask(
                            new Deadline(instruction.getParameters(), instruction.getDate())));
                    break;
                case EVENT:
                    System.out.println(addTask(
                            new Event(instruction.getParameters(), instruction.getDate())));
                    break;
                case LIST:
                    System.out.println(ui.showTasks());
                    break;
                case DELETE:
                    System.out.println(deleteTask(Integer.parseInt(instruction.getParameters())));
                    break;
                case FIND:
                    System.out.println(ui.showFound(tasks.findTasks(instruction.getParameters())));
                    break;
                default:
                    ;
                }
            } catch (InvalidInstructionException e) {
                System.out.format("Error: %s. Please try again.%n%n", e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: Unable to write to file.\n");
            }
        }
        
        System.out.println(ui.showFarewell());
    }

    /**
     * Processes instruction given by user. Used by GUI.
     * 
     * @param input Instruction given by user.
     * @return Response (or error message) according to instruction processed.
     */
    String processInstruction(String input) {
        try {
            Parser instruction = new Parser(input);
            Command command = instruction.getCommand();

            switch (command) {
            case BYE:
                return ui.showFarewell();
            case DONE:
                return setDone(Integer.parseInt(instruction.getParameters()));
            case TODO:
                return addTask(new Todo(instruction.getParameters()));
            case DEADLINE:
                return addTask(new Deadline(instruction.getParameters(), instruction.getDate()));
            case EVENT:
                return addTask(new Event(instruction.getParameters(), instruction.getDate()));
            case LIST:
                return ui.showTasks();
            case DELETE:
                return deleteTask(Integer.parseInt(instruction.getParameters()));
            case FIND:
                return ui.showFound(tasks.findTasks(instruction.getParameters()));
            case TAG:
                return addTag(instruction.getParameters());
            default:
                return "";
            }
            
        } catch (InvalidInstructionException e) {
            return String.format("Error: %s. Please try again.%n%n", e.getMessage());
        } catch (IOException e) {
            return "Error: Unable to write to file.\n";
        }
    }
    
    private String setDone(int taskNum) throws InvalidInstructionException, IOException {
        tasks.setAsDone(taskNum);
        storage.writeToFile(tasks);
        return ui.showSetAsDone(tasks.getTask(taskNum));
    }
    
    private String addTask(Task task) throws IOException {
        tasks.addTask(task);
        storage.writeToFile(tasks);
        return ui.showAddTask(task);
    }
    
    private String deleteTask(int taskNum) throws InvalidInstructionException, IOException {
        Task delTask = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        storage.writeToFile(tasks);
        return ui.showDeleteTask(delTask);
    }
    
    private String addTag(String parameters) throws InvalidInstructionException {
        // todo: move logic to Parser
        String[] paramsList = parameters.split(" ");
        
        if (paramsList.length < 2) {
            throw new InvalidInstructionException("Not enough parameters given");
        }
        
        try {
            int taskNum = Integer.parseInt(paramsList[0]);
            tasks.getTask(taskNum).addTag(paramsList[1]);
            return ui.showTagTask(tasks.getTask(taskNum));
        } catch (NumberFormatException e) {
            throw new InvalidInstructionException("Task number given is not an integer");
        }
    }
}
