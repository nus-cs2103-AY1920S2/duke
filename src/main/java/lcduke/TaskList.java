package lcduke;

import java.text.ParseException;
import static java.lang.Integer.parseInt;

/** Ths creates a TaskList object.
 */

public class TaskList {
    static Task[] totalTasks = new Task[100];
    static int totalTasksCount;

    /** This is the constructor to create the TaskList Object.
     */
    public TaskList() {
        totalTasksCount = 0;
    }

    public TaskList(String[] totalTasks, int storageNo) throws ParseException {
        for(int i = 0; i < storageNo; i++) {
            if (totalTasks[i].contains("[T]")) {
                this.toDo("todo" + totalTasks[i].substring(totalTasks[i].indexOf(" ")));
            } else if (totalTasks[i].contains("[D]")) {
                String temp = totalTasks[i].substring(totalTasks[i].indexOf("by:") + 4, totalTasks[i].length() - 1);
                String part1 = temp.substring(0, 4);
                String part2 = temp.substring(4);
                part2 = part2.substring(0, part2.indexOf(" "));
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
                        part1 = "09";
                        break;
                    case("Aug"):
                        part1 = "08";
                        break;
                    case("Jul"):
                        part1 = "07";
                        break;
                    case("Jun"):
                        part1 = "06";
                        break;
                    case("May"):
                        part1 = "05";
                        break;
                    case("Apr"):
                        part1 = "04";
                        break;
                    case("Mar"):
                        part1 = "03";
                        break;
                    case("Feb"):
                        part1 = "02";
                        break;
                    default:
                        part1 = "01";
                }
                if (part2.length() < 2){
                    part2 = "0" + part2;
                }
                temp = part3 + "-" + part1 + "-" + part2;
                this.deadline("deadline" + totalTasks[i].substring(totalTasks[i].indexOf(" "), totalTasks[i].indexOf("by:") - 1)
                        + "/by " + temp);

            } else {
                String temp = totalTasks[i].substring(totalTasks[i].indexOf("at:") + 4, totalTasks[i].length() - 1);
                String part1 = temp.substring(0, 4);
                String part2 = temp.substring(4);
                part2 = part2.substring(0, part2.indexOf(" "));
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
                        part1 = "09";
                        break;
                    case("Aug"):
                        part1 = "08";
                        break;
                    case("Jul"):
                        part1 = "07";
                        break;
                    case("Jun"):
                        part1 = "06";
                        break;
                    case("May"):
                        part1 = "05";
                        break;
                    case("Apr"):
                        part1 = "04";
                        break;
                    case("Mar"):
                        part1 = "03";
                        break;
                    case("Feb"):
                        part1 = "02";
                        break;
                    default:
                        part1 = "01";
                }
                if (part2.length() < 2){
                    part2 = "0" + part2;
                }
                temp = part3 + "-" + part1 + "-" + part2;
                System.out.println(temp);
                this.event("event" + totalTasks[i].substring(totalTasks[i].indexOf(" "), totalTasks[i].indexOf("at:") - 1)
                        + "/at " + temp);
            }
            if(totalTasks[i].contains("\u2713")){
                this.totalTasks[i].markAsDone();
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

    public String toDo(String userInput){
        String content = userInput.substring(5);
        Task t = new Todo(content);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
        return t.printInit();
    }

    public String deadline(String userInput){
        userInput = userInput.substring(9);
        String contentTasks = userInput.substring(0, userInput.indexOf("/by") - 1);
        String taskDeadline = userInput.substring(userInput.indexOf("/by") + 4);
        Task t = new Deadline(contentTasks, taskDeadline);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
        return t.printInit();
    }

    public String event(String userInput){
        userInput = userInput.substring(6);
        String contentTasks = userInput.substring(0, userInput.indexOf("/at") - 1);
        String taskTime = userInput.substring(userInput.indexOf("/at") + 4);
        Task t = new Event(contentTasks, taskTime);
        totalTasks[totalTasksCount] = t;
        totalTasksCount++;
        return t.printInit();
    }
    public String find(String userInput){
        String response;
        String keyword = userInput.substring(5);
        response = "    ____________________________________________________________"
                + "     Here are the matching tasks in your list:";
        int i = 1;
        for(int j = 0; j < totalTasksCount; j++) {
            if(totalTasks[j].getDescription().contains(keyword)){
                response = response + "     " + i + "." + totalTasks[j].toString();
                i++;
            }
        }
        response = response + "    ____________________________________________________________";
        return response;
    }
}
