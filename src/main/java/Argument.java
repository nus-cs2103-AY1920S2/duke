import java.util.HashMap;
import java.util.Optional;

/*
 * Argument
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 23 Jan 2020
 *
 */

/**
 * <p>Argument class is used for storing the commands
 * entered by the client. The Argument stores the keyword,
 * and the details of the command, such as the description,
 * and the date of the task, if applicable.</p>
 * @author Mario Lorenzo
 */

public class Argument {
    private Keyword keyword;
    private String details;

    /**
     * Constructs the Argument instance that takes in a Command enum
     * and the details of the command entered by the client. The method
     * is private, which is intended to be called by the factory method.
     * @param keyword The keyword enum, representing the keyword of the command.
     * @param details The details, which are the second word, onwards of the command entered.
     */

    public Argument(Keyword keyword, String details) {
        this.keyword = keyword;
        this.details = details;
    }

    /**
     * getCommand method is used for other classes to retrieve
     * the Command enum of the Argument instance.
     * @return The corresponding command enum of an Argument.
     */

    public Keyword getKeyword() {
        return this.keyword;
    }


}
