package com.duke.util;

import com.duke.tag.Tag;
import com.duke.tag.TagList;
import com.duke.task.Deadline;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.task.Todo;
import com.duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Represents a handler that interacts with the storage file in the hard-drive
 * through both loading and saving data into and from the current Duke session.
 */
public class Storage {
    private String filePath;

    /**
     * Instantiates a storage handler with the storage file in the hard-drive given as input.
     * @param taskfilePath The file path of the data file storing user information.
     */
    public Storage(String taskfilePath) {
        this.filePath = taskfilePath;

    }

    /**
     * Loads the data associated to the task list from the hard-drive into the Duke session.
     *
     * @return An <code>ArrayList</code> object that contains the all the tasks on the list
     *         and their relevant status.
     * @throws FileNotFoundException If the file path provided is invalid.
     */
    public ArrayList<Task> loadTaskList() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            Path p = Paths.get(filePath);
            Path p1 = p.getParent();
            Files.createDirectories(p1);
            Files.createFile(Paths.get(filePath));
        }

        Scanner s = new Scanner(f);
        if (!s.hasNext()) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> out = new ArrayList<>();
            while (s.hasNext()) {
                StringTokenizer st = new StringTokenizer(s.nextLine(), "|");
                String type = st.nextToken();
                int status = Integer.parseInt(st.nextToken());
                Task tba;
                if (type.equals("T")) {
                    tba = new Todo(st.nextToken());
                } else if (type.equals("D")) {
                    tba = new Deadline(st.nextToken(), st.nextToken());
                } else {
                    tba = new Event(st.nextToken(), st.nextToken());
                }
                if (status == 1) {
                    tba.setDone();
                }
                out.add(tba);
                if (st.hasMoreTokens()) {
                    loadTaskTag(tba, st.nextToken());
                }
            }
            return out;
        }
    }

    /**
     * Loads all the tags that are associated with a task in the hard drive into the Task object.
     * @param t The task to have its tags added.
     * @param tags The String representation of all its tags.
     */
    public void loadTaskTag(Task t, String tags) {
        if (tags.equals("")) {
            return;
        }

        String[] tagList = tags.split(",");
        for (String tag: tagList) {
            if (!tag.equals("")) {
                t.setTag(tag);
            }
        }

    }

    /**
     * Loads all the tags of all the tasks in the task list into one single TagList object for the current duke session.
     * @param taskList The list of tasks to process tags with.
     * @return A list of tags associated with all the tasks in the task list.
     */
    public TagList loadTags(TaskList taskList) {
        TagList tagList = new TagList();
        for (Task t: taskList.tasks) {
            for (String tag: t.getTags()) {
                Tag temp = tagList.addTag(tag);
                temp.addTaskToTag(t);
            }
        }
        return tagList;
    }

    /**
     * Saves the task data in the current Duke session into the hard-drive.
     *
     * @param taskList The task list that contains the updated task data.
     * @throws IOException When the file path provided is invalid.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        List<Task> ts = taskList.tasks;
        int count = ts.size();
        if (count == 0) {
            fw.write("");
        } else {
            for (int i = 0; i < count - 1; i++) {
                fw.write(ts.get(i).generateWriteFormat());
                fw.write('\n');
            }
            fw.write(ts.get(count - 1).generateWriteFormat());
            fw.close();
        }
    }
}
