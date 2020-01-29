import e0148811.duke.Deadline;
import e0148811.duke.DukeException;
import e0148811.duke.Event;
import e0148811.duke.Task;
import e0148811.duke.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Duke {
    // a common error (invalid format) handling message prefix
    static final String FORMAT_CORRECTION = "Invalid format for the instruction you gave.\n"
            + "The correct format should be ";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        String instruction;

        try {
            BufferedReader br = new BufferedReader(new FileReader("data/duke.txt"));
            boolean isEmptyFile = true;
            String line;
            while ((line = br.readLine()) != null) {
                isEmptyFile = false;
                addEventToList(list, line);
            }
            if (isEmptyFile) {
                System.out.println("File found but empty. Start with an empty task list.");
            } else {
                System.out.println("File found. Load saved task list.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Start with an empty task list.");
        }

        System.out.println("Hello, this is Duke. "
                + "Please give me an instruction followed by relevant description.");

        while (!(instruction = sc.nextLine()).equals("bye")) {
            try {
                String[] instructionByWord = instruction.split(" ");
                int lengthOfArray = instructionByWord.length;
                String actionWord = instructionByWord[0];
                switch (actionWord) {
                case "list":
                    if (instructionByWord.length != 1) {
                        throw new DukeException(FORMAT_CORRECTION + "\"list\"");
                    }
                    printList(list);
                    break;
                case "todo":
                    if (instructionByWord.length == 1) {
                        throw new DukeException(FORMAT_CORRECTION
                                + "\"todo a_string_describing_the_task\"");
                    }
                    Task t = createATodoTask(instructionByWord, lengthOfArray);
                    addTaskToList(list, t);
                    writeToHardDisk(list);
                    break;
                case "deadline":
                    t = createADeadlineTask(instructionByWord, lengthOfArray);
                    addTaskToList(list, t);
                    writeToHardDisk(list);
                    break;
                case "event":
                    t = createAnEventTask(instructionByWord, lengthOfArray);
                    addTaskToList(list, t);
                    writeToHardDisk(list);
                    break;
                case "done":
                    if (instructionByWord.length != 2) {
                        throw new DukeException(FORMAT_CORRECTION
                                + "\"done a_positive_integer_indicating_the_index_of_the_task_done\"");
                    }
                    try {
                        int index = Integer.parseInt(instructionByWord[1]) - 1;
                        if (index >= list.size() || index < 0) {
                            throw new DukeException("Invalid index.\n" + getNumOfTasks(list)
                                    + " Please note that the index is one-based (begins with 1 instead of 0).");
                        } else {
                            markATaskDone(list.get(index));
                        }
                        writeToHardDisk(list);
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException(FORMAT_CORRECTION + "\"done a_positive_integer\"");
                    }
                case "delete":
                    if (instructionByWord.length != 2) {
                        throw new DukeException(FORMAT_CORRECTION
                                + "\"delete a_positive_integer_indicating_the_index_of_the_task_to_be_removed\"");
                    }
                    try {
                        int index = Integer.parseInt(instructionByWord[1]) - 1;
                        if (index >= list.size() || index < 0) {
                            throw new DukeException("Invalid index.\n" + getNumOfTasks(list)
                                    + " Please note that the index is one-based (begins with 1 instead of 0).");
                        } else {
                            deleteATask(list, index);
                        }
                        writeToHardDisk(list);
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException(FORMAT_CORRECTION + "\"done a_positive_integer\"");
                    }
                case "":
                    throw new DukeException("Empty line input. "
                            + "Please specify an instruction followed by relevant description.\n"
                            + "The valid instructions include: todo, deadline, event, list, done, bye.");
                default:
                    throw new DukeException("I don't understand this instruction.\n"
                            + "The valid instructions include: todo, deadline, event, list, done, bye.");
                }
            } catch (DukeException | IOException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Goodbye. See you next time!");
    }

    private static void addEventToList(List<Task> list, String line) {
        String[] lineByWord = line.split("//");
        boolean isDone;
        if (lineByWord[1].equals("T")) {
            isDone = true;
        } else {
            isDone = false;
        }
        switch (lineByWord[0]) {
        case "T":
            list.add(new Todo(isDone, lineByWord[2]));
            break;
        case "D":
            list.add(new Deadline(isDone, lineByWord[2], lineByWord[3]));
            break;
        case "E":
            list.add(new Event(isDone, lineByWord[2], lineByWord[3]));
        }
    }

    private static void writeToHardDisk(List<Task> list) throws IOException {
        FileWriter writer = new FileWriter("data/duke.txt");
        for (Task t : list) {
            writer.write(t.toSimplerString() + "\n");
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Task createAnEventTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        int indexOfAt = -1;
        for (int i = 1; i < lengthOfArray; i++) {
            if (instructionByWord[i].equals("/at")) {
                indexOfAt = i;
            }
        }
        if (indexOfAt == -1 || indexOfAt == 1 || indexOfAt == lengthOfArray) {
            throw new DukeException(FORMAT_CORRECTION
                    + "\"event a_string_describing_the_task /at a_string_describing_the_time\"");
        }
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, indexOfAt));
        String time = String.join(" ",
                Arrays.copyOfRange(instructionByWord, indexOfAt + 1, lengthOfArray));
        return new Event(description, time);
    }

    static Task createADeadlineTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        int indexOfBy = -1;
        for (int i = 1; i < lengthOfArray; i++) {
            if (instructionByWord[i].equals("/by")) {
                indexOfBy = i;
                break;
            }
        }
        if (indexOfBy == -1 || indexOfBy == 1 || indexOfBy == (lengthOfArray - 1)) {
            throw new DukeException(FORMAT_CORRECTION
                    + "\"deadline a_string_describing_the_task /by a_string_describing_the_deadline_time\"");
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
        System.out.println("Noted, the following task is stored:");
        System.out.println(task);
        System.out.println(getNumOfTasks(list));
    }

    static void markATaskDone(Task taskToBeCompleted) {
        taskToBeCompleted.setDone();
        System.out.println("Noted, the following task is marked done:");
        System.out.println(taskToBeCompleted);
    }

    private static void deleteATask(List<Task> list, int index) {
        Task t = list.remove(index);
        System.out.println("Noted, the following task is removed from the list:");
        System.out.println(t);
        System.out.println(getNumOfTasks(list));
    }

    private static void printList(List<Task> list) {
        System.out.println("Here is the task list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
        System.out.println(getNumOfTasks(list));
    }

    private static String getNumOfTasks(List<Task> list) {
        return "Currently there is/are " + list.size() + " task(s) in the list.";
    }
}
