package main.java;

import java.util.Scanner;

public class Ui {
    String line;
    Scanner sc = new Scanner(System.in);

    public Ui(String line) {
        this.line = line;
    }

    public Ui() {}

    public String[] parseInput() {
        return line.split(" ", 2);
    }

    public String getCommand() {
        return (this.parseInput())[0];
    }

    public String getDescription() {
        return (this.parseInput())[1];
    }

    public String getNumber() {
        return (this.parseInput())[1];
    }

<<<<<<< HEAD
    public String showList() {
        return ("Here are the tasks in your list: ");
=======
    public  String showList() {
        return "Here are the tasks in your list: ";
>>>>>>> 23b7f1d0e01940f845a562962edf14088c038c89
    }

    public String errmessage() {
        return "Invalid Input, Please check instructions";
    }

    public String doneTask(Task task) {
<<<<<<< HEAD
        return ("-------------------------------------------------------------\nNice I have marked this task as done\n" +
        " " + task.getDescription() + "\n-------------------------------------------------------------");
    }

    public String addedTask(Task todo) {
        return ("-------------------------------------------------------------\n" + todo.toString() +
        "\n-------------------------------------------------------------\n");
    }

    public String deleteTask(String task) {
        return ("Deleted task: " + task);
=======
        return("Nice I have marked this task as done\n" +
        " " + task.getDescription() + "\n");
    }

    public String addedTask(Task todo) {
        return (todo.toString() + "\n");
    }

    public String deleteTask(String task) {
        return("Deleted task: " + task);
>>>>>>> 23b7f1d0e01940f845a562962edf14088c038c89
    }

}