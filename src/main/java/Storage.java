import java.io.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filepath;

    /**
     * Constructor for the Storage class.
     * @param filepath File path of the storage file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads existing Tasks from the file storage to an ArrayList of Tasks.
     * @return An ArrayList of Tasks.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>(); // initialize a new ArrayList.

        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String task = sc.nextLine();

            String[] split_task = task.split(" - ");

            // The tasks inside the file should be stored in a format of completion status - task type - description - date
            // Or, completion status - task type - description, if it's a TODO Task
            assert split_task.length == 3 || split_task.length == 4: "Your memory is corrupted.";

            if (split_task.length == 3) { // TODO
                Todo new_todo = new Todo(split_task[2]);
                if (split_task[0].contains("\u2713")) { // read the completion status
                    new_todo.updateIsCompleted(true);
                }
                tasks.add(new_todo);
            }
            else { // EVENT
                LocalDate date = LocalDate.parse(split_task[3]); // handle the date object

                if (split_task[1].contains("E")) {
                    Event new_event = new Event(split_task[2], date);
                    if (split_task[0].contains("\u2713")) { // read the completion status
                        new_event.updateIsCompleted(true);
                    }
                    tasks.add(new_event);
                }
                else { // DEADLINE
                    Deadline new_deadline = new Deadline(split_task[2], date);
                    if (split_task[0].contains("\u2713")) {
                        new_deadline.updateIsCompleted(true);
                    }
                    tasks.add(new_deadline);
                }
            }
        }

        return tasks;
    }

    /**
     * Updates the storage file with the new TaskList of Tasks. Only called after the user inputs "bye!"
     * @param tasks Final TaskList of tasks which the user wants to maintain.
     */
    public void updateFile(TaskList tasks) {

        // delete everything in the file first
        File file = new File(filepath);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("Whoops! You're not on my onboard memory!");
        }

        // now we create a file writer and write one by one to the file
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.getTaskListSize(); i++) {
                String exception_symbol = tasks.get(i).getCompletionStatusAsString();
                String task_type = tasks.get(i).getTaskType();
                String description = tasks.get(i).getDescription();
                String date = "";
                String line = exception_symbol + " - [" + task_type + "] - " + description;
                if (task_type.equals("E") || task_type.equals("D")) {
                    date = tasks.get(i).getDateForWritingToFile();
                    line = exception_symbol + " - [" + task_type + "] - " + description + " - " + date;
                }
                writer.write(line + "\n");
            }
            writer.close();
        }
        catch (IOException exception) {
            System.out.println("Whoops! You're not on my onboard memory!");
        }

    }

}

