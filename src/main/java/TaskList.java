import java.util.ArrayList;
import java.util.NoSuchElementException;

// Has operations to add or remove tasks in the list
class TaskList{

    private ArrayList<Task> list = new ArrayList<>();

    // Getting the list of the task.
     ArrayList<Task> getList() {
        return list;
    }

    // Add the task to the list
     void add_to_list (Task task) {
        list.add(task);
    }

    // Print out the size of the list
     int size_of_list() {
        return list.size();
    }

    // Delete the task from the list
     void remove_from_list (Task task) throws DukeException{
        try {
            list.remove(task);
        } catch (NoSuchElementException e) {
            throw new DukeException("This task does not exist in the list :( ");
        }
    }

    // To print out every element in the list
     void print_elements() {
        for (int i = 0; i < list.size(); i++) {
            String space = "        ";
            System.out.println( space + (i + 1) + "." + list.get(i));
        }
    }
}
