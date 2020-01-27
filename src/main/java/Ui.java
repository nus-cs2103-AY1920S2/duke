package main.java;

import java.util.Scanner;

public class Ui {
    String line;
    Scanner sc = new Scanner(System.in);

    public Ui(String line) {
        this.line = line;
    }

    public String[] parseInput() {
        return line.split(" ", 2);
    }

    public String getCommand() {
        return (this.parseInput())[0];
    }

    public String getDescription() {
        return (this.parseInput())[1];
    }

    public void showList() {
        System.out.println("Here are the tasks in your list: ");
    }

    public String errmessage() {
        return "Invalid Input, Please check instructions";
    }

    public void doneTask(Task task) {
        System.out.println("-------------------------------------------------------------\nNice I have marked this task as done\n" +
        task.getStatusIcon() + " " + task.getDescription() + "\n-------------------------------------------------------------");
    }

    public void addedTask(Task todo) {
        System.out.println("-------------------------------------------------------------\n" + todo.toString() +
        "\n-------------------------------------------------------------\n");
    }

    public void deleteTask(String task) {
        System.out.println("Deleted task: " + task);
    }

}