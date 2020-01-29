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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
                case "find":
                    if (instructionByWord.length != 2) {
                        throw new DukeException(FORMAT_CORRECTION
                                + "\"find a_single_word_without_empty_space\"");
                    }
                    String keyword = instructionByWord[1];
                    HashMap<Integer, Task> selectedList = new HashMap<>();
                    int count = 0;
                    for (int j = 0; j < list.size(); j++) {
                        Task task = list.get(j);
                        String taskDescription = task.getTask();
                        String[] descriptionByWord = taskDescription.split(" ");
                        for (String s : descriptionByWord) {
                            if (s.equals(keyword)) {
                                selectedList.put(j + 1, task);
                                count++;
                                break;
                            }
                        }
                    }
                    System.out.println(count + " task(s) were found containing keyword " + keyword + " :");
                    for (Integer index : selectedList.keySet()) {
                        System.out.println(index + ". " + selectedList.get(index));
                    }
                    break;
                case "clear":
                    if (instructionByWord.length != 2
                            || !((instructionByWord[1].equals("all")) || (instructionByWord[1].equals("done")))) {
                        throw new DukeException(FORMAT_CORRECTION + "\"clear all/done\"");
                    } else {
                        clearTheList(instructionByWord[1], list, sc);
                    }
                    break;
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

    private static void clearTheList(String word, List<Task> list, Scanner sc) throws IOException {
        if (word.equals("all")) {
            System.out.println("Are sure you want to clear all the tasks in the list?");
        } else {
            System.out.println("Are sure you want to clear all completed tasks in the list?");
        }
        System.out.println("Type \"yes\" or \"y\" to proceed. Type any other input to cancel.");
        String input = sc.nextLine();
        if (input.equals("yes") || input.equals("y")) {
            if (word.equals("all")) {
                list.clear();
                System.out.println("List is now empty.");
            } else {
                List<Task> newList = new ArrayList<>();
                for (Task t : list) {
                    if (!t.isDone()) {
                        newList.add(t);
                    }
                }
                list = newList;
                System.out.println("List cleared.");
                printList(list);
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            for (Task t : list) {
                writer.write(t.toSimplerString() + "\n");
            }
            writer.close();
        } else {
            System.out.println("Canceled.");
        }
    }

    private static void addEventToList(List<Task> list, String line) {
        String[] lineByWord = line.split("//");
        boolean isDone;
        isDone = lineByWord[1].equals("T");
        switch (lineByWord[0]) {
        case "T":
            list.add(new Todo(isDone, lineByWord[2]));
            break;
        case "D":
            list.add(new Deadline(isDone, lineByWord[2], LocalDate.parse(lineByWord[3])));
            break;
        case "E":
            list.add(new Event(isDone, lineByWord[2], LocalDate.parse(lineByWord[3])));
            break;
        }
    }

    private static void writeToHardDisk(List<Task> list) throws IOException {
        FileWriter writer = new FileWriter("data/duke.txt");
        for (Task t : list) {
            writer.write(t.toSimplerString() + "\n");
        }
        writer.close();
    }

    static Task createAnEventTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        try {
            int indexOfAt = -1;
            for (int i = 1; i < lengthOfArray; i++) {
                if (instructionByWord[i].equals("/at")) {
                    indexOfAt = i;
                }
            }
            if (indexOfAt == -1 || indexOfAt == 1 || indexOfAt == lengthOfArray) {
                throw new DukeException(FORMAT_CORRECTION
                        + "\"event a_string_describing_the_task /at YYYY-MM-DD\"");
            }
            String description = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, 1, indexOfAt));
            String time = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, indexOfAt + 1, lengthOfArray));
            return new Event(description, LocalDate.parse(time));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect format of date.\n"
                    + "The correct format should be YYYY-MM-DD.");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid value for year/month/date");
        }
    }

    static Task createADeadlineTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        try {
            int indexOfBy = -1;
            for (int i = 1; i < lengthOfArray; i++) {
                if (instructionByWord[i].equals("/by")) {
                    indexOfBy = i;
                    break;
                }
            }
            if (indexOfBy == -1 || indexOfBy == 1 || indexOfBy == (lengthOfArray - 1)) {
                throw new DukeException(FORMAT_CORRECTION
                        + "\"deadline a_string_describing_the_task /by YYYY-MM-DD\"");
            }
            String description = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, 1, indexOfBy));
            String deadline = String.join(" ",
                    Arrays.copyOfRange(instructionByWord, indexOfBy + 1, lengthOfArray));
            return new Deadline(description, LocalDate.parse(deadline));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect format of date.\n"
                    + "The correct format should be YYYY-MM-DD.");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid value for year/month/date");
        }
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
