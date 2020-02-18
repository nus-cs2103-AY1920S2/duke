package resources;

import database.MyList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Tracks the task's activity
 */
public class TaskTracker {

    private static ArrayList<Task> listOfDoneTasks = new ArrayList<>();
    private static int currentWeek = 0;
    private static int deleted = 0;

    /**
     * Adds a task that is finish into the list
     * Updates the current week
     *
     * @param t the task to add
     */
    public static void finishTask(Task t) {
        listOfDoneTasks.add(t);
    }

    /**
     * Returns completed things this week
     *
     * @return String of things done this week
     */
    public static String doneThisWeek() {
        //update current week
        currentWeek = getWeek(LocalDate.now());
        ArrayList<Task> listOfDoneTasksWeek = updateDone();
        String ans = "";
        if (listOfDoneTasksWeek.size() != 0) {
            ans = "" + listToString(listOfDoneTasksWeek);
        }
        return ans + "You have completed "
                + listOfDoneTasksWeek.size() + " things this week\n";
    }

    /**
     * Returns number of items done this week
     *
     * @return  int the task to add
     */
    public static int done() {
        return updateDone().size();
    }

    /**
     * Returns the list of Events in String
     *
     * @return String of Events
     */
    public static String getEventsString() {
        ArrayList<Task> current = MyList.getList();
        ArrayList<Task> events = new ArrayList<>();
        for (Task t : current) {
            if (t instanceof Event) {
                events.add(t);
            }
        }
        String ans = "";
        if (events.size()!= 0) {
            ans = listToString(events);
        }
        return ans + "You have "
                + events.size() + " events in your list\n";
    }
    /**
     * Returns the list of todos in String
     *
     * @return String of todos
     */
    public static String getToDoString() {
        ArrayList<Task> current = MyList.getList();
        ArrayList<Task> ToDo = new ArrayList<>();
        for (Task t : current) {
            if (t instanceof ToDos) {
                ToDo.add(t);
            }
        }
        String ans = "";
        if (ToDo.size()!= 0) {
            ans = listToString(ToDo);
        }

        return ans + "You have "
                + ToDo.size() + " todos in your list\n";
    }

    /**
     * Returns the list of Deadlines in String
     *
     * @return String of Deadlines
     */
    public static String getDeadlineString() {
        ArrayList<Task> current = MyList.getList();
        ArrayList<Task> deadlines = new ArrayList<>();
        for (Task t : current) {
            if (t instanceof Deadline) {
                deadlines.add(t);
            }
        }
        String ans = "";
        if (deadlines.size()!= 0) {
            ans = listToString(deadlines);
        }

        return ans + "You have "
                + deadlines.size() + " deadlines this week\n";
    }

    /**
     * Returns the String of things done today
     *
     * @return String of things completed today
     */
    public static String getDoneToday() {
        int todayDate = LocalDate.now().getDayOfMonth();
        int todayMonth = LocalDate.now().getMonthValue();
        ArrayList<Task> doneToday = new ArrayList<>();
        for (Task t : listOfDoneTasks) {
            int date = t.getDoneDate().getDayOfMonth();
            int month = t.getDoneDate().getMonthValue();

            if (date == todayDate && month == todayMonth) {
                doneToday.add(t);
            }
        }
        String ans = "";
        if (doneToday.size()!= 0) {
            ans = listToString(doneToday);
        }

        return ans + "You have completed "
                + doneToday.size() + " tasks today\n";
    }

    /**
     * Increments the number of things deleted in session
     *
     */
    public static void markDeleted() {
        deleted = deleted + 1;
    }

    public static String showExpired() {
        ArrayList<Task> current = MyList.getList();
        ArrayList<Task> expired = new ArrayList<>();
        for (Task t : current) {
            if (t.isDone) {
                continue;
            }
            if (t instanceof ToDos) {
                continue;
            }
            if (isExpired(t)) {
                expired.add(t);
            }
        }

        String ans = "";
        if (expired.size()!= 0) {
            ans = listToString(expired);
        }

        return ans + "You have "
                + expired.size() + " expired tasks\n";
    }

    /**
     * Returns the num of deleted items
     *
     * @return String of things deleted
     */
    public static String showDeleted() {
        return "You have deleted " + deleted + " task in this session.\n";
    }

    private static boolean isExpired(Task t) {
        LocalDate today = LocalDate.now();
        LocalDate checkDate = LocalDate.now();
        if (t instanceof Event) {
            checkDate = ((Event) t).getEndDate().toLocalDate();
        }
        if (t instanceof Deadline) {
            checkDate = ((Deadline) t).getDeadline().toLocalDate();
        }

        return checkDate.isBefore(today);
    }

    private static String listToString(ArrayList<Task> list) {
        String ans = "";
        int count =  1;
        for (Task t : list) {
            ans =  ans + count + ". " + t + "\n";
            count++;
        }
        return ans;
    }

    private static ArrayList<Task> updateDone() {
        ArrayList<Task> newDoneTasks = new ArrayList<>();
        for (Task task: listOfDoneTasks) {
            if (isSameWeekAsToday(task.getDoneDate())) {
                newDoneTasks.add(task);
            }
        }
        return newDoneTasks;
    }

    private static boolean isSameWeekAsToday(LocalDate d) {
        int todayWeek = getWeek(d);
        return todayWeek == currentWeek;
    }

    //part of code snippet found at https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8/26013129
    private static int getWeek(LocalDate d) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int todayWeekNum = d.get(woy);
        return todayWeekNum;
    }
}
