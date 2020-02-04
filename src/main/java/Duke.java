package main.java;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.FileAlreadyExistsException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.stream.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



import main.java.*;


public class Duke extends Application{

    /**Declaration of variables */
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<>();
        final String FILEPATH;
        final File FILE;
        final String SIZE = "main/java/data/list.txt";
        final File SIZEFILE = new File(SIZE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Storage storage;
        

    /**
     * Constructor
     * @param String filepath
     */
    public Duke(String filepath) {
        this.FILEPATH = filepath;
        this.FILE = new File(filepath);
        this.storage = new Storage(filepath);
        
    }

    /**
     * Main method
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        
        Duke d = new Duke("main/java/data/list.txt");

        d.run();
    }

    /**
     * Run program
     * @throws DukeException
     */
    public void run() throws DukeException {
        /** Welcome Message */
        DukeGreeting dg = new DukeGreeting();
        dg.showWelcomeMessage();
        //dg.showInstructions();
        TaskList tl = new TaskList(FILEPATH, Tasks);
        ArrayList<Task> TL = tl.getTL();
        try {
            tl.loadFromStorage();
        } catch (Exception e) {
            e.getMessage();
        }

        while(true) {
            String line = sc.nextLine();
            Ui ui = new Ui(line);
            String command = ui.getCommand();
            if (command.equals("bye")) {
                try {
                    String S = "";
                    for(int i = 0; i < TL.size(); i++) {
                        S+= TL.get(i).toString() + '\n'; 
                    }
                    storage.writeToFile(FILEPATH, S);
                    dg.showGoodbyeMessage();
                    sc.close();
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("list")) {
                try {
                    ui.showList();
                    //storage.printFileContents(FILEPATH);
                    
                    for(int i = 0; i < TL.size(); i++) {
                        int j = i+1;
                        System.out.println(j + TL.get(i).toString());
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input");
                } 
            } else if (command.equals("done")) {
                    try {
                        /*String desc = ui.getDescription();
                        Task t = new Task(desc);
                        t.markAsDone();
                        String upd8task = storage.getTask(desc, FILEPATH).replaceAll("["+"\u2718"+"]", "["+"\u2713"+"]");
                        String f = File.createTempFile("temp", null).getAbsolutePath();
                        storage.removeLine(desc, FILEPATH, f);
                        storage.writeToFile(FILEPATH, upd8task + '\n');*/
                        int taskNum = Integer.parseInt(ui.getNumber());
                        Task t = TL.get(taskNum-1);
                        t.markAsDone();
                        
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                
            } else if (command.equals("todo")) {
                try {
                    //int size = Integer.parseInt((new BufferedReader(new FileReader(SIZEFILE)).readLine())); //shift this to storage
                    Todo todo = new Todo(ui.getDescription());
                    TL.add(todo);
                    //storage.writeToFile(FILEPATH, todo.toString()+'\n');
                    ui.addedTask(todo);
                    //increase the number in the size file
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                } 
            } else if (command.equals("deadline")) {
                try {
                    String desc =ui.getDescription();
                    String date = (desc.split("/by "))[1];
                    LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                    String sldt = ldt.format(formatter);
                    Deadline deadline = new Deadline(desc, sldt);
                    TL.add(deadline);
                    //storage.writeToFile(FILEPATH, deadline.toString()+'\n');
                    ui.addedTask(deadline);
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            } else if (command.equals("event")) {
                
                try {
                    String desc = ui.getDescription();
                    String date = (desc.split("/at "))[1];
                    LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                    String sldt = ldt.format(formatter);
                    Event event = new Event(desc, sldt);
                    TL.add(event);
                    ui.addedTask(event);
                    //storage.writeToFile(FILEPATH, event.toString()+'\n');
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            } else if (command.equals("delete")) {
                    try {
                        int taskNum = Integer.parseInt(ui.getNumber());
                        //long currsize = storage.getLineCount(FILE)-1;
                        Task t = TL.get(taskNum-1);
                        ui.deleteTask(t.toString());
                        //String f = File.createTempFile("temp", null).getAbsolutePath();
                        //storage.removeLine(num, FILEPATH, f);
                        TL.remove(taskNum-1);
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                    }
            } else if (command.equals("tasks")) {
                try {
                    String rest = ui.getDescription();
                    String[] q = rest.split("/on ");
                    String date = q[1];
                    LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                    String sldt = ldt.format(formatter);
                    System.out.println("Here are the tasks in your list for this date:");
                    //System.out.println(storage.getTask(sldt, FILEPATH));
                    System.out.println(tl.getTaskFromKeyword(sldt));
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                } 
         } else if(command.equals("find")) {
            try {
                String rest = ui.getDescription();
                System.out.println("Here are the tasks in your list that matches:" + rest);
                //System.out.println(storage.getTask(rest, FILEPATH));
                System.out.println(tl.getTaskFromKeyword(rest));
            } catch (Exception e) {
                System.out.println("Invalid Input");
            } 
         } else {
            try {
                throw new DukeException("Oops I'm sorry, what is this?"); 
            } catch (DukeException e) {
                System.out.println(e.getLocalizedMessage());
            }
            } 
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
/**
 * use the size file and make numbering automated
 */