package bot.loadsave;


import bot.command.instruction.concrete.AliasInstruction;
import bot.util.ClosedScanner;
import bot.util.Pair;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that handles saving and loading
 * of aliased commands from the disk
 */
public class AliasLoader extends LoadAndSave<Pair<String, String>> {
    private static final String DEFAULT_ALIASES =
            "alias a\n"
            + "bye b\n"
            + "deadline d\n"
            + "delete de\n"
            + "done do\n"
            + "event e\n"
            + "find f\n"
            + "help h\n"
            + "list l\n"
            + "notdone n\n"
            + "search s\n"
            + "todo t\n";

    /**
     * Constructor for a new AliasLoader for the
     * given file directory and name
     *
     * @param fd Location of the file on the system
     * @param fn Name of the file in the directory
     *
     * @throws FileNotFoundException When file directory
     *     or file name is invalid
     */
    public AliasLoader(String fd, String fn) throws FileNotFoundException {
        super(fd, fn, AliasLoader.DEFAULT_ALIASES);
    }

    @Override
    public ArrayList<Pair<String, String>> loadFromDisk() {
        ArrayList<Pair<String, String>> storedAliases =
                new ArrayList<Pair<String, String>>();
        Scanner io = super.getToLoadFrom()
                .map(fr -> new Scanner(fr))
                .orElseGet(() -> ClosedScanner.getClosedScanner());

        while (io.hasNext()) {
            String originalName = io.next();
            String aliasedName;
            try {
                aliasedName = io.next();
            } catch (NoSuchElementException e) {
                // possible corrupted file
                break;
            }

            storedAliases.add(new Pair<String, String>(originalName, aliasedName));
        }
        return storedAliases;
    }

    @Override
    public void saveToDisk(Collection<Pair<String, String>> aliases) {
        StringBuilder toBeSaved = new StringBuilder();
        for (Pair<String, String> alias : aliases) {
            // use line breaks to separate each alias
            toBeSaved.append(alias.getFirst())
                    .append(" ")
                    .append(alias.getSecond())
                    .append("\n");
        }
        FileSaver.saveStringAsFile(super.getRelativeAddress(), toBeSaved);
    }
}
