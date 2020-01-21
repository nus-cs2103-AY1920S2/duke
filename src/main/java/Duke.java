import e0148811.duke.Deadline;
import e0148811.duke.Event;
import e0148811.duke.Task;
import e0148811.duke.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, this is Duke. " +
                "Please give me an instruction or specify a task to be stored.");

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        String instruction;
        while (!(instruction = sc.nextLine()).equals("bye")) {
            String[] instructionByWord = instruction.split(" ");
            int lengthOfArray = instructionByWord.length;
            String actionWord = instructionByWord[0];
            switch (actionWord) {
            case "list":
                printList(list);
                break;
            case "todo":
                Task t = createATodoTask(instructionByWord, lengthOfArray);
                addTaskToList(list, t);
                break;
            case "deadline":
                t = createADeadlineTask(instructionByWord, lengthOfArray);
                addTaskToList(list, t);
                break;
            case "event":
                t = createAnEventTask(instructionByWord, lengthOfArray);
                addTaskToList(list, t);
                break;
            case "done":
                markATaskDone(list.get(Integer.parseInt(instructionByWord[1]) - 1));
                break;
            }
        }

        System.out.println("Goodbye. See you next time!");
    }

    static Task createAnEventTask(String[] instructionByWord, int lengthOfArray) {
        int indexOfAt = -1;
        for (int i = 1; i < lengthOfArray; i++) {
            if (instructionByWord[i].equals("/at")) {
                indexOfAt = i;
            }
        }
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, indexOfAt));
        String time = String.join(" ",
                Arrays.copyOfRange(instructionByWord, indexOfAt + 1, lengthOfArray));
        return new Event(description, time);
    }

    static Task createADeadlineTask(String[] instructionByWord, int lengthOfArray) {
        int indexOfBy = -1;
        for (int i = 1; i < lengthOfArray; i++) {
            if (instructionByWord[i].equals("/by")) {
                indexOfBy = i;
                break;
            }
        }
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, indexOfBy));
        String deadline = String.join(" ",
                Arrays.copyOfRange(instructionByWord, indexOfBy + 1, lengthOfArray));
        return new Deadline(description, deadline);
    }

    static Task createATodoTask(String[] instructionByWord, int lengthOfArray) {
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, lengthOfArray));
        return new Todo(description);
    }

    static void addTaskToList(List<Task> list, Task task) {
        list.add(task);
        System.out.println("Noted, the following pending task is stored:");
        System.out.println(task);
        System.out.println("Currently there are " + list.size() + " tasks in the list.");
    }

    static void markATaskDone(Task taskToBeCompleted) {
        taskToBeCompleted.setDone();
        System.out.println("Noted, the following task is marked done:");
        System.out.println(taskToBeCompleted);
    }

    static void printList(List<Task> list) {
        System.out.println("Here is the task list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }
}
