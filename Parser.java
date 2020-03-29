import java.util.ArrayList;

/**
 * Represents a parsed object
 * @author kerwei
 * @version 1.0
 */
public class Parser {

    String[] inputDetails;
    TaskList taskList;
    ArrayList<String> tasksSaved;
    ArrayList<Task> previousTasks;
    ArrayList<String> previousInstructions;
    ArrayList<Integer> previousTaskIndex;

    public Parser(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved,
                  ArrayList<Task> previousTasks, ArrayList<String> previousInstructions, ArrayList<Integer> previousTaskIndex) {
        this.inputDetails = inputDetails;
        this.taskList = taskList;
        this.tasksSaved = tasksSaved;
        this.previousTasks = previousTasks;
        this.previousInstructions = previousInstructions;
        this.previousTaskIndex = previousTaskIndex;
    }

    /**
     * Parse the user input
     * @param inputDetails Array of user input
     * @param taskList List of tasks
     * @param tasksSaved List of tasks saved
     */
    public static String parse(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved,
                                ArrayList<Task> previousTasks, ArrayList<String> previousInstructions, ArrayList<Integer> previousTaskIndex) {
        String typeOfTask = inputDetails[0];
        String dateTime = "";
        String response = "";
        try {
            switch (typeOfTask) {
                case ("list"):
                    response = taskList.toString();
                    break;
                case ("todo"):
                    response = addTodo(inputDetails, taskList, tasksSaved, typeOfTask, previousTasks, previousInstructions);
                    break;
                case ("deadline"):
                    response = addDeadline(inputDetails, taskList, tasksSaved, typeOfTask, previousTasks, previousInstructions);
                    break;
                case ("event"):
                    response = addEvent(inputDetails, taskList, tasksSaved, typeOfTask, previousTasks, previousInstructions);
                    break;
                case ("done"):
                    response = done(inputDetails,taskList, tasksSaved, typeOfTask, previousTasks, previousInstructions, previousTaskIndex);
                    break;
                case ("delete"):
                    response = delete(inputDetails,taskList, tasksSaved, typeOfTask, previousTasks, previousInstructions);
                    break;
                case ("find"):
                    response = find(inputDetails,taskList, tasksSaved, typeOfTask);
                    break;
                case ("undo"):
                    response = undo(taskList, tasksSaved, previousTasks, previousInstructions, previousTaskIndex);
                    break;
                case ("bye"):
                    response = "hope to see you again BYE BYEE!!";
                    break;
                default:
                    throw new DukeException("dont anyhow type la idiot");
            }
        } catch (DukeException error) {
            response = error.getMessage();
        }
        return response;
    }

    /**
     * Returns the type of task
     * @param inputDetails Array of user input
     * @return Type of task
     */
    public static String getTypeOfTask(String[] inputDetails){
        return inputDetails[0];
    }

    /**
     * Adds a to do task to the task list
     * @param inputDetails Details of user input
     * @param taskList Lists of task
     * @param tasksSaved List of task saved
     * @param typeOfTask Type of task
     * @return Message that task has been added
     */
    public static String addTodo(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved, String typeOfTask,
                                    ArrayList<Task> previousTasks, ArrayList<String> previousInstructions) {
        String response;
        try{
            if (inputDetails[1] == null) {
                throw new NullPointerException("error");
            } else {
                String detailOfTask = inputDetails[1];
                Todo newTask = new Todo(typeOfTask, detailOfTask, "");
                taskList.add(newTask);
                tasksSaved.add(typeOfTask + " /" + newTask.isDone + " /" + detailOfTask + " / " + "");
                previousTasks.add(newTask);
                previousInstructions.add("todo");
                response = ("added: " + newTask.toString());
            }
        } catch (NullPointerException error){
            try {
                throw new DukeException("need description for todo to be added la idiot");
            } catch (DukeException err) {
                response = err.getMessage();
            }
        }
        return response;
    }

