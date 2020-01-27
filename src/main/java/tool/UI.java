package tool;

import command.Command;

import java.util.Scanner;

public class UI {
    private static String indent = "    ";
    private static String line = "    ____________________________________________________________";
    private Storage storage;
    private Parser parser;

    public UI(Storage storage){
        this.storage = storage;
        this.parser = parser;
    }

    public void getInput(){
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            Command userCommand = Command.OTHERS;

            if (input.equals("bye")){
                parser.parseBye();
            } else if (input.equals("list")){
                parser.parseList();
            } else if (input.split(" ")[0].equals("done")){
                parser.parseDone(input);
            }  else if (input.split(" ")[0].equals("delete")){
                parser.parseDelete(input);
            } else if (input.split(" ")[0].equals("todo")){
                parser.parseToDos(input);
            } else if (input.split(" ")[0].equals("deadline")){
                parser.parseDeadlines(input);
            } else if (input.split(" ")[0].equals("event")){
                parser.parseEvents(input);
            } else {
                printError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            storage.saveToFile();

        }
    }

    public void printWelcomeMessage(){
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + indent + "Hello! I'm Snow\n" + indent + "What can I do for you?\n" + logo + line);
    }

    public void printLine(){
        System.out.println(line);
    }

    public void print(String message){
        System.out.println(indent + message);
    }

    public void printError(String message){
        System.out.println(line);
        System.out.println(indent + message);
        System.out.println(line);
    }

    public void setParser(Parser parser){
        this.parser = parser;
    }
}
