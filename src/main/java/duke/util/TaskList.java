package duke.util;

import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.NoTaskNumberException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * TaskList
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 30 Jan 2020
 *
 * @author Jel
 */
public class TaskList {
    protected Storage storage;
    protected List<Task> tasks;

    TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.tasks;
    }

    protected void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    protected void listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Below is your task list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t");
            sb.append(i + 1);
            sb.append("" + tasks.get(i));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
        printSeparator();
    }

    protected void deleteTask(String line)
            throws NoTaskNumberException, InvalidIndexException, IOException {
        int size = tasks.size();
        String[] splitInput = line.split(" ");
        StringBuilder sb = new StringBuilder();
        if (splitInput.length > 1) {
            int n = Integer.parseInt(splitInput[1]);
            if (n < 1 || n > size) {
                throw new InvalidIndexException(n, size);
            } else {
                sb.append("Noted. I've removed this task:\n\t" + tasks.get(n - 1));
                tasks.remove(n - 1);
                sb.append("\nYou now have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
            }
        } else {
            throw new NoTaskNumberException();
        }
        System.out.println(sb);
        printSeparator();
        storage.clearAllData();
        storage.updateData();
    }

    protected void markTaskAsDone(String line)
            throws NoTaskNumberException, InvalidIndexException, IOException {
        int size = tasks.size();
        String[] splitInput = line.split(" ", 2);
        StringBuilder sb = new StringBuilder();
        if (splitInput.length > 1) {
            int taskNum = Integer.parseInt(splitInput[1]);
            if (taskNum < 1 || taskNum > size) {
                throw new InvalidIndexException(taskNum, size);
            }
            sb.append("Nice! I've marked this task as done:\n\t");
            Task t = tasks.get(taskNum - 1);
            t.markAsDone();
            sb.append(t);
            System.out.println(sb);
            printSeparator();
        } else {
            throw new NoTaskNumberException();
        }
        storage.clearAllData();
        storage.updateData();
    }

    private static boolean dateIsValid(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(source);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    protected void addTodo(String desc) throws IOException {
        Task toSave = new Todo(desc);
        tasks.add(toSave);
        storage.saveTask(toSave, true);
        actionConfirmation();
    }

    protected void addDeadlineOrEvt(String id, String descAndDate) throws IOException, InvalidDateException {
        String[] temp = descAndDate.split(" \\| ");
        if (dateIsValid(temp[1])) {
            if (id.equals(" /by ")) {
                Task toSave = new Deadline(temp[0].trim().toString(), LocalDate.parse(temp[1]));
                storage.saveTask(toSave, true);
                tasks.add(toSave);
            } else {
                Task toSave = new Event(temp[0].trim().toString(), LocalDate.parse(temp[1]));
                storage.saveTask(toSave, true);
                tasks.add(toSave);
            }
            actionConfirmation();
        } else {
            throw new InvalidDateException();
        }
    }

    protected void actionConfirmation() {
        int size = this.tasks.size();
        System.out.println("Got it. I've added this task:");
        System.out.print("\t" + this.tasks.get(size - 1));
        System.out.printf("\nYou now have %d %s in the list.\n", size, tasks.size() > 1 ? "tasks" : "task");
        printSeparator();
    }
}
