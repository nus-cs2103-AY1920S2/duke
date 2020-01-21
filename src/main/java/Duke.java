import e0148811.duke.Task;

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
            switch (instructionByWord[0]) {
            case "list":
                printList(list);
                break;
            case "todo":
                addAPendingTask(list, String.join(" ",
                        Arrays.copyOfRange(instructionByWord, 1, instructionByWord.length)));
                break;
            case "deadline":
                break;
            case "event":
                break;
            }

//            if (instruction.equals("list")) {
//            } else if (instructionByWord.length == 2 && instructionByWord[0].equals("done")) {
//                markATaskDone(list.get(Integer.parseInt(instructionByWord[1]) - 1));
//            } else {
//                addAPendingTask(list, instruction);
//            }
        }

        System.out.println("Goodbye. See you next time!");
    }

    static void addAPendingTask(List<Task> list, String instruction) {
        Task t = new Task(instruction);
        list.add(t);
        System.out.println("Noted, the following pending task is stored:");
        System.out.println(instruction);
    }

    static void markATaskDone(Task taskToBeCompleted) {
        taskToBeCompleted.setDone();
        System.out.println("Noted, the following task is marked done:");
        System.out.println(taskToBeCompleted.getTask());
    }

    static void printList(List<Task> list) {
        System.out.println("Here is the task list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
        }
    }
}
