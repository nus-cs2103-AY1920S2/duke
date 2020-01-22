import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String welcome = "OwO Hello! I am your neckbeard chatbot! \n" + "What can I do for you senpai? *sweats profusely*";
        System.out.println(welcome);

        ArrayList<Task> taskList = new ArrayList<>();

        while(input.hasNextLine()) {
            String taskDescription = input.nextLine();
            String[] taskDesc = taskDescription.split(" ");

            if (taskDescription.equals("bye")) {

                System.out.println("UwU gone so fast? You're a fat bitch anyway.");
                break;

            } else if (taskDescription.equals("list")) {

                System.out.println("UwU you got some nice tasks:");

                for (int i = 0; i < taskList.size(); i++) {
                    int indexNumber = i + 1;

                    if (taskList.get(i).getStatus() == 0) {
                        System.out.println(indexNumber + ".[✗] " + taskList.get(i).getDescription());
                    } else {
                        System.out.println(indexNumber + ".[✓] " + taskList.get(i).getDescription());
                    }

                }

            } else if (taskDesc[0].equals("done")) {

                String indexString = taskDesc[1];
                int index = Integer.parseInt(indexString);
                Task oldTask = taskList.get(index - 1);
                Task newTask = new Task(oldTask.getDescription());
                newTask.setStatusDone();
                taskList.set(index - 1, newTask);

                System.out.println("I... I've marked this as done... notice me pls: \n" + "[✓] " + newTask.getDescription());

            } else if (taskDesc[0].equals("todo")) {

                String taskDescString = "";
                for (int i = 1; i < taskDesc.length; i++) {
                    taskDescString += taskDesc[i];
                }
                Task newTask = new Task(taskDescString);
                taskList.add(newTask);
                System.out.println("Senpai I have added this task: \n" + "[T][✗] " + newTask.getDescription() + "\n"
                + "Now you have " + taskList.size() + " number of tasks in the list.");

            } else if (taskDesc[0].equals("deadline")) {

                String taskDescString = "";
                for (int i = 1; i < taskDesc.length; i++) {
                    taskDescString += taskDesc[i];
                }
                Task newTask = new Task(taskDescString);
                taskList.add(newTask);
                System.out.println("Senpai I have added this task: \n" + "[T][✗] " + newTask.getDescription() + "\n"
                        + "Now you have " + taskList.size() + " number of tasks in the list.");

            }

        }


    }
}
