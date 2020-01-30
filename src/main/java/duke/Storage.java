package duke;
import java.io.*;
import java.util.ArrayList;

public class Storage {
  String path;

  public Storage(String path) {
    this.path = path;
  }

  public void saveBaby(ArrayList<Task> taskList) throws IOException {
    BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
    StringBuilder tasks = new StringBuilder();
    for (Task task : taskList) {
      tasks.append(task.toSaveString()).append("\n");
    }
    taskWriter.write(tasks.toString());
    taskWriter.close();
  }

  public void loadBaby(TaskList taskList, Parser parser) throws IOException {
    BufferedReader taskLoader = new BufferedReader(new FileReader(path));
    String longCommand = taskLoader.readLine();
    while (longCommand != null) {
      String[] keywords = longCommand.split(" \\|\\| ");
      Task cur = null;
      switch (keywords[1]) {
        case "todo":
          cur = new Todo(keywords[2]);
          taskList.getTaskList().add(cur);
          break;
        case "deadline":
          cur = new Deadline(keywords[2], parser.stringToTime(keywords[3]));
          taskList.getTaskList().add(cur);
          break;
        case "event":
          cur = new Event(keywords[2], parser.stringToTime(keywords[3]));
          taskList.getTaskList().add(cur);
          break;
        default:
          System.out.println("error");
          break;
      }
      if (keywords[0].equals("1")) {
        assert cur != null;
        cur.done();
      }
      longCommand = taskLoader.readLine();
    }
    taskLoader.close();
  }
}
