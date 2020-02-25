/**
 * Processes user input and also serves as primary UI for the application
 */
public class Parser {

    /**
     * A list of Task objects to keep track of task changes
     */
    private TaskList taskList;

    /**
     * Creates a Parser object that is able to process user input
     *
     * @param taskList A list of task objects to keep track of task changes
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Recognises command prompts and does the appropriate task handling
     *
     * @param input A command prompt by the user to Duke
     * @return a string to be printed out on the duke application GUI
     */
    public String parse(String input) {

        if (input.equals("list")) {
            return taskList.list();
        } else if (input.contains("done")) {
            if (input.length() >= 6) {
                try {
                    int taskNum = Integer.parseInt(input.substring(5));
                    assert taskNum <= taskList.size() : "Task number cannot be larger than list size";
                    return taskList.done(taskNum);
                } catch (NumberFormatException e) {
                    return "Please state a proper task number:(";
                }
            } else {
                return "Please state a task number:(";
            }
        } else if (input.contains("delete")) {
            //Delete task
            if (input.length() >= 8) {
                try {
                    int taskNum = Integer.parseInt(input.substring(7));
                    assert taskNum <= taskList.size() : "Task number cannot be larger than list size";
                    return taskList.delete(taskNum);
                } catch (NumberFormatException e) {
                    return "PLease state a proper task number to delete:(";
                }
            } else {
                return "Please state a task number to delete:(";
            }
        } else if (input.contains("find")) {
            if (input.length() >= 6) {
                String keyWord = input.substring(5);
                return taskList.find(keyWord);
            } else {
                return "Where is your keyword:(";
            }
        } else if (input.equals ("clear list")) {
            return taskList.clearList();
        } else if (input.equals ("commands")) {
            return taskList.commands();
        } else {
            //Create task using key words: "todo", "deadline", "event"
            if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                //if there are no task, no need to check for duplicates
                if (taskList.size() > 0 && taskList.containsDup(input)) {
                    return "This task has already been added before!";
                } else {
                    if (input.contains("todo")) {
                        return taskList.addTask("T", input);
                    } else if (input.contains("deadline")) {
                        return taskList.addTask("D", input);
                    } else {
                        return taskList.addTask("E", input);
                    }
                }
            } else {
                //invalid task format
                return "Back at you!";
            }
        }
    }
}
