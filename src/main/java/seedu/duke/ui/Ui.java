package seedu.duke.ui;

import seedu.duke.task.Task;

import java.util.List;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Greets the user when the GUI first starts and displays the available commands.
     * @return greetings and available commands format.
     */
    public String greet() {
        String greetings = "Hi there, I'm Dodo!\n"
                + "How may I help you today?\n"
                + "Available commands:\n"
                + "- To list all tasks: list\n"
                + "- To add new deadline: deadline [description] /by [due date in yyyy-mm-dd]\n"
                + "- To add new event: event [description] /at [date in yyyy-mm-dd]\n"
                + "- To add new todo: todo [description]\n"
                + "- To mark task as done: done [index]\n"
                + "- To delete a task: delete [index]\n"
                + "- To find a task: find [keyword]\n"
                + "- To exit: bye";
        return greetings;
    }

    public void showLoadingError() {
        System.out.println("Loading error. Try again!");
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    public void printFoundTask() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void printNoFoundTask() {
        System.out.println("Sorry I can't find what you are looking for....");
    }

    public void printRemoveTask() {
        System.out.println("Okay, I have removed this task for you:");
    }

    public void printAddToList() {
        System.out.println("Gotcha. Added this to your list:");
    }

    protected void printNoTaskYet() {
        System.out.println("You currently don't have any task. Start listing now!");
    }

    protected void printStopProcrastinating() {
        System.out.println("Stop procrastinating. Do it now!");
    }

    public void printTaskDone() {
        System.out.println("Good job! One off your chest!");
    }

    public void printTaskAlrDone() {
        System.out.println("Task is already done!");
    }

    public void printDuplicateFile() {
        System.out.println("File already exists.");
    }



    /**
     * Updates the user of the remaining number of tasks in the list.
     *
     * @param tasks The current list of tasks.
     */
    public void printNumTask(List<Task> tasks) {
        String taskWord;
        if (tasks.size() <= 1) {
            taskWord = "task";
        } else {
            assert tasks.size() > 1 : tasks.size();
            taskWord = "tasks";
        }
        System.out.printf("Now you got %d %s in your list!\n", tasks.size(), taskWord);
    }

    /**
     * Prints the list of tasks the user has.
     *
     * @param currTasks The list of tasks to be printed.
     */
    public void printList(List<Task> currTasks) {
        if (currTasks.size() == 0) {
            printNoTaskYet();
        } else {
            assert currTasks.size() > 0 : currTasks.size();
            printStopProcrastinating();
            for (int i = 0; i < currTasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, currTasks.get(i).toString());
            }
        }
    }





}
