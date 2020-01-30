import java.io.*;
import java.util.Scanner;

public class Duke {

    private Store lib;
    private Ui ui;
    private Scanner sn = new Scanner(System.in);
    private DukeException DE;
    String[] CheckInput;

    public Duke(String filepath){
        ui = new Ui();
        DE = new DukeException();
        File file = new File(filepath);
        lib = new Store(file);
        try {
            boolean result = file.exists();
            if(!result){
                boolean ans = file.createNewFile();
            } else {
                    Scanner newSN = new Scanner(file);
                    while(newSN.hasNextLine()){
                        String nxtLine = newSN.nextLine();
                        lib.load(nxtLine);
                    } //end while- for reading existing file
                    System.out.println(ui.line());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    } //end Duke

    public void run(){
        ui.showWelcome();
        while(sn.hasNext()){
            String input = sn.nextLine();
            if(input.equals("bye")) {
                lib.bye();
            } else if (input.equals("list")) {
                lib.list();
            } else if(input.contains("done")) {
                String[] splited = input.split(" ");
                int index = Integer.parseInt(splited[1]);
                lib.done(index);
            } else if(input.contains("delete")) {
                String[] splited = input.split(" ");
                int index = Integer.parseInt(splited[1]);
                lib.delete(index);
            }else if(input.contains("todo")) {
                CheckInput = input.split(" ");
                if (CheckInput.length <2 ){
                    DE.IncorrectInputTodo();
                } else {
                    String NewInput = input.substring(5);
                    System.out.println(lib.todo(NewInput));
                }
            } else if (input.contains("deadline")){
                CheckInput = input.split(" ");
                if (CheckInput.length <2 ){
                    DE.IncorrectInputDeadline();
                } else if (!input.contains("/")){
                    DE.DeadlineMissingDate();
                } else {
                    String NewInput = input.substring(9);
                    String[] ActionTime = NewInput.split("/", 2);
                    lib.deadline(ActionTime);
                }
            } else if (input.contains("event")) {
                CheckInput = input.split(" ");
                if (CheckInput.length <2 ){
                    DE.IncorrectInputEvent();
                } else if (!input.contains("/")) {
                    DE.EventMissingDate();
                }else {
                    String NewInput = input.substring(6);
                    String[] ActionTime = NewInput.split("/",2);
                    lib.event(ActionTime);
                }
            } else {
                DE.InvalidInput();
            }

        }
    }

    public static void main(String[] args) {
        new Duke("D:/duke/data/duke.txt").run();
    }


}
