package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/** Represents the Duke object with which the user interacts. */
public class Duke {
  public static final String FILE_PATH = "data/data.txt";
  public static final String BYE_COMMAND = "bye";
  public static final String DONE_COMMAND = "done";
  public static final String DELETE_COMMAND = "delete";
  public static final String FIND_COMMAND = "find";
  public static final String TODO = "todo";
  public static final String DEADLINE = "deadline";
  public static final String EVENT = "event";
  public static final String LIST = "list";
  public static final String TASK_DESCRIPTION_ERROR = "OOPS, task description cannot be empty";
  public static final String INPUT_ERROR_MESSAGE =
      "OOPS, I don't understand this input. Please use a known command " + "and try again";
  public static final String FILE_SAVING_ERROR =
      "Error saving to file. Please check if 'data.txt' is present in " + "'/data/";

  private Storage storage;
  private TaskList tasks;
  private Ui ui;
  private Parser parser;

  /**
   * Creates a Duke Instance with given filePath.
   *
   * @param filePath is a String describing the relative path of the File object to which Duke will
   *     write/read.
   */
  public Duke(String filePath) {
    this.ui = new Ui();
    this.storage = new Storage(filePath);
    this.parser = new Parser();
    try {
      tasks = new TaskList(storage.getPreviousTasks(filePath));
    } catch (FileNotFoundException e) {
      tasks = new TaskList();
    }
  }

  /** Creates a Duke Instance with the file path specified in the constant. */
  public Duke() {
    this(FILE_PATH);
  }

  /**
   * Gets a response from Duke based on a set of input.
   *
   * @param input
   * @return a String with Duke's message.
   */
  public String getResponse(String input) {
    String output = "";
    if (input.equalsIgnoreCase(BYE_COMMAND)) {
      output += ui.byeMessage();
      try {
        storage.fillFileWithTasks(tasks.getTaskList());
      } catch (IOException e) {
        output += FILE_SAVING_ERROR;
      }
    } else if (input.startsWith(DONE_COMMAND)) {
      int index =
          Integer.parseInt(parser.parse(input, 2)[1]); // accept second argument from command line
      output = tasks.markDone(index);
    } else if (input.startsWith(DELETE_COMMAND)) {
      int index =
          Integer.parseInt(parser.parse(input, 2)[1]); // accept second argument from command line
      output = tasks.deleteTaskByIndex(index);
    } else if (input.equalsIgnoreCase(LIST)) {
      output = tasks.printList();
    } else if (input.startsWith(FIND_COMMAND)) {
      String[] parsedInput = parser.parse(input, 2);
      output = tasks.find(parsedInput[1]);
    } else {
      String[] parsedInput = parser.parse(input, 2);
      if (input.startsWith(TODO) || input.startsWith(DEADLINE) || input.startsWith(EVENT)) {
        try {
          output = tasks.addTask(parsedInput[0], parsedInput[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
          output += TASK_DESCRIPTION_ERROR;
        }
      } else {
        output += INPUT_ERROR_MESSAGE;
      }
    }
    return output;
  }
}
