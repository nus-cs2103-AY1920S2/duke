package tasks;

import javafx.util.Pair;
import ui.HelloDuke;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TaskManager {
    private List<TaskType> Task_List = new ArrayList<>();

    private String[] parser(String input_str){
        String[] command_content = input_str.split("/");

        return command_content;
    }

    public String Print_Task_List(){
        if (Task_List.size() == 0){
            return("There is no task now, please add some tasks.");
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Task_List.size(); i++) {
                sb.append(Task_List.get(i).print_task());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public String Print_Bye_Info(){
        return "Goodbye and see you again!";
    }

    public String Add_DDL(String event, String Date){
        for (int i = 0; i < Task_List.size(); i++) {
            if (Task_List.get(i).TaskName.equals(event)) {
                return "Task already added";
            }
        }
        TaskType task = new TaskType(false, event, "D", Date);
        this.Task_List.add(task);
        return "Task added successfully";
    }

    public String Add_Event(String event, String Date){
        if (Task_List.size() > 0) {
            for (int i = 0; i < Task_List.size(); i++) {
                if (Task_List.get(i).TaskName.equals(event)) {
                    return "Task already added";
                }
            }
        }
        TaskType task = new TaskType(false, event, "E", Date);
        this.Task_List.add(task);
        return "Task added successfully";
    }

    public String Add_ToDo(String event){
        if (Task_List.size() > 0) {
            for (int i = 0; i < Task_List.size(); i++) {
                if (Task_List.get(i).TaskName.equals(event)) {
                    return "Task already added";
                }
            }
        }
        TaskType task = new TaskType(false, event, "T", "None");
        this.Task_List.add(task);
        return "Task added successfully";
    }

    public void Mark_task(String event){
        for (int i = 0; i < Task_List.size(); i++){
            if (Task_List.get(i).TaskName.equals(event)){
                Task_List.get(i).is_finished = true;
                break;
            }
        }
    }

    public void Unmark_task(String event){
        for (int i = 0; i < Task_List.size(); i++){
            if (Task_List.get(i).TaskName.equals(event)){
                Task_List.get(i).is_finished = true;
                break;
            }
        }
    }


    public void Delete_Task(String event){
        for (int i = 0; i < Task_List.size(); i++){
            if (Task_List.get(i).TaskName.equals(event)){
                Task_List.remove(i);
                break;
            }
        }
    }


    public String Search_By_Task(String event){
        int i = 0;
        for (i = 0; i < Task_List.size(); i++){
            if (Task_List.get(i).TaskName.equals(event)){
                return(Task_List.get(i).print_task());
            }
        }
        return("This task cannot be found!");
    }


    public String Save_task() {
        try {
            Path currentPath = Paths.get(System.getProperty("user.dir"));
            Path filePath = Paths.get(currentPath.toString(), "Tasks.txt");
            String path = filePath.toString();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(Print_Task_List());
            bufferWritter.close();
            return "Saved successfuly to " + path;
        } catch (IOException e) {
            e.printStackTrace();
            return "Save failed";
        }
    }


    public String Input_Solver(String input_str){
        String[] input_list = parser(input_str);

        //for (int i = 0; i < input_list.length; i++){
          //  System.out.println(input_list[i]);
        //}

        if (input_list[0].equals("list")){
            assert input_list.length == 1;
            return Print_Task_List();
        }

        else if (input_list[0].equals("bye")){
            assert input_list.length == 1;
            return Print_Bye_Info();
        }

        else if (input_list[0].equals("deadline")){
            assert input_list.length == 3;
            return Add_DDL(input_list[1], input_list[2]);
        }

        else if (input_list[0].equals("event")){
            assert input_list.length == 3;
            return Add_Event(input_list[1], input_list[2]);
        }

        else if (input_list[0].equals("todo")){
            assert input_list.length == 2;
            return Add_ToDo(input_list[1]);
        }

        else if (input_list[0].equals("done")){
            assert input_list.length == 2;
            Mark_task(input_list[1]);
            return "Finished mark";
        }

        else if (input_list[0].equals("undone")){
            assert input_list.length == 2;
            Unmark_task(input_list[1]);
            return "Finished unmark";
        }

        else if (input_list[0].equals("search")){
            assert input_list.length == 2;
            return Search_By_Task(input_list[1]);
        }

        else if (input_list[0].equals("delete")){
            assert input_list.length == 2;
            Delete_Task(input_list[1]);
            return "Already deleted";
        }

        else if (input_list[0].equals("save")){
            assert input_list.length == 1;
            return(Save_task());
        }

        return("This command " +
                input_str + " is not valid, please check again.");

    }
}