    /**
     * Adds a deadline task to the task list
     * @param inputDetails Details of user input
     * @param taskList Lists of task
     * @param tasksSaved List of task saved
     * @param typeOfTask Type of task
     * @return Message that task has been added
     */
    public static String addDeadline(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved, String typeOfTask,
                                     ArrayList<Task> previousTasks, ArrayList<String> previousInstructions) {
        String response;
        try {
            if (inputDetails[1] == null || inputDetails[2] == null) {
                throw new NullPointerException("error");
            } else {
                String detailOfTask = inputDetails[1];
                String dateTime = inputDetails[2];
                Deadline newTask = new Deadline(typeOfTask, detailOfTask, dateTime);
                taskList.add(newTask);
                tasksSaved.add(typeOfTask + " /" + newTask.isDone + " /" + detailOfTask + "/" + dateTime);
                previousTasks.add(newTask);
                previousInstructions.add("todo");
                response = ("added: " + newTask.toString());
            }
        } catch (NullPointerException error) {
            try {
                throw new DukeException("need description and date/ time for deadline to be added la idiot");
            } catch (DukeException err) {
                response = err.getMessage();
            }
        }
        return response;
    }

    /**
     * Adds an event task to the task list
     * @param inputDetails Details of user input
     * @param taskList Lists of task
     * @param tasksSaved List of task saved
     * @param typeOfTask Type of task
     * @return Message that task has been added
     */
    public static String addEvent(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved, String typeOfTask,
                                    ArrayList<Task> previousTasks, ArrayList<String> previousInstructions) {
        String response;
        try {
            if (inputDetails[1] == null || inputDetails[2] == null) {
                throw new NullPointerException("error");
            } else {
                String detailOfTask = inputDetails[1];
                String dateTime = inputDetails[2];
                Event newTask = new Event(typeOfTask, detailOfTask, dateTime);
                taskList.add(newTask);
                tasksSaved.add(typeOfTask + " /" + newTask.isDone + " /" + detailOfTask + "/" + dateTime);
                previousTasks.add(newTask);
                previousInstructions.add("event");
                response = ("added: " + newTask.toString());
            }
        } catch (NullPointerException error){
            try {
                throw new DukeException("need description and date/ time for event to be added la idiot");
            } catch (DukeException err) {
                response = err.getMessage();
            }
        }
        return response;
    }

    /**
     * Indicate that a task is done
     * @param inputDetails Details of user input
     * @param taskList Lists of task
     * @param tasksSaved List of task saved
     * @param typeOfTask Type of task
     * @return Message that task is mark as done
     */
    public static String done(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved, String typeOfTask,
                                 ArrayList<Task> previousTasks, ArrayList<String> previousInstructions, ArrayList<Integer> previousTaskIndex) {
        String response;
        try {
            if (inputDetails[1] == null) {
                throw new NullPointerException("error");
            } else {
                String detailOfTask = inputDetails[1];
                int index = Integer.parseInt(detailOfTask) - 1;
                Task currentTask = taskList.get(index);
                currentTask.isDone = true;
                tasksSaved.set(index, currentTask.type + " /" + currentTask.isDone + " /" +
                        currentTask.description + " /" + currentTask.dateTime);
                previousTaskIndex.add(index);
                previousInstructions.add("done");
                previousTasks.add(currentTask);
                response = ("Good job on getting this done!" + "\n" + currentTask.toString());
            }
        } catch (NullPointerException error){
            try {
                throw new DukeException("type the correct task number you are done with la idiot");
            } catch (DukeException err) {
                response = err.getMessage();
            }
        }
        return response;
    }

