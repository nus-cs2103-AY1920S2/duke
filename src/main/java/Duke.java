import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void loadFileContents(String filePath , ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskString = nextLine.split("/");
            String taskTitle = taskString[0];
            switch (taskTitle){
                case "T":
                    Task todoTask = new Todo(taskString[2]);
                    if(taskString[1].equals("1")){
                        todoTask.markDone();
                    }
                    tasks.add(todoTask);
                    break;

                case "D":
                    Task deadlineTask = new Deadline(taskString[2], taskString[3]);
                    if(taskString[1].equals("1")){
                        deadlineTask.markDone();
                    }
                    tasks.add(deadlineTask);
                    break;

                case "E":
                    Task eventTask = new Event(taskString[2], taskString[3]);
                    if(taskString[1].equals("1")){
                        eventTask.markDone();
                    }
                    tasks.add(eventTask);
                    break;

                default:
                    break;
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {  //append to file
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException { //overwrite file
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "    ____________________________________________________________" + "\n";
        String fiveSpaces = "      ";
        System.out.println(line + fiveSpaces + "Hello! I'm Duke\n" + fiveSpaces + "Whatcha wanna do?\n" + line);
        String filePath = "/Users/freddy/Desktop/duke/src/main/java/tasks.txt";
        try{
            loadFileContents(filePath, tasks);
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }

        while (scanner.hasNextLine()) {
            try{
                Scanner commandScanner = new Scanner(scanner.nextLine());
                String word = commandScanner.next();
                if (word.equals("bye")) {
                    System.out.println(line + fiveSpaces + "See ya later alligator!\n" + line);
                    break;
                } else {
                    String taskName = "";
                    switch (word) {
                        case "list":
                            System.out.print(line);
                            System.out.println("     Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println("      " + Integer.toString(i + 1) + "." + tasks.get(i));
                            }
                            System.out.println(line);
                            break;

                        case "done":
                            int doneTaskNum = Integer.parseInt(commandScanner.next()) - 1;
                            tasks.get(doneTaskNum).isDone = true;
                            System.out.print(line);
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + tasks.get(doneTaskNum).toString());
                            System.out.println(line);
                            writeToFile(filePath, Task.toStringDukeTasks(tasks));
                            break;

                        case "delete":
                            int deleteTaskNum = Integer.parseInt(commandScanner.next()) - 1;
                            System.out.print(line);
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + tasks.get(deleteTaskNum).toString());
                            tasks.remove(deleteTaskNum);
                            System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                            System.out.println(line);
                            writeToFile(filePath, Task.toStringDukeTasks(tasks));
                            break;

                        case "todo":
                            if (!commandScanner.hasNext()) {
                                throw new DukeException(word);
                            } else {
                                while (commandScanner.hasNext()) {
                                    taskName += commandScanner.next() + " ";
                                }
                                taskName = taskName.substring(0, taskName.length() - 1);
                                Task todoTask = new Todo(taskName);
                                tasks.add(todoTask);
                                System.out.print(line);
                                System.out.println("     Got it. I've added this task:");
                                System.out.println("       " + todoTask.toString());
                                System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                                System.out.println(line);
                                appendToFile(filePath, todoTask.toStringTaskstxt());
                                break;
                            }

                        case "deadline":
                            while (commandScanner.hasNext()) {
                                taskName += commandScanner.next() + " ";
                            }
                            taskName = taskName.substring(0, taskName.length() - 1);
                            String deadlineTask[] = taskName.split("/by ");
                            Task newDeadLineTask = new Deadline(deadlineTask[0], deadlineTask[1]);
                            tasks.add(newDeadLineTask);
                            System.out.print(line);
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + newDeadLineTask.toString());
                            System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                            System.out.println(line);
                            appendToFile(filePath, newDeadLineTask.toStringTaskstxt());
                            break;

                        case "event":
                            while (commandScanner.hasNext()) {
                                taskName += commandScanner.next() + " ";
                            }
                            taskName = taskName.substring(0, taskName.length() - 1);
                            String eventTask[] = taskName.split("/at ");
                            Task newEventTask = new Event(eventTask[0], eventTask[1]);
                            tasks.add(newEventTask);
                            System.out.print(line);
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + newEventTask.toString());
                            System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                            System.out.println(line);
                            appendToFile(filePath, newEventTask.toStringTaskstxt());
                            break;

                        default:
                            throw new DukeException("");

                    }
                }
            }
            catch (DukeException ex) {
                System.out.print(line);
                System.out.println(ex);
                System.out.println(line);
            }
            catch (IOException io) {
                System.out.println("file not found");
            }
        }
    }
}


