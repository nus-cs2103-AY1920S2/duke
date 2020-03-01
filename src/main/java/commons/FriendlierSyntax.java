package commons;

import java.util.HashMap;

public class FriendlierSyntax {
    public HashMap<String, String> friendlierSyntax = new HashMap<>();

    /**
     * Instantiates the list of friendlier syntax which includes shortcuts for each command.
     */
    public FriendlierSyntax(HashMap<String, String> friendlierSyntax) {
        if (friendlierSyntax == null) {
            System.out.println("haiz");
            friendlierSyntax.put("a", "alias");
            friendlierSyntax.put("alias", "alias");
            friendlierSyntax.put("b", "bye");
            friendlierSyntax.put("bye", "bye");
            friendlierSyntax.put("l", "list");
            friendlierSyntax.put("list", "list");
            friendlierSyntax.put("dlt", "delete");
            friendlierSyntax.put("delete", "delete");
            friendlierSyntax.put("dn", "done");
            friendlierSyntax.put("done", "done");
            friendlierSyntax.put("c", "clear");
            friendlierSyntax.put("clear", "clear");
            friendlierSyntax.put("dn", "done");
            friendlierSyntax.put("f", "find");
            friendlierSyntax.put("find", "find");
            friendlierSyntax.put("t", "todo");
            friendlierSyntax.put("todo", "todo");
            friendlierSyntax.put("ddln", "deadline");
            friendlierSyntax.put("deadline", "deadline");
            friendlierSyntax.put("e", "event");
            friendlierSyntax.put("event", "event");
            friendlierSyntax.put("hello", "hello");
            friendlierSyntax.put("tag", "tag");
        } else {
            this.friendlierSyntax = friendlierSyntax;
        }
    }

    public void setFriendlierSyntax(HashMap<String, String> friendlierSyntax) {
        this.friendlierSyntax = friendlierSyntax;
    }

    /**
     * Checks the hashmap for the command that the alias corresponds to.
     *
     * @param alias alias input by the user.
     * @return the command which the alias corresponds to.
     */
    public String findCommand(String alias) {
        if (friendlierSyntax.get(alias) != null) {
            return friendlierSyntax.get(alias);
        } else {
            return "error";
        }
    }

    /**
     * Adds alias and corresponding command into mappings.
     *
     * @param alias   alias for command.
     * @param command command selected.
     */
    public void addAlias(String alias, String command) {
        friendlierSyntax.put(alias, command);
    }

    /**
     * Returns the list of friendlier syntax mappings.
     *
     * @return
     */
    public HashMap<String, String> getFriendlierSyntax() {
        return this.friendlierSyntax;
    }
}
