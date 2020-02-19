package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Storage class stores and loads the task information in Duke.txt. */
public class Storage {
  String filePath;
  File file;
  Scanner fileScanner;

  Storage(String filePath) throws DukeException {
    try {
      this.filePath = filePath;
      this.file = new File(filePath);
      this.fileScanner = new Scanner(file);
    } catch (FileNotFoundException f) {
		File dir = new File("data");
		dir.mkdir();
		this.file = new File(dir, "duke.txt");
		try {
			file.createNewFile();
			this.fileScanner = new Scanner(file);
		} catch (IOException e) {
			throw new DukeException("file", "");
		}
    }
  }

  /**
   * Interprets each line in Duke.txt and returns the appropiate task.
   *
   * @param inputLine each line of Duke.txt file.
   * @return Task parsed from the line.
   * @throws DukeException if the line is not any of the task types.
   */
  public static Task interpretLine(String inputLine) throws DukeException {
    String[] strArr = inputLine.split(" ");
    String doneStatus = strArr[0];
    String type = strArr[1];
    String input = inputLine.substring(2);
    switch (type) {
      case "deadline":
        Deadline d = new Deadline(input);
        if (doneStatus.equals("1")) {
          d.setDone();
          assert d.isDone : "deadline did not set done";
        }
        return d;
      case "event":
        Event e = new Event(input);
        if (doneStatus.equals("1")) {
          e.setDone();
          assert e.isDone : "event did not set done";
        }
        return e;
      case "todo":
        Todo td = new Todo(input);
        if (doneStatus.equals("1")) {
          td.setDone();
          assert td.isDone : "todo did not set done";
        }
        return td;
      case "reminder":
        Reminder r = new Reminder(input);
        if (doneStatus.equals("1")) {
          r.setDone();
          assert r.isDone : "reminder did not set done";
        }
        return r;
      default:
        throw new DukeException("read", "");
    }
  }

  public void saveData(TaskList tasks) throws DukeException {
    try {
      PrintWriter writer = new PrintWriter(filePath);
      for (int i = 0; i < tasks.getLength(); i++) {
        Task t = tasks.get(i);
        writer.println(t.getFormatForSave());
      }
      writer.close();
      assert countLines() == tasks.getLength() : "Number of tasks does not tally";
    } catch (IOException e) {
      throw new DukeException("file", "");
    }
  }

  public int countLines() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    int lines = 0;
    while (reader.readLine() != null) lines++;
    reader.close();
    return lines;
  }

  public ArrayList<Task> loadData() throws DukeException {
    ArrayList<Task> loadedList = new ArrayList<>();
    while (fileScanner.hasNextLine()) {
      String inputLine = fileScanner.nextLine();
      loadedList.add(interpretLine(inputLine));
    }
    if (loadedList.size() < 1) {
      throw new DukeException("emptyLoad", "");
    }
    return loadedList;
  }
}
