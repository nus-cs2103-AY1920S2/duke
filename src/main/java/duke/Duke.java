package duke;

import tool.*;

import java.util.Scanner;

public class Duke {
  public Duke() {}

  public static void main(String[] args) {
    Duke duke = new Duke();
    duke.run("", false);
  }

  public String run(String inputString, boolean isTest) {
    UI.UIString = "";
    TaskList taskList = new TaskList();
    Storage storage = new Storage("src/data/tasks.txt", taskList);
    UI ui = new UI(storage);

    if (!isTest) {
      storage.readFromFile();
    }

    Parser parser = new Parser(ui, taskList);

    ui.printWelcomeMessage();
    boolean isExit = false;
    Scanner stringScanner = new Scanner(inputString);

    while (!isExit) {
      String fullCommand = ui.readCommand(stringScanner, isTest);
      ui.printLine(); // show the divider line ("_______")
      Command c = parser.parse(fullCommand);
      isExit = c.execute(taskList, ui);
      ui.printLine();

      if (!isTest) {
        storage.saveToFile();
      }
    }

    return UI.UIString;
  }
}
