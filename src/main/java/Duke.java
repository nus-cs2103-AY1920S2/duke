import java.io.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        String[] CheckInput;
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        File file = new File("D:/duke/data/duke.txt");
        Store lib = new Store(file);
        DukeException DE = new DukeException();
        try {
            boolean result = file.exists();
            if(!result){
                boolean ans = file.createNewFile();
            } else {
                try {
                    Scanner newSN = new Scanner(file);
                    while(newSN.hasNextLine()){
                        String nxtLine = newSN.nextLine();
                        lib.load(nxtLine);
                    } //end while- for reading existing file
                    System.out.println(line);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }



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
                    lib.todo(NewInput);
                }
            } else if (input.contains("deadline")){
                CheckInput = input.split(" ");
                if (CheckInput.length <2 ){
                    DE.IncorrectInputDeadline();
                } else if (!input.contains("/")){
                    DE.DeadlineMissingDate();
                } else {
                    String NewInput = input.substring(9);
                    String[] ActionTime = NewInput.split("/");
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
                    String[] ActionTime = NewInput.split("/");
                    lib.event(ActionTime);
                }
            } else {
                DE.InvalidInput();
            }

        }

    }


}
