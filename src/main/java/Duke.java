import e0148811.duke.Task;

import java.util.ArrayList;
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
            if (instruction.equals("list")) {
                System.out.println("Here is the task list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1).toString());
                }
            } else if (instructionByWord.length == 2 && instructionByWord[0].equals("done")) {
                Task taskToBeCompleted = list.get(Integer.parseInt(instructionByWord[1]) - 1);
                taskToBeCompleted.setDone();
                System.out.println("Noted, the following task is marked done:");
                System.out.println(taskToBeCompleted.getTask());
            } else {
                Task t = new Task(instruction);
                list.add(t);
                System.out.println("Noted, the following pending task is stored:");
                System.out.println(instruction);
            }
        }

        System.out.println("Goodbye. See you next time!");
    }
}
