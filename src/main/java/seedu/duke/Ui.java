package seedu.duke;

import seedu.duke.task.Task;

import java.util.List;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public Ui() {

    }

    protected void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    protected void showLoadingError() {
        System.out.println("Loading error. Try again!");
    }

    protected void print(String str) {
        System.out.println(str);
    }

    protected void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    protected void printFoundTask() {
        System.out.println("Here are the matching tasks in your list:");
    }

    protected void printNoFoundTask() {
        System.out.println("Sorry I can't find what you are looking for....");
    }

    protected void printRemoveTask() {
        System.out.println("Okay, I have removed this task for you:");
    }

    protected void printAddToList() {
        System.out.println("Gotcha. Added this to your list:");
    }

    protected void printNoTaskYet() {
        System.out.println("You currently don't have any task. Start listing now!");
    }

    protected void printStopProcrastinating() {
        System.out.println("Stop procrastinating. Do it now!");
    }

    protected void printTaskDone() {
        System.out.println("Good job! One off your chest!");
    }

    /**
     * Updates the user of the remaining number of tasks in the list.
     *
     * @param tasks The current list of tasks.
     */
    protected void printNumTask(List<Task> tasks) {
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
     */
    protected void printList(List<Task> currTasks) {
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
