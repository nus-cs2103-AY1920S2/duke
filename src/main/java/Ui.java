import java.util.Scanner;

public class Ui {
  Scanner io;

  Ui() {
    this.io = new Scanner(System.in);
  }

  public String getCommand() {
    return io.nextLine();
  }

  public void greeting() {
    System.out.println("----------------------------");
    System.out.println("Hello I'm your task manager!");
    System.out.println("----------------------------\n");
  }

  public void initPrompt() {
    System.out.println("\n----------------------------");
    System.out.println("What tasks do you have dude?");
    System.out.println("----------------------------");
  }

  public void printSmallLine() {
    System.out.println("    -----");
  }

  public void bye() {
    printSmallLine();
    System.out.println("    Bye bye friend!");
    printSmallLine();
  }
}
