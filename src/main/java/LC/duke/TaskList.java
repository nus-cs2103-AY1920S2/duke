package LC.duke;

import static java.lang.Integer.parseInt;
import java.text.ParseException;

public class TaskList {
    static Task[] totalTasks = new Task[100];
    static int totalTasksCount;

    public TaskList() {
        totalTasksCount = 0;
    }

    public TaskList(String[] totalTasks) throws ParseException {
        for(int i = 0; i < totalTasks.length; i++) {
            if (totalTasks[i].contains("[T]")) {
                this.toDo("todo" + totalTasks[i].substring(totalTasks[i].indexOf(" ")));
            } else if (totalTasks[i].contains("[D]")) {
                String temp = totalTasks[i].substring(totalTasks[i].indexOf("by:") + 4, totalTasks[i].length() - 1);
                String part1 = temp.substring(0, totalTasks[i].indexOf(" "));
                String part2 = temp.substring(4, totalTasks[i].indexOf(" "));
                String part3 = temp.substring(temp.length()-4, temp.length());

                switch(part1){
                    case("Dec"):
                        part1 = "12";
                        break;
                    case("Nov"):
                        part1 = "11";
                        break;
                    case("Oct"):
                        part1 = "10";
                        break;
                    case("Sep"):
                        part1 = "9";
                        break;
                    case("Aug"):
                        part1 = "8";
                        break;
                    case("Jul"):
                        part1 = "7";
                        break;
                    case("Jun"):
                        part1 = "6";
                        break;
                    case("May"):
                        part1 = "5";
                        break;
                    case("Apr"):
                        part1 = "4";
                        break;
                    case("Mar"):
                        part1 = "3";
                        break;
                    case("Feb"):
                        part1 = "2";
                        break;
                    default:
                        part1 = "1";
                }
                temp = part3 + "-" + part1 + "-" + part2;
                System.out.println(temp);
                System.out.println("deadline" + totalTasks[i].substring(totalTasks[i].indexOf(" "), totalTasks[i].indexOf("by:") - 1)
                        + "/by " + temp);
                //this.deadline("deadline" + totalTasks[i].substring(totalTasks[i].indexOf(" "), totalTasks[i].indexOf("by:") - 1)
                        //+ "/by " + temp);

            } else {
                String temp = totalTasks[i].substring(totalTasks[i].indexOf("at:") + 4, totalTasks[i].length() - 1);
                String part1 = temp.substring(0, totalTasks[i].indexOf(" "));
                String part2 = temp.substring(4, totalTasks[i].indexOf(" "));
                String part3 = temp.substring(temp.length()-4, temp.length());

                switch(part1){
                    case("Dec"):
                        part1 = "12";
                        break;
                    case("Nov"):
                        part1 = "11";
                        break;
                    case("Oct"):
                        part1 = "10";
                        break;
                    case("Sep"):
                        part1 = "9";
                        break;
                    case("Aug"):
                        part1 = "8";
                        break;
                    case("Jul"):
                        part1 = "7";
                        break;
                    case("Jun"):
                        part1 = "6";
                        break;
                    case("May"):
                        part1 = "5";
                        break;
                    case("Apr"):
                        part1 = "4";
                        break;
                    case("Mar"):
                        part1 = "3";
                        break;
                    case("Feb"):
                        part1 = "2";
                        break;
                    default:
                        part1 = "1";
                }
                temp = part3 + "-" + part1 + "-" + part2;
                System.out.println(temp);
                this.event("event" + totalTasks[i].substring(totalTasks[i].indexOf(" "), totalTasks[i].indexOf("at:") - 1)
                        + "/at " + temp);
            }
        }
    }

    public void delete(String userInput){
        String deleteSelect = userInput.substring(7);
        int deleteSelectNo = parseInt(deleteSelect) - 1;
        if (deleteSelectNo != totalTasksCount) {
            while (deleteSelectNo <= totalTasksCount) {
                totalTasks[deleteSelectNo] = totalTasks[deleteSelectNo + 1];
                deleteSelectNo++;
            }
        }
        totalTasksCount = totalTasksCount - 1;
        Task.taskNo = Task.taskNo - 1;
    }

    public void toDo(String userInput){
        String content = userInput.substring(5);
        Task t = new Todo(content);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
    }

    public void deadline(String userInput){
        userInput = userInput.substring(9);
        String contentTasks = userInput.substring(0, userInput.indexOf("/by") - 1);
        String taskDeadline = userInput.substring(userInput.indexOf("/by") + 4);
        Task t = new Deadline(contentTasks, taskDeadline);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
    }

    public void event(String userInput){
        userInput = userInput.substring(6);
        String contentTasks = userInput.substring(0, userInput.indexOf("/at") - 1);
        String taskTime = userInput.substring(userInput.indexOf("/at") + 4);
        Task t = new Event(contentTasks, taskTime);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
    }
}
