import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;


public class Duke{
    public static void main(String[] args) throws IOException,Exception {
        Scanner sc = new Scanner(System.in);
        String mesInput = "";
        ArrayList<Task> arrTask = new ArrayList<Task>();
//        TaskList tList = new TaskList(arrTask);
        Ui uiDisplay = new Ui();
        Storage  fileStorage = new Storage();
        String filePath = "duke.txt";
        File f = new File(filePath);
        while(!mesInput.equalsIgnoreCase("bye")){
            mesInput = sc.nextLine();
            String [] s = new String[2];
            Parser userCommand = new Parser(mesInput,uiDisplay,fileStorage);
            // store task

            if(mesInput.equalsIgnoreCase("bye")){
                break;
            }

            if(userCommand.getCommandType(mesInput).equals("todo") && !mesInput.equalsIgnoreCase("todo")){
                    userCommand.todoTaskCommand(mesInput,arrTask,uiDisplay,f);
            }else if(userCommand.getCommandType(mesInput).equalsIgnoreCase("list")) {
                TaskList tList = new TaskList(arrTask);
                tList.printTaskList();
            }else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("deadline") && !mesInput.equalsIgnoreCase("deadline")) {
                    userCommand.deadlineCommand(mesInput,arrTask,uiDisplay,f);
            }else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("event" ) && !mesInput.equalsIgnoreCase("event")) {
                    userCommand.eventCommand(mesInput,arrTask,uiDisplay,f);
            }else if (userCommand.getCommandType(mesInput).equalsIgnoreCase("done") && !mesInput.equalsIgnoreCase("done")){
                    userCommand.doneCommand(mesInput,arrTask,uiDisplay);
            }else if(mesInput.contains("delete") && !mesInput.equalsIgnoreCase("delete")){
                    userCommand.deleteCommand(mesInput,arrTask,uiDisplay);
            }else if (mesInput.equalsIgnoreCase("todo")){
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }else if(mesInput.equalsIgnoreCase("deadline")){
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a deadlines cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }else if(mesInput.equalsIgnoreCase("event")){
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a event cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }
        }
        uiDisplay.exitsMessage();




    }
}

class Deadline extends Task{
    private String by ;
    public Deadline(String description, String by){
        super(description);

        this.by= by;
    }

    @Override
    public String toString(){

        return "[D][" +super.getStatusIcon()+ "] "+ super.getDescription() + "(" + by +")" ;
    }
}

class Event extends Task {
    private String at ;
    public Event(String description, String at){
        super(description);
        this.at= at;
    }
    @Override
    public String toString(){
        return "[E][" + super.getStatusIcon()+ "] "+ super.getDescription() + "(" + at +  ")";
    }
}

