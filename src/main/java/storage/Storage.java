package storage;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;
import exception.DukeException;

public class Storage {
  private Path filePath;
  private File file;

  public Storage(Path filePath) {
    this.filePath = filePath;
    this.file = new File(this.filePath.toString());
  }

  public void update(ArrayList<Task> tasks) {
    FileWriter fr = null;
    try {
      fr = new FileWriter(file);
      for (Task t : tasks) {
        fr.write(String.format("%s%n", t.toStorable())); // | separated data
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public ArrayList<Task> getTasksFromStorage() {
    ArrayList<Task> tempTasks = new ArrayList<>();
    try {
      List<String> lines = Files.readAllLines(this.filePath);
      for (String entry : lines) {
        try {
          Task newTask = Task.newTaskFromMemory(entry);
          tempTasks.add(newTask);
        } catch (DukeException err) {
          System.out.println("File has been corrupted");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tempTasks;
  }
}
