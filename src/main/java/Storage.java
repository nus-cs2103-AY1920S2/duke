import java.io.*;
import java.util.ArrayList;

/** Storage class manages the reading and writing of the txt file. */
public class Storage {

  private String filePath;
  private File file;
  private BufferedReader br;
  private FileWriter fw;

  /**
   * Constructor. Initialises the file if it exists. Creates the file the file does not exist.
   *
   * @param filePath Path of the file.
   * @throws IOException Throws an error if some sort of I/O error is met.
   */
  public Storage(String filePath) throws IOException {
    this.filePath = filePath;
    this.file = new File(filePath);
    if (!this.file.exists()) {
      this.file.getParentFile().mkdirs();
      this.file.createNewFile();
      this.file = new File(this.filePath);
    }
  }

  /**
   * This method converts the string of texts from the input file to the ArrayList of tasks.
   *
   * @return Returns the task list.
   * @throws FileNotFoundException Throws an error if file is not found.
   */
  public TaskList getTaskList() throws FileNotFoundException {
    br = new BufferedReader(new FileReader(file));
    ArrayList<String> stringArr = new ArrayList<>();
    String st;
    try {
      while ((st = br.readLine()) != null) {
        stringArr.add(st);
      }
    } catch (Exception e) {
      System.err.println(e);
    }
    return new TaskList(stringArr);
  }

  /**
   * Writes the tasks in the ArrayList of tasks to the output file.
   *
   * @throws IOException Throws an error if there is some sort of I/O error.
   */
  public void writeList() throws IOException {
    fw = new FileWriter(file);
    for (Task t : TaskList.getTaskList()) {
      //            System.out.println(t);
      fw.write(t.save() + "\n");
    }
    fw.flush();
  }
}
