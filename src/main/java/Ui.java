package main.java;

public class Ui {
    String line;

    public Ui(String line) {
        this.line = line;
    }

    public Ui() {}

    public String[] parseInput() {
        return line.split(" ", 2);
    }

    public String getCommand() {
        return (this.parseInput())[0];
    }

    public String getDescription() {
        return (this.parseInput())[1];
    }

    /**
     *
     * Gets task number
     * */
    public String getNumber() {
        return (this.parseInput())[1];
    }

    public String showList() {
        return ("Here are the tasks in your list: ");
    }

    public String showHelp() {
        return ("Commands:\n" +
                "list: Shows current tasks in list\n" +
                "todo: add todo in format ~todo task~\n" +
                "deadline: add task in format ~deadline task /by YYYY-mm-DD HH:mm~\n" +
                "event: add task in format ~event task /at YYYY-mm-DD HH:mm~\n" +
                "delete: delete task in format ~delete task num~\n" +
                "done: mark task as done in format ~done task num~\n" +
                "find: finds task with word in format ~find word~\n" +
                "viewschedule: shows schedule for day in format ~viewschedule /at YYYY-mm-DD\n" +
                "bye: saves tasks to storage\n");
    }

    public String doneTask(Task task) {
        return ("Nice I have marked this task as done\n" +
        " " + task.getDescription() + "\n");
    }

    public String deleteTask(String task) {
        return ("Deleted task: " + task);
    }

    public String addedTask(Task todo) {
        return ("Added task: " + todo.toString() + "\n");
    }

}