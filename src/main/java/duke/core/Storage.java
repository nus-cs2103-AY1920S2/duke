package duke.core;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import duke.exception.InvalidTimeFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a manager to handle all storage related functionality.
 */
public class Storage {
    public static final String DIRECTORY = "./data/";
    public static final String FILENAME = "tasks.txt";
    public static final String FILEPATH = "./data/tasks.txt";
    public static final String TEMP_FILEPATH = "./data/temp.txt";

    /**
     * Constructs a fresh Storage instance, with a supplied path for the file storing the task list.
     * @param fileName The file path for the task list file.
     */
    public Storage() {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public String getFilePath() {
        return FILEPATH;
    }

    /**
     * Loads tasks from the input file, and returns them in an ArrayList.
     * @return An ArrayList of all stored tasks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILEPATH);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String result = sc.nextLine();
                String[] split = result.split("~");
                String command = split[0];
                if (command.compareTo("T") == 0) {
                    Boolean isDone = Integer.parseInt(split[1]) == 1 ? true : false;
                    Todo todo = new Todo(split[2], isDone);
                    tasks.add(todo);
                } else if (command.compareTo("E") == 0) {
                    Boolean isDone = Integer.parseInt(split[1]) == 1 ? true : false;
                    Event event = new Event(split[2], isDone, split[3]);
                    tasks.add(event);
                } else if (command.compareTo("D") == 0) {
                    Boolean isDone = Integer.parseInt(split[1]) == 1 ? true : false;
                    Deadline deadline = new Deadline(split[2], isDone, split[3]);
                    tasks.add(deadline);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return tasks;
    }

    /**
     * Writes data to the file.
     * @param fileName The file containing all tasks.
     * @param data The data to be written.
     */
    public void writeToFile(String data) {
        try {
            FileWriter fw = new FileWriter(FILEPATH, true);
            fw.write(data);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            Ui.printLines("Sorry, the file input is invalid.");
        }
    }

    /**
     * Modifies the task in the task list file to mark it as done.
     * @param idx The index of the task in the task list.
     */
    public void doTask(int idx) {
        File originalFile = new File(FILEPATH);
        File tempFile = new File(TEMP_FILEPATH);

        try {
            FileWriter fw = new FileWriter(TEMP_FILEPATH, false);
            Scanner sc = new Scanner(originalFile);
            int i = 1;

            while (sc.hasNext()) {
                if (i == idx) {
                    String originalTask = sc.nextLine();
                    String modifiedTask = originalTask.replaceFirst("0", "1");
                    fw.write(modifiedTask + "\n");
                } else {
                    fw.write(sc.nextLine() + "\n");
                }
                i++;
            }
            sc.close();
            fw.close();

            originalFile.delete();
      
            tempFile.renameTo(originalFile);
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        } catch (IOException e) {
            Ui.printLines("IOException");
        }
    }

    /**
     * Deletes the task from the task list file.
     * @param idx The index of the task in the task list.
     */
    public void deleteTask(int idx) {
        File originalFile = new File(FILEPATH);
        File tempFile = new File(TEMP_FILEPATH);

        try {
            FileWriter fw = new FileWriter(TEMP_FILEPATH, false);
            Scanner sc = new Scanner(originalFile);
            int i = 1;

            while (sc.hasNext()) {
                String task = sc.nextLine();
                if (i != idx) {
                    fw.write(task + "\n");
                }
                i++;
            }
            sc.close();
            fw.close();

            originalFile.delete();
      
            tempFile.renameTo(originalFile);
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        } catch (IOException e) {
            Ui.printLines("IOException");
        }
    }

    public void updateDescription(int idx, String input) {
        File originalFile = new File(FILEPATH);
        File tempFile = new File(TEMP_FILEPATH);

        try {
            FileWriter fw = new FileWriter(TEMP_FILEPATH, false);
            Scanner sc = new Scanner(originalFile);
            int i = 1;

            while (sc.hasNext()) {
                if (i == idx) {
                    String originalTask = sc.nextLine();
                    String[] splitTask = originalTask.split("~");

                    int newDescriptionIndex = input.indexOf("" + idx);
                    String newDescription = input.substring(newDescriptionIndex + 2);

                    splitTask[2] = newDescription;
                    String modifiedTask = "";
                    for (int j = 0; j < splitTask.length; j++) {
                        modifiedTask += splitTask[j];
                        if (j != splitTask.length -1) {
                            modifiedTask += "~";
                        }
                    }
                    fw.write(modifiedTask + "\n");
                } else {
                    fw.write(sc.nextLine() + "\n");
                }
                i++;
            }
            sc.close();
            fw.close();

            originalFile.delete();
      
            tempFile.renameTo(originalFile);
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        } catch (IOException e) {
            Ui.printLines("IOException");
        }
    }

    public void updateTime(int idx, String input) throws InvalidTimeFormatException {
        File originalFile = new File(FILEPATH);
        File tempFile = new File(TEMP_FILEPATH);

        try {
            FileWriter fw = new FileWriter(TEMP_FILEPATH, false);
            Scanner sc = new Scanner(originalFile);
            int i = 1;

            while (sc.hasNext()) {
                if (i == idx) {
                    String originalTask = sc.nextLine();
                    String[] splitTask = originalTask.split("~");

                    int newTimeIndex = input.indexOf("" + idx);
                    String newTime = Parser.reformatDateAndTime(input.substring(newTimeIndex + 2));

                    splitTask[3] = newTime;
                    String modifiedTask = "";
                    for (int j = 0; j < splitTask.length; j++) {
                        modifiedTask += splitTask[j];
                        if (j != splitTask.length -1) {
                            modifiedTask += "~";
                        }
                    }
                    fw.write(modifiedTask + "\n");
                } else {
                    fw.write(sc.nextLine() + "\n");
                }
                i++;
            }
            sc.close();
            fw.close();

            originalFile.delete();
      
            tempFile.renameTo(originalFile);
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        } catch (IOException e) {
            Ui.printLines("IOException");
        }
    }
}