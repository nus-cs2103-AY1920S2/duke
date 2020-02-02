import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The UI class for Duke
 **/
public class Ui {
    protected String logoPath = "logo.txt";
    protected String waterDroplets = "\uD83D\uDCA6";
    protected Storage storage;

    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(logoPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // print logo
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        this.welcomeMsg();
        this.promptMsg();
    }

    public void promptMsg(){
        this.lineBreak();
        System.out.print("What will SQUIRTLE do? > ");
    }

    public void exitMsg() {
        this.defaultMsg("Ui says bye bye!!!");
    }

    public void listMsg(ArrayList<Task> lst) {
        this.defaultMsg("your tasks!! ");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + lst.get(i));
        }
    }

    public void taskMsg(Task task, int lstSize) {
        this.defaultMsg("adding: " + task.toString() + "\nUi now has "
                    + lstSize + " thing(s) to do!!");
    }

    public void deleteMsg(Task task) {
        this.defaultMsg("Ui will forget: " + task.toString() + " ~~");
    }

    public void doneMsg(Task task) {
        this.defaultMsg("Ui used water gun on: " + task.toString() + "!\n\tIt is super effective!!");
    }

    public void lineBreak() {
        String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(lineBreak);
    }

    public void errorMsg(DukeException e) {
        switch(e.error) {
            case NUMBER: // error in accessing list, no such task exists
                System.out.println("Ui cannot find task!!");
                break;
            case INSUFFICIENT:
                System.out.println("Ui needs more info!!");
                break;
            case COMMAND:
                System.out.println("Ui doesn't understand!!");
                break;
            default:
                System.out.println("Ui says error!");
        }
    }

    public void welcomeMsg() {
        System.out.println(waterDroplets + " SQUIRTLE!! I'M HERE TO HELP!! SQUIRTLE!!");
    }

    public void defaultMsg(String message) {
        System.out.println(waterDroplets + " SQUIRTLE!!");
        System.out.println("\t" + message);
    }



}
