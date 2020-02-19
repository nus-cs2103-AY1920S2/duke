package command;

import java.util.HashMap;

public class FriendlierSyntax {
    public HashMap<String, String> friendlierSyntax = new HashMap<>();

    public FriendlierSyntax() {
        friendlierSyntax.put("a", "alias");
        friendlierSyntax.put("alias", "alias");
        friendlierSyntax.put("b", "bye");
        friendlierSyntax.put("bye", "bye");
        friendlierSyntax.put("l", "list");
        friendlierSyntax.put("list", "list");
        friendlierSyntax.put("dlt", "delete");
        friendlierSyntax.put("delete", "delete");
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
        friendlierSyntax.put("tag", "tag");
    }

    public void setFriendlierSyntax(HashMap<String, String> friendlierSyntax) {
        this.friendlierSyntax = friendlierSyntax;
    }

    public String findCommand(String alias) {
        if (friendlierSyntax.get(alias) != null) {
            return friendlierSyntax.get(alias);
        } else {
            return "error";
        }
    }

    public void addAlias(String alias, String command) {
        friendlierSyntax.put(alias, command);
    }

    public HashMap<String, String> getFriendlierSyntax() {
        return this.friendlierSyntax;
    }
}