    /**
     * Delete a task from the task list
     * @param inputDetails Details of user input
     * @param taskList Lists of task
     * @param tasksSaved List of task saved
     * @param typeOfTask Type of task
     * @return Message that task has been deleted
     */
    public static String delete(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved, String typeOfTask,
                                    ArrayList<Task> previousTasks, ArrayList<String> previousInstructions) {
        String response;
        try {
            if (inputDetails[1] == null) {
                throw new NullPointerException("error");
            } else {
                String detailOfTask = inputDetails[1];
                Task currentTask = taskList.get(Integer.parseInt(detailOfTask) - 1);
                response = ("alright bye bye task" + "\n" + currentTask.toString());
                taskList.delete(Integer.parseInt(detailOfTask) - 1);
                tasksSaved.remove(Integer.parseInt(detailOfTask) - 1);
                previousTasks.add(currentTask);
                previousInstructions.add("delete");
            }
        } catch (NullPointerException error){
            try {
                throw new DukeException("type the correct task you want to delete la idiot");
            } catch (DukeException err) {
                response = err.getMessage();
            }
        }
        return response;
    }

    /**
     * Returns the list of task that matches the keyword
     * @param inputDetails Details of user input
     * @param taskList List of task
     * @param tasksSaved List of task saved
     * @param typeOfTask Type of task
     * @return List of task the matches the keyword
     */
    public static String find(String[] inputDetails, TaskList taskList, ArrayList<String> tasksSaved, String typeOfTask) {
        String response;
        String detailOfTask = inputDetails[1];
        response = "these are the matching tasks in your list:";
        taskList.find(detailOfTask);
        return response;
    }

    /**
     * Undo the last command
     * @param taskList List of task
     * @param tasksSaved List of task saved
     * @param previousTasks List of task edited
     * @param previousInstructions List of instruction executed
     * @return Message of status of undo
     */
    public static String undo(TaskList taskList, ArrayList<String> tasksSaved, ArrayList<Task> previousTasks,
                                ArrayList<String> previousInstructions, ArrayList<Integer> previousTaskIndex) {
        String response = "";
        try {
            if (previousInstructions.size() == 0) {
                throw new NullPointerException("error");
            } else {
                int lastCommand = previousTasks.size() - 1;
                String instruction = previousInstructions.get(lastCommand);
                Task task = previousTasks.get(lastCommand);
                int lastTask = taskList.getSize() - 1;
                int lastIndex = previousTaskIndex.size() - 1;

                switch (instruction) {
                    case ("todo"):
                    case ("deadline"):
                    case ("event"):
                        response = "Undo success \n" + "Task removed: " + taskList.get(lastTask);
                        taskList.delete(lastTask);
                        tasksSaved.remove(lastTask);
                        previousInstructions.remove(lastCommand);
                        previousTasks.remove(lastCommand);
                        break;
                    case ("done"):
                        int taskIndex = previousTaskIndex.get(lastIndex);
                        System.out.println(lastIndex + "   " + taskIndex);
                        Task doneTask = taskList.get(taskIndex);
                        System.out.println(doneTask);
                        doneTask.isDone = false;
                        tasksSaved.set(taskIndex, (doneTask.type + " /" + doneTask.isDone + " /" + doneTask.description + " /" + doneTask.dateTime));
                        response = "Undo success \n" + "Task remains undone: " + taskList.get(taskIndex);
                        previousInstructions.remove(lastCommand);
                        previousTaskIndex.remove(lastIndex);
                        previousTasks.remove(lastCommand);
                        break;
                    case ("delete"):
                        Task deletedTask = previousTasks.get(lastCommand);
                        taskList.add(deletedTask);
                        tasksSaved.add(deletedTask.type + " /" + deletedTask.isDone + " /" + deletedTask.description + " /" + deletedTask.dateTime);
                        response = "Undo success \n" + "Task added back: " + deletedTask;
                        previousInstructions.remove(lastCommand);
                        previousTasks.remove(lastCommand);
                        break;
                    default:
                        break;
                }
            }
        } catch (NullPointerException error) {
            try {
                throw new DukeException("there is nothing to undo :)");
            } catch (DukeException err) {
                response = err.getMessage();
            }
        }
        return response;
    }

}
