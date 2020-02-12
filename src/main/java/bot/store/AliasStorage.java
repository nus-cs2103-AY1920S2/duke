package bot.store;

import bot.command.CommandParser;
import bot.command.exception.InstructionAlreadyExistsException;
import bot.command.instruction.concrete.AliasInstruction;

import bot.loadsave.LoadAndSave;

import bot.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A class that stores aliased commands while
 * the bot is running. Aliased commands are
 * represented by a Pair of Strings, where
 * the first String is the original name and
 * the second String is the aliased name
 */
public class AliasStorage implements Storage<Pair<String, String>> {
    private final ArrayList<Pair<String, String>> storedAliases =
            new ArrayList<Pair<String, String>>();
    // maps an alias to the original name
    // (second in pair to first in pair)
    private final HashMap<String, String> aliasMap = new HashMap<String, String>();

    @Override
    public void delete(int i) {
        if (i > 0 && i <= this.getSize()) {
            // index not out of bounds
            this.storedAliases.remove(i - 1);
        }
    }

    @Override
    public Pair<String, String> get(int index) {
        if (index <= 0 || index > this.getSize()) {
            // an empty alias
            return new Pair<String, String>("", "");
        } else {
            return this.storedAliases.get(index - 1);
        }
    }

    @Override
    public int getSize() {
        return this.storedAliases.size();
    }

    @Override
    public void importFromCollection(Collection<Pair<String, String>> aliases) {
        for (Pair<String, String> alias : aliases) {
            String aliasedName = alias.getSecond();
            if (CommandParser.INSTR_MAP.containsKey(aliasedName)
                    || this.aliasMap.containsKey(aliasedName)) {

                // the alias is already in use
                System.err.println("import error: " + aliasedName
                        + "is already a name");
            } else {
                this.storedAliases.add(alias);
                String originalName = alias.getFirst();
                this.aliasMap.put(aliasedName, originalName);
                AliasInstruction.addAliasedName(originalName, aliasedName);
            }
        }
    }

    @Override
    public String retrieve(int i) {
        if (i <= 0 || i > this.getSize()) {
            // index out of bounds
            return "";
        } else {
            return i + ". " + this.get(i);
        }
    }

    @Override
    public void saveToDisk(LoadAndSave<Pair<String, String>> aliasLoader) {
        aliasLoader.saveToDisk(this.storedAliases);
    }

    @Override
    public void store(Pair<String, String> aliasPair)
            throws InstructionAlreadyExistsException {

        String aliasedName = aliasPair.getSecond();
        if (CommandParser.INSTR_MAP.containsKey(aliasedName)
                || this.aliasMap.containsKey(aliasedName)) {

            // the alias is already in use
            throw new InstructionAlreadyExistsException(aliasedName);
        } else {
            this.storedAliases.add(aliasPair);
            this.aliasMap.put(aliasedName, aliasPair.getFirst());
        }
    }
}
