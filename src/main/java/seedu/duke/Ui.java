package seedu.duke;

import seedu.duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * UI class that deals with interactions with the user.
 * Supports reading in user command and outputting response.
 */
public class Ui {

    private static final Scanner SC = new Scanner(System.in);
    private static final String HORIZONTAL_LINE = "------------------------------------------------------------";
    private static final String OUTPUT_INDENTATION = "    ";
    private static final String FORMAT_STRING_FOR_H_LINE =
            OUTPUT_INDENTATION + "|%-" + HORIZONTAL_LINE.length() + "s|";
    private static final String FORMAT_STRING_FOR_CONTENT =
            OUTPUT_INDENTATION + "|  %-" + (HORIZONTAL_LINE.length() - 2) + "s|";

    List<String> lastResponse;

    public List<String> getLastResponse() {
        return lastResponse;
    }

    void print(String s) {
        print(Collections.singletonList(s));
    }

    void print(String... strings) {
        print(Arrays.asList(strings));
    }

    void print(List<String> stringList) {
        lastResponse = stringList;

        System.out.println(String.format(FORMAT_STRING_FOR_H_LINE, HORIZONTAL_LINE));
        for (String s : stringList) {
            System.out.println(String.format(FORMAT_STRING_FOR_CONTENT, s));
        }
        System.out.println(String.format(FORMAT_STRING_FOR_H_LINE, HORIZONTAL_LINE));
    }

    void greet() {
        print("Hello! I'm Alfred!", "How may I help you today?");
    }

    /**
     * Prints goodbye message.
     */
    public void bye() {
        print("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Prints the list of task given a TaskList object.
     *
     * @param tasks TaskList object to be printed.
     */
    public void printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            print("List is empty");
            return;
        }

        List<String> outputStreamBuffer = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            String str = tasks.get(i).toString();
            String newStr = String.format("%d.%s", i + 1, str);
            outputStreamBuffer.add(newStr);
        }

        print(outputStreamBuffer);
    }

    /**
     * Given a TaskList, print the list of tasks with sorted message.
     *
     * @param tasks TaskList object to be printed.
     */
    public void printSortedTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            print("List is empty");
            return;
        }

        List<String> outputStreamBuffer = new ArrayList<>();

        outputStreamBuffer.add("Here are your tasks in chronological order:");
        for (int i = 0; i < tasks.size(); i++) {
            String str = tasks.get(i).toString();
            String newStr = String.format("%d.%s", i + 1, str);
            outputStreamBuffer.add(newStr);
        }

        print(outputStreamBuffer);
    }

    /**
     * Given a TaskList, print the list of tasks with matched message.
     *
     * @param tasks TaskList object to be printed.
     */
    public void printMatchedTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            print("No tasks found.");
            return;
        }

        List<String> outputStreamBuffer = new ArrayList<>();

        outputStreamBuffer.add("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String str = tasks.get(i).toString();
            String newStr = String.format("%d.%s", i + 1, str);
            outputStreamBuffer.add(newStr);
        }

        print(outputStreamBuffer);
    }

    String readCommand() {
        return SC.nextLine();
    }

    /**
     * Prints response message after marking a task as done.
     *
     * @param tasks TaskList object that contains the selected task.
     * @param selectedTask Task to be marked as done.
     */
    public void printDoneMessage(TaskList tasks, Task selectedTask) {
        print("Nice! I've marked this task as done: ",
                "  " + selectedTask,
                String.format("Now you have %d tasks in the list.", tasks.size())
        );
    }

    /**
     * Prints response message after deleting a task.
     *
     * @param tasks TaskList object that contains the selected task.
     * @param selectedTask Task to be deleted.
     */
    public void printDeleteMessage(TaskList tasks, Task selectedTask) {
        print("Noted. I've removed this task: ",
                "  " + selectedTask,
                String.format("Now you have %d tasks in the list.", tasks.size())
        );
    }

    /**
     * Prints response message after adding given task to task list.
     *
     * @param tasks TaskList object to which given Task object will be added.
     * @param newTask Task object to be added.
     */
    public void printAddMessage(TaskList tasks, Task newTask) {
        print("Got it. I've added this task: ",
                "  " + newTask,
                String.format("Now you have %d tasks in the list.", tasks.size())
        );
    }
}
