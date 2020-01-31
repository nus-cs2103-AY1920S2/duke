import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private ArrayList<Task> taskArr;
    private String path = "data/duke.txt";

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArr){
        this.taskArr = taskArr;
    }

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

    private String doneTask(int index){
        taskArr.set(index - 1, taskArr.get(index - 1).completeTask());
        return "Complete task! :)" + "\n" + totalTask();
    }

    public String listToPrint() {
        int i = 1;
        String toPrint = "";
        for (Task x : taskArr) {
            toPrint += (i++) + "" + x.toString() + "\n";
        }
        return toPrint;
    }

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
                default:
                    return "How did you end up here?";
            }
        }catch (Exception e) {
            return "Cannot Compute :(";
        }
    }
}
