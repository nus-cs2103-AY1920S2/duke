package com.nus.duke.storage;

import com.nus.duke.dao.DAOFactory;
import com.nus.duke.dao.DAOInterface;
import com.nus.duke.tasks.DisplayTaskFormatter;
import com.nus.duke.tasks.Tasks;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileStorage implements StorageInterface {
    private DAOInterface dataObj = DAOFactory.getOrCreate();

    private void write(String taskString, Writer writer) {
        try {
            writer.write(taskString);
            writer.write("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void write(Tasks tasks, Writer writer) {
        String taskString = DisplayTaskFormatter.stringify(tasks);
        this.write(taskString, writer);
    }

    private void write(List<Tasks> tasks, String toFile) {
        try (Writer writer = new FileWriter(toFile, true)) {
            for (Tasks eachTask : tasks) this.write(eachTask, writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initFile(String file) throws IOException {
        final Path fileLocation = Paths.get(file);
        if (Files.notExists(fileLocation.getParent()))
            Files.createDirectory(fileLocation.getParent());
        Files.deleteIfExists(fileLocation);
        Files.createFile(fileLocation);
    }

    private void saveTasks(List<Tasks> tasks, String toFile) {
        try {
            this.initFile(toFile);
            this.write(tasks, toFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(String toFile) {
        List<Tasks> tasks = dataObj.getAll();
        this.saveTasks(tasks, toFile);
    }

    @Override
    public void load() {

    }
}
