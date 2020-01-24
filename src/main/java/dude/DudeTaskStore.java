package dude;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DudeTaskStore implements IDudeTaskStore {
    private static String fileLocation = "data/dude.txt";
    private static Pattern storageFormat =
            Pattern.compile("\\d+\\.\\[(?<type>[TDE])]"
                    + "\\[(?<isDone>[OX])]"
                    + " (?<details>\\S+.*?)"
                    + "( \\(by: (?<by>\\S+.*)\\))?"
                    + "( \\((?<from>.*) to (?<to>.*)\\))?");

    public static DudeTaskStore restoreSession() {
        DudeTaskStore taskStore = new DudeTaskStore();
        File data = new File(fileLocation);
        Scanner sc;
        try {
            sc = new Scanner(data);
        } catch (FileNotFoundException e) {
            return taskStore;
        }

        while (sc.hasNext()) {
            String input = sc.nextLine();
            Matcher m = storageFormat.matcher(input);

            try {
                if (m.matches()) {
                    String details = m.group("details");
                    boolean isDone = m.group("isDone").equals("O");
                    switch (m.group("type")) {
                    case "T":
                        taskStore.addTask(new Todo(details, isDone));
                        break;
                    case "D":
                        LocalDate by = LocalDate.parse(m.group("by"),
                                DateTimeFormatter.ofPattern("d MMM yyyy"));
                        taskStore.addTask(new Deadline(details, by, isDone));
                        break;
                    case "E":
                        LocalDate from = LocalDate.parse(m.group("from"),
                                DateTimeFormatter.ofPattern("d MMM yyyy"));
                        LocalDate to = LocalDate.parse(m.group("to"),
                                DateTimeFormatter.ofPattern("d MMM yyyy"));
                        taskStore.addTask(new Event(details, from, to, isDone));
                        break;
                    }
                } else {
                    throw new ParsingException();
                }
            } catch (Exception e) {
                System.out.println("Warning: An error occurred while reading your existing todos. "
                        + "Some of your data may have been lost");
            }
        }
        sc.close();
        return taskStore;
    }

    private ArrayList<Task> taskList;

    private DudeTaskStore() {
        this.taskList = new ArrayList<>(100);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    public String[] showAllTasks() {
        String[] result = new String[taskCount()];
        for (int i = 1; i <= taskCount(); i++) {
            result[i-1] = String.format("%d.%s", i, getTask(i));
        }
        return result;
    }

    public void saveTasksToMemory() {
        try (FileWriter fw = new FileWriter(fileLocation)) {
            for (String taskString : showAllTasks()) {
                fw.write(taskString + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Warning: An error occurred when saving your tasks. "
                    + "Some of your data may have been lost");
        }
    }

    public int taskCount() {
        return this.taskList.size();
    }
}