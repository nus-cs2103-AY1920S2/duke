package tool;

import java.util.Scanner;

public class UI {
  public static String UIString = "";
  private static String indent = "    ";
  private static String line = "    ____________________________________________________________";
  private Storage storage;
  private Scanner userScanner = new Scanner(System.in);

  public UI(Storage storage) {
    this.storage = storage;
  }

  public String readCommand(Scanner stringScanner, boolean isTest) {
    if (stringScanner.hasNextLine()) {
      return stringScanner.nextLine();
    } else {
      if (!isTest) {
        return userScanner.nextLine();
      } else {
        return "bye";
      }
    }
  }

  public void printWelcomeMessage() {
    String logo =
        indent
            + " ____        _        \n"
            + indent
            + "|  _ \\ _   _| | _____ \n"
            + indent
            + "| | | | | | | |/ / _ \\\n"
            + indent
            + "| |_| | |_| |   <  __/\n"
            + indent
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println(
        line
            + "\n"
            + indent
            + "Hello! I'm Snow\n"
            + indent
            + "What can I do for you?\n"
            + logo
            + line);
  }

  public void printLine() {
    System.out.println(line);
    UIString += line + "\n";
  }

  public void print(String message) {
    System.out.println(indent + message);
    UIString += indent + message + "\n";
  }

  public void printError(String message) {
    System.out.println(line);
    System.out.println(indent + message);
    System.out.println(line);
    UIString += line + "\n" + indent + message + "\n" + line;
  }
}
