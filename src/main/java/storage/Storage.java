package storage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.parser.CommandSyntax;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * Handles the loading and saving of task lists to the hard disk.
 */
public class Storage {
    private Gson gson;
    private String userDirectory;
    final RuntimeTypeAdapterFactory<Task> typeFactory = RuntimeTypeAdapterFactory
            .of(Task.class, "type")
            .registerSubtype(Todo.class, "todo")
            .registerSubtype(Deadline.class, "deadline")
            .registerSubtype(Event.class, "event");

    /**
     * Initialises a storage object with gson and user directory.
     */
    public Storage() {
        gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();
        userDirectory = System.getProperty("user.dir");
    }

    /**
     * Searches for an existing file with a previous alias store.
     * If no such file found, reinitialise alias.
     */
    public CommandSyntax readAlias() {
        try {
            FileReader fileReader = new FileReader(userDirectory + "/alias.json");
            return gson.fromJson(fileReader, CommandSyntax.class);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new CommandSyntax();
        }
    }

    /**
     * Searches for an existing file with a previous task list store.
     * If no such file found, reinitialise task list.
     */
    public TaskList readTaskList() {
        try {
            FileReader task = new FileReader(userDirectory + "/data.json");
            return gson.fromJson(task, TaskList.class);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new TaskList(new ArrayList<>());
        }
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(userDirectory + "/data.json");
            gson.toJson(taskList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException io) {
            System.err.println(io.getMessage());
        }
    }

    /**
     * Writes alias into hard drive.
     */
    public void saveFile(CommandSyntax alias) {
        try {
            FileWriter aliasFile = new FileWriter(userDirectory + "/alias.json");
            gson.toJson(alias, aliasFile);
            aliasFile.flush();
            aliasFile.close();
        } catch (IOException io) {
            System.err.println(io.getMessage());
        }
    }
}
