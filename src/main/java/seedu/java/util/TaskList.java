package seedu.java.util;

import java.util.ArrayList;

/**
 * Handles Task and their operations.
 *  getTaskArr()
 * String listToPrint()
 * String read()
 */
public class TaskList {
    private ArrayList<Task> taskArr;
    private String path = "data/duke.txt";

    public TaskList() {
        System.out.println("Tasklist created");
        this.taskArr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    /**
     * Returns its ArrayList of Tasks.
     * @return taskArr
     */
    public ArrayList<Task> getTaskArr() {
        return taskArr;
    }

    private String totalTask() {
        return "You have " + (taskArr.size()) + " task on your list.";
    }

    private String addTodo(String task) {
        taskArr.add(new Todo(task));
        return "Affirmative. Adding a to-do task :) \n" + "added: " + task + "\n" + totalTask();
    }

    private String addDead(String task, String timing) {
        taskArr.add(new Deadline(task, timing));
        return "Affirmative. Adding a task with Deadline :) \n" + "added: " + task + "\n" + totalTask();
    }

    private String addEvent(String task, String timing) {
        taskArr.add(new Event(task, timing));
        return "Affirmative. Adding an event :) \n" + "added: " + task + "\n" + totalTask();
    }

    private String deleteTask(int index) {
        taskArr.remove(index - 1);
        return "Deleting this task. now you have less things to do :)" + "\n" + totalTask();
    }

    /**
     * Completes a task and returns a string to be printed by the UI.
     * @param index - the task that you want printed
     * @return String to print
     */
    private String doneTask(int index) {
        taskArr.set(index - 1, taskArr.get(index - 1).completeTask());
        return "Complete task! :)" + "\n" + totalTask();
    }

    /**
     * Returns a String of list of Task.
     * @return String of list
     */
    public String listToPrint() {
        int i = 1;
        String toPrint = "";
        for (Task x : taskArr) {
            toPrint += (i++) + "" + x.toString() + "\n";
        }
        return toPrint;
    }

    private String listToPrint(ArrayList<Task> tasks) {
        int i = 1;
        String toPrint = "";
        for (Task x : tasks) {
            toPrint += (i++) + "" + x.toString() + "\n";
        }
        return toPrint;
    }

    private String findTask(String keyword) {
        ArrayList<Task> matchedTask = new ArrayList<>();
        for (Task x: taskArr) {
            if (x.getTask().contains(keyword)) {
                matchedTask.add(x);
            }
        }
        if (matchedTask.isEmpty()) {
            return "I'm sorry, but your search garners no results";
        } else {
            return listToPrint(matchedTask);
        }
    }

    private String getHelp(String input) {
        String testForError = "";
        try {
            Command cmdHelp = Parser.readNextCommand(input);
            testForError += Text.printHelpForCommands(cmdHelp);
        } catch (Exception e) {
            testForError += Text.printGeneralHelp();
        }
        return testForError;
    }

    /**
     * Reads an input from UI, performs an operation, and returns a string to be printed.
     * @param input from user
     * @return String to print for confirmation
     */
    public String read(String input) {
        try {
            Command cmd = Parser.readCommand(input);
            switch (cmd) {
            case TODO:
                return addTodo(Parser.readTask(input));
            case DEADLINE:
                return addDead(Parser.readTask(input), Parser.readTiming(input));
            case EVENT:
                return addEvent(Parser.readTask(input), Parser.readTiming(input));
            case DONE:
                return doneTask(Parser.readNum(input));
            case DELETE:
                return deleteTask(Parser.readNum(input));
            case LIST:
                return listToPrint();
            case BYE:
                return "0";
            case FIND:
                return findTask(Parser.readTask(input));
            case HELP:
                return getHelp(input);
            default:
                return "Cannot compute. type wrongly? key in 'help' :)";
            }
        } catch (Exception e) {
            return "Still Cannot compute. type wrongly? key in 'help' :)";
        }
    }
}
