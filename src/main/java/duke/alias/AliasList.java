package duke.alias;

import duke.DukeException;
import duke.storage.Storage;

import java.util.HashMap;

public class AliasList {
    private HashMap<String, String> items;
    private Storage storage;

    /**
     * Instantiates a new alias list.
     */
    public AliasList(Storage storage) {
        items = new HashMap<>();
        this.storage = storage;

    }

    /**
     * Instantiates an alias list with existing items.
     */
    protected AliasList(HashMap<String, String> items, Storage storage) {
        this.items = items;
        this.storage = storage;

    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public HashMap<String, String> getAll() {
        return items;
    }

    /**
     * Gets the original command with the user defined alias
     * e.g. get(t) with retrieve the record t that is mapped to to-do
     *
     * @param alias the name for the alias
     * @return the alias
     */
    public String get(String alias) {
        return items.get(alias);
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public int getSize() {
        return items.size();
    }


    /**
     * Adds the alias.
     *
     * @param s       the alias string
     * @param command the current operation
     */
    public void addAlias(String s, String command) {
        items.put(s, command);
    }

    /**
     * Return the aliases that fall within the date range provided.
     *
     * @param current  current operation
     * @param newAlias alias name for the current operation
     */
    public AliasList addAlias(String current, String newAlias, Storage storage) {
        assert (storage != null);

        if (current != null && !current.isEmpty()) {
            items.put(current, newAlias);
        } else {
            throw new DukeException("☹ OOPS!!! Alias not specified. ");
        }


        StringBuilder sb = new StringBuilder();
        items.forEach((e, c) -> sb.append(e).append(" ").append(c).append("\n"));

        assert (sb.toString().split(" ").length > 1);
        storage.writeToFile(sb.toString());

        return new AliasList(items, storage);
    }


    /**
     * Delete alias.
     *
     * @param alias   the alias name
     * @param storage storage
     */
    public String deleteAlias(String alias, Storage storage) {
        final String cur = items.get(alias);
        items.remove(alias);
        StringBuilder sb = new StringBuilder();

        if (items.size() > 0) {
            items.forEach((t, s) -> sb.append(t).append(" ").append(s).append("\n"));

        }
        storage.writeToFile(sb.toString());
        return cur;
    }

}
