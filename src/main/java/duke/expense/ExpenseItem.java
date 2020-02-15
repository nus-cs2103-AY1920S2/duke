package duke.expense;

import duke.util.DateUtil;

import java.time.LocalDate;

/**
 * Lets the user track their expenses. This is the base class for different categories available to keep track.
 */
public class ExpenseItem {
    protected double amount;
    protected Category category;
    protected LocalDate dateTime;
    protected String description;

    /**
     * Instantiates the expense item with default datetime.
     */
    public ExpenseItem(Category category, double amount, String description) {
        this.amount = amount;
        this.category = category;
        this.dateTime = DateUtil.formatDate(LocalDate.now().toString());
        this.description = description;
    }

    protected ExpenseItem(Category category, double amount, LocalDate dateTime, String description) {
        this.amount = amount;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
    }

    /**
     * Parses the expenseString loaded from storage.
     *
     * @param expenseString the task string
     * @return the expenseItem
     */
    public static ExpenseItem parse(String expenseString) {
        String[] parts = expenseString.split(" ");
        //checks that the file is populated with data of the right format

        Category category = Category.valueOf(parts[0].toUpperCase());
        double amount = Double.parseDouble(parts[1]);
        LocalDate dateTime = null;
        if (parts.length > 2) {
            dateTime = DateUtil.formatDate(parts[2]);
        }

        String description = "";
        if (parts.length > 3) {
            description = parts[3];
        }
        return new ExpenseItem(category, amount, dateTime, description);
    }

    /**
     * Prints out the string.
     *
     * @return the string
     */
    public String toString() {
        return category.toString() + " " + amount + " " + dateTime + " " + description;
    }

}
