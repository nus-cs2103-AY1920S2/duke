/**
 * readCommand()
 * readTask()
 * readTiming()
 * readNum()
 */

public class Parser{
    public static Command readCommand(String input) throws Exception{
        return Command.convert(input.split(" ")[0]);
    }

    public static String readTask(String input) throws Exception {
        String[] arr = input.split(" ");
        String task = "";
        int i = 1;
        while (i < arr.length && !arr[i].startsWith("/")) {
            task += arr[i++] + " ";
        }
        if (task.equals("")) throw new Exception();
        else return task;
    }

    public static String readTiming(String input) throws Exception{
        String[] arr = input.split(" ");
        String timing = "";
        int i = 1;
        while (i < arr.length) {
            if (arr[i].startsWith("/")) {
                timing += arr[i++].split("/")[1] + ":";
                while (i < arr.length) {
                    timing += " " + arr[i];
                    i++;
                }
                break;
            }
            i++;
        }
        return timing;
    }

    public static int readNum(String input) {
        return Integer.valueOf(input.split(" ")[1]);
    }

}