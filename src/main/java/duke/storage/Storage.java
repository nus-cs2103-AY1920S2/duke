/**
 * Storage stores and updates the duke text file
 * of any changes in the text list
 */

package duke.storage;

import duke.tasks.Task;
import duke.tasks.Events;
import duke.tasks.Deadline;
import duke.tasks.ToDos;
import duke.tasklist.TaskList;
import duke.exceptions.DukeException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    File file;
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        file = new File(filepath);
    }

    /**
     * load() fetches existing information from the text file and
     * updates the task list. load() is called when Duke starts.
     */
    public List<Task> load() throws DukeException, FileNotFoundException {
        List<Task> list = new ArrayList<Task>();

        Scanner scanExisting = new Scanner(file);
        while (scanExisting.hasNext()) {
            String line = scanExisting.nextLine();
            String[] addLine = line.split("@", 4);
            if (addLine[0].equals("T")) {
                ToDos t = new ToDos(addLine[2]);
                if (addLine[1].equals("1")) {
                    t.setDone();
                }
                list.add(t);
            } else if (addLine[0].equals("D")) {
                Deadline d = new Deadline(addLine[2], addLine[3]);
                if (addLine[1].equals("1")) {
                    d.setDone();
                }
                list.add(d);
            } else if (addLine[0].equals("E")) {
                Events e = new Events(addLine[2], addLine[3]);
                if (addLine[1].equals("1")) {
                    e.setDone();
                }
                list.add(e);
            }
        }
        return list;
    }

    /**
     * updateTask() updates the information int the text file when a task is completed
     */
    public void updateTask(int doneTask, TaskList tasks) throws IOException {
        List<Task> list = tasks.getList();
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        List<String> tempArr = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (i == doneTask) {
                String oldLine = br.readLine();
                String updatedLine = oldLine.replaceFirst("0", "1");
                tempArr.add(updatedLine);
            } else {
                tempArr.add(br.readLine());
            }
        }
        Files.delete(Paths.get(filepath));
        File newFile = new File(filepath);
        FileWriter fw = new FileWriter(filepath);
        for (int j = 0; j < tempArr.size(); j++) {
            if (j == 0) {
                fw.write(tempArr.get(j));
            } else {
                fw.write("\n");
                fw.write(tempArr.get(j));
            }
        }
        fw.close();
    }

    /**
     * deleteTask() updates the information int the text file when a task is deleted
     */
    public void deleteTask(int deleteTask, TaskList tasks) throws IOException {
        List<Task> list = tasks.getList();
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        List<String> tempArr = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (i == deleteTask) {
                String delete = br.readLine();
                continue;
            } else {
                tempArr.add(br.readLine());
            }
        }
        Files.delete(Paths.get(filepath));
        File newFile = new File(filepath);
        FileWriter fw = new FileWriter(filepath);
        for (int j = 0; j < tempArr.size(); j++) {
            if (j == 0) {
                fw.write(tempArr.get(j));
            } else {
                fw.write("\n");
                fw.write(tempArr.get(j));
            }
        }
        fw.close();
    }

    /**
     * addTodo() adds the new todo task to the text file
     */
    public void addTodo(TaskList tasks) throws IOException {
        List<Task> list = tasks.getList();
        Task t = list.get(list.size() - 1);
        FileWriter fw = new FileWriter(filepath, true);
        fw.write("\n");
        fw.write("T@" + t.getStatusNumber() + "@" + t.getDescription());
        fw.close();
    }

    /**
     * addDeadline() adds the new deadline task to the text file
     */
    public void addDeadline(TaskList tasks) throws IOException {
        List<Task> list = tasks.getList();
        Task t = list.get(list.size() - 1);
        FileWriter fw = new FileWriter(filepath, true);
        fw.write("\n");
        fw.write("D@" + t.getStatusNumber() + "@" + t.getDescription() + "@" + t.getDetails());
        fw.close();
    }

    /**
     * addEvent() adds the new event task to the text file
     */
    public void addEvent(TaskList tasks) throws IOException {
        List<Task> list = tasks.getList();
        Task t = list.get(list.size() - 1);
        FileWriter fw = new FileWriter(filepath, true);
        fw.write("\n");
        fw.write("E@" + t.getStatusNumber() + "@" + t.getDescription() + "@" + t.getDetails());
        fw.close();
    }
}