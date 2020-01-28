import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {
    private static void writeFile(String filePath, Chatbot bot) throws IOException {
        String textToAdd = "";

        //Create string of text to add to file
        for(int i = 0; i < bot.getRecord().size(); i++) {
            Task current = bot.getRecord().get(i);

            //Add type
            if(current instanceof ToDo) {
                textToAdd = textToAdd + "T | ";
            } else if(current instanceof Deadline) {
                textToAdd = textToAdd + "D | ";
            } else {
                textToAdd = textToAdd + "E | ";
            }

            //Add boolean
            if(current.getIsDone()) {
                textToAdd = textToAdd + "1 ";
            } else {
                textToAdd = textToAdd + "0 ";
            }

            //Add description
            if(current instanceof ToDo) {
                textToAdd = textToAdd + "| " + current.getDescription();
            } else if(current instanceof Deadline) {
                textToAdd = textToAdd + "| " + current.getDescription();
            } else {
                textToAdd = textToAdd + "| " + current.getDescription();
            }

            //Add at and by for events and deadlines
            if(current instanceof Deadline) {
                textToAdd = textToAdd + "| " + ((Deadline) current).by + "\n";
            } else if(current instanceof Event) {
                textToAdd = textToAdd + "| " + ((Event) current).at + "\n";
            } else {
                textToAdd = textToAdd + "\n";
            }

        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFile(String filePath, Chatbot bot) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        //add data in file into the arraylist in bot
        while(s.hasNext()) {
            String str = s.nextLine();
            String[] arr = str.split("\\|");

            //trim whitespaces
            for(int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }

            if(arr[0].equals("T")) {
                Task task = new ToDo(arr[2]);

                if(arr[1].equals("1")) {
                    task.setIsDone();
                }

                bot.addTask(task);

            } else if(arr[0].equals("D")) {
                Task task = new Deadline(arr[2], arr[3]);

                if(arr[1].equals("1")) {
                    task.setIsDone();
                }

                bot.addTask(task);
            } else {
                Task task = new Event(arr[2], arr[3]);

                if(arr[1].equals("1")) {
                    task.setIsDone();
                }

                bot.addTask(task);
            }
        }
    }

    public static void main(String[] args) {
            //Intialise chatbot and scanner objects
            Chatbot bot = new Chatbot("bot");
            Scanner sc = new Scanner(System.in);
            Path path = Paths.get("Data/duke.txt");

            //Read data from file
            try {
                readFile(path.toString(), bot);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }

            //Set greeting and greet
            String intro = "--------------------------------------------------\n" +
                    "Wassup! Wo shi " + bot.getName() + "\n" +
                    "You want what? \n" +
                    "--------------------------------------------------\n";
            bot.setGreeting(intro);
            bot.greet();

            //Set goodbye
            String goodbye = "--------------------------------------------------\n" +
                    "Why you so ba...bot has been killed\n" +
                    "--------------------------------------------------\n";
            bot.setGoodbye(goodbye);

            //read first input
            String input = sc.nextLine();
            String[] arr = input.split(" ");
            String command = arr[0];
            String description = "";


            //add inputs, list record and mark as done when asked
            while(!command.equals("bye")) {
                try {
                    if(command.equals("list")) {
                        bot.listRecord();
                    } else if(command.equals("done")) {
                        int num = Integer.parseInt(arr[1]);
                        bot.setDone(num);
                    } else if(command.equals("delete")) {
                        int num = Integer.parseInt(arr[1]);
                        bot.delete(num);
                    } else if(command.equals("todo")) {
                        if(arr.length == 1) {
                            throw new DukeException("todo");
                        } else {
                            //get task description
                            for(int i = 1; i < arr.length - 1; i++) {
                                description = description + arr[i] + " ";
                            }
                            description = description + arr[arr.length - 1];

                            //add to do
                            bot.addToDo(description);
                        }
                    } else if(command.equals("deadline")) {
                        if(arr.length == 1) {
                            throw new DukeException("deadline");
                        } else {
                            //get task description
                            for(int i = 1; i < arr.length - 1; i++) {
                                description = description + arr[i] + " ";
                            }
                            description = description + arr[arr.length - 1];
                            String[] array = description.split(" /by ");

                            //add deadline
                            bot.addDeadline(array[0], array[1]);
                        }
                    } else if(command.equals("event")) {
                        if(arr.length == 1) {
                            throw new DukeException("event");
                        } else {
                            //get task description
                            for(int i = 1; i < arr.length - 1; i++) {
                                description = description + arr[i] + " ";
                            }
                            description = description + arr[arr.length - 1];
                            String[] array = description.split(" /at ");

                            //add event
                            bot.addEvent(array[0], array[1]);
                        }
                    } else {
                        throw new DukeException("");
                    }

                } catch(Exception e){
                    System.out.println(e);
                }
                    //get new inputs
                    input = sc.nextLine();
                    arr = input.split(" ");
                    command = arr[0];
                    description = "";
            }

            try {
                writeFile(path.toString(), bot);
            } catch(IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            //exit
            bot.goodbye();
    }
}