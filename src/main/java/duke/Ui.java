package duke;

import duke.tasks.Task;
import duke.utilities.Storage;
import duke.exceptions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The UI class for duke.Duke
 **/
public class Ui {
    protected String logoPath = "duke/files/logo.txt";
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
        this.defaultMsg("SQUIRTLE shall retreat!!");
    }

    public void listMsg(ArrayList<Task> lst) {
        this.defaultMsg("SQUIRTLE has to attack: ");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + lst.get(i));
        }
    }

    public void taskMsg(Task task, int lstSize) {
        this.defaultMsg("adding: " + task.toString() + "\nSQUIRTLE now has "
                    + lstSize + " thing(s) to do!!");
    }

    public void deleteMsg(Task task) {
        this.defaultMsg("SQUIRTLE will forget: " + task.toString() + " ~~");
    }

    public void doneMsg(Task task) {
        this.defaultMsg("SQUIRTLE used water gun on: " + task.toString() + "!\n\tIt is super effective!!");
    }

    public void lineBreak() {
        String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(lineBreak);
    }

    public void errorMsg(DukeException e) {
        switch(e.error) {
            case NUMBER: // error in accessing list, no such task exists
                System.out.println("SQUIRTLE cannot find task!!");
                break;
            case INSUFFICIENT:
                System.out.println("SQUIRTLE needs more info!!");
                break;
            case COMMAND:
                System.out.println("SQUIRTLE doesn't understand!!");
                break;
            default:
                System.out.println("SQUIRTLE error!");
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
