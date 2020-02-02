import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    public static void main(String[] args) throws DukeException, FileNotFoundException, IOException {
        System.out.println("Hello I'm Duke.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        //create list
        List<Task> list = new ArrayList<Task>();

        //get existing data
        File f = new File("../../../data/duke.txt");
        Scanner scanExisting = new Scanner(f);
        while (scanExisting.hasNext()) {
            String line = scanExisting.nextLine();
            String[] addLine = line.split("@", 4);
            if (addLine[0].equals("T")) {
                ToDos t = new ToDos(addLine[2]);
                if (addLine[1].equals("1")) {
                    t.setDone();
                }
                list.add(t);
            } else if (addLine[0].equals("D")) {
                Deadline d = new Deadline(addLine[2], addLine[3]);
                if (addLine[1].equals("1")) {
                    d.setDone();
                }
                list.add(d);
            } else if (addLine[0].equals("E")) {
                Events e = new Events(addLine[2], addLine[3]);
                if (addLine[1].equals("1")) {
                    e.setDone();
                }
                list.add(e);
            }
        }

        while (true) {
            String command = sc.nextLine();
            String arr[] = command.split(" ", 2);
            String firstWord = arr[0];

            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            Task t = list.get(i);
                            System.out.println((i+1) + "." + t.toString());
                        }
                    }
                } else if (firstWord.equals("done")){
                    if (arr.length > 1) { //check for errors
                        //which task done
                        String secNum = arr[1];
                        //set that task to done
                        Task t = list.get(Integer.parseInt(secNum) - 1);
                        t.setDone();
                        //update hard disk

                        //print output
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t.toString());

                        //write new file
                        FileReader fr = new FileReader("../../../data/duke.txt");
                        BufferedReader br = new BufferedReader(fr);
                        List<String> tempArr = new ArrayList<String>();
                        for (int i = 0; i < list.size(); i++) {
                            if (i == Integer.parseInt(secNum) - 1) {
                                String oldLine = br.readLine();
                                String updatedLine = oldLine.replace("0", "1");
                                tempArr.add(updatedLine);
                            } else {
                                tempArr.add(br.readLine());
                            }
                        }
                        Files.delete(Paths.get("../../../data/duke.txt"));
                        File newFile = new File("../../../data/duke.txt");
                        FileWriter fw = new FileWriter("../../../data/duke.txt");
                        for (int j = 0; j < tempArr.size(); j++) {
                            if (j == 0) {
                                fw.write(tempArr.get(j));
                            } else {
                                fw.write("\n");
                                fw.write(tempArr.get(j));
                            }
                        }
                        fw.close();
                    } else {
                        throw new DukeException("☹ OOPS!!! Please specify which task you've done.");
                    }
                } else if (firstWord.equals("delete")) {
                    if (arr.length > 1) { //check for errors
                        //which task to delete
                        String secNum = arr[1];
                        //delete task
                        Task t = list.get(Integer.parseInt(secNum) - 1);
                        list.remove(Integer.parseInt(secNum) - 1);
                        //print output
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                        //write new file
                        FileReader fr = new FileReader("../../../data/duke.txt");
                        BufferedReader br = new BufferedReader(fr);
                        List<String> tempArr = new ArrayList<String>();
                        for (int i = 0; i < list.size() + 1; i++) {
                            if (i == Integer.parseInt(secNum) - 1) {
                                continue;
                            } else {
                                tempArr.add(br.readLine());
                            }
                        }
                        Files.delete(Paths.get("../../../data/duke.txt"));
                        File newFile = new File("../../../data/duke.txt");
                        FileWriter fw = new FileWriter("../../../data/duke.txt");
                        for (int j = 0; j < tempArr.size(); j++) {
                            if (j == 0) {
                                fw.write(tempArr.get(j));
                            } else {
                                fw.write("\n");
                                fw.write(tempArr.get(j));
                            }
                        }
                        fw.close();
                    } else {
                        throw new DukeException("☹ OOPS!!! Please specify which task to delete.");
                    }
                } else if (firstWord.equals("todo")) {
                    if (arr.length > 1) { //check for errors
                        //add task to list
                        ToDos t = new ToDos(arr[1]);
                        list.add(t);
                        //print output
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                        //write new file
                        FileWriter fw = new FileWriter("../../../data/duke.txt", true);
                        fw.write("\n");
                        fw.write("T@" + t.getStatusNumber() + "@" + t.getDescription());
                        fw.close();
                        fw.close();
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (firstWord.equals("deadline")) {
                    if (arr.length > 1) { //check for errors
                        //separate task and deadline
                        String arr2[] = arr[1].split("/", 2);

                        if (arr2.length > 1) { //check that deadline is specified
                            String arr3[] = arr2[1].split(" ", 2);
                            //add task and deadline to list
                            Deadline d = new Deadline(arr2[0], arr3[1]);
                            list.add(d);
                            //print output
                            System.out.println("Got it. I've added this task:");
                            System.out.println(d.toString());
                            System.out.println("Now you have " + list.size() + " tasks in the list.");

                            //write new file
                            FileWriter fw = new FileWriter("../../../data/duke.txt", true);
                            fw.write("\n");
                            fw.write("D@" + d.getStatusNumber() + "@" + d.getDescription() + "@" + d.getDetails());
                            fw.close();
                        } else {
                            throw new DukeException("☹ OOPS!!! Please specify the deadline.");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (firstWord.equals("event")) {
                    if (arr.length > 1) { //check for errors
                        //separate task and time
                        String arr2[] = arr[1].split("/", 2);

                        if (arr2.length > 1) {
                            String arr3[] = arr2[1].split(" ", 2);
                            //add task and deadline to list
                            Events e = new Events(arr2[0], arr3[1]);
                            list.add(e);
                            //print output
                            System.out.println("Got it. I've added this task:");
                            System.out.println(e.toString());
                            System.out.println("Now you have " + list.size() + " tasks in the list");

                            //write new file
                            FileWriter fw = new FileWriter("../../../data/duke.txt", true);
                            fw.write("\n");
                            fw.write("E@" + e.getStatusNumber() + "@" + e.getDescription() + "@" + e.getDetails());
                            fw.close();
                        } else {
                            throw new DukeException("☹ OOPS!!! Please specify event time.");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            //catch exceptions
            catch (DukeException e){
                System.out.println(e.getMessage());
            }


        }
    }
}
