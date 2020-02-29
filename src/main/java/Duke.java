import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import tasks.TaskManager;
import ui.HelloDuke;


import java.util.Scanner;

public class Duke{
    TaskManager taskManager = new TaskManager();

    public void handle_input(Stage primaryStage){
        Scanner sc = new Scanner((System.in));
        boolean exit_flag = true;

        while (exit_flag){
            String str_input = sc.nextLine();
            taskManager.Input_Solver(str_input);
            }
        }

    }


