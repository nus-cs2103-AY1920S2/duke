package duke.command;

import java.util.Comparator;

public class TaskDatetimeComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        System.out.println(o1.datetime);
        if (o1.datetime.equals("") && o2.datetime.equals("")){
            return o1.description.compareTo(o2.description);
        } else if (o1.datetime.equals("")){
            return -1;
        } else if (o2.datetime.equals("")){
            return 1;
        } else if (o1.datetime.contains(" to ") && o2.datetime.contains(" to ")){
            return o1.datetime.compareTo(o2.datetime);
        } else if (o1.datetime.contains(" to ")){
            return 1;
        } else if (o2.datetime.contains(" to ")){
            return -1;
        } else {
            return o1.datetime.compareTo(o2.datetime);
        }
    }

}
