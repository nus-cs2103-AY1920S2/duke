package duke.expense;

import duke.DukeException;
import duke.storage.Storage;
import duke.util.EnumUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseList {
    private List<ExpenseItem> items;
    private Storage storage;

    /**
     * Instantiates a new expense list.
     */
    public ExpenseList(Storage storage) {
        items = new ArrayList<>();
        this.storage = storage;

    }

    /**
     * Instantiates an expense list with existing items.
     */
    protected ExpenseList(List<ExpenseItem> items, Storage storage) {
        this.items = items;
        this.storage = storage;

    }

    /**
     * Gets the expenses.
     *
     * @return the expenses
     */
    public List<ExpenseItem> getExpenses() {
        return items;
    }

    /**
     * Gets the expense with its index.
     *
     * @param id the task id
     * @return the task
     */
    public ExpenseItem get(int id) {
        return items.get(id);
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
     * Return the expenses that fall within the date range provided.
     *
     * @return the size
     */
    public ExpenseList filterExpensesByDate(LocalDate startDate, LocalDate endDate) {
        List<ExpenseItem> myItems = new ArrayList<>();

        if (startDate == null && endDate == null) {
            return new ExpenseList(items, storage);
        } else {
            for (ExpenseItem item : items) {
                if (item.dateTime.compareTo(startDate) >= 0 && ((endDate == null)
                        || item.dateTime.compareTo(endDate) < 0)) {
                    myItems.add(item);
                }
            }
            return new ExpenseList(myItems, storage);
        }

    }


    /**
     * Adds the expense.
     *
     * @param e the expense
     */
    public void addExpense(ExpenseItem e) {
        items.add(e);
    }

    /**
     * Return the expenses that fall within the date range provided.
     *
     * @param current adds the current item to the list
     */
    public ExpenseItem addExpense(String[] current, Storage storage) {
        assert (storage != null);
        String[] expense = Arrays.stream(current).skip(1).toArray(String[]::new);
        if (expense.length == 0) {
            throw new DukeException("☹ OOPS!!! Please specify what you have spent on. ");
        }

        String category = expense[0];
        if (!EnumUtil.isValidCategory(category)) {
            throw new DukeException("☹ OOPS!!! There is no such category. ");
        }

        Category cat = Category.valueOf(category.toUpperCase());

        double amount = 0;
        if (expense.length > 1 && expense[1] != null) {
            amount = Double.parseDouble(expense[1]);
        } else {
            throw new DukeException("☹ OOPS!!! No amount specified. ");
        }

        String desc = "";
        if (expense.length > 2 && expense[2] != null) {
            desc = expense[2];
        }

        ExpenseItem expenseItem = new ExpenseItem(cat, amount, desc);
        items.add(expenseItem);

        StringBuilder sb = new StringBuilder();
        items.forEach(e -> sb.append(e).append("\n"));

        assert (sb.toString().split(" ").length > 1);
        storage.writeToFile(sb.toString());

        return expenseItem;
    }


    /**
     * Delete expense.
     *
     * @param id      the expense index
     * @param storage storage
     */
    public ExpenseItem deleteExpense(int id, Storage storage) {
        ExpenseItem cur = items.get(id - 1);
        items.remove(cur);
        StringBuilder sb = new StringBuilder();

        if (items.size() > 0) {
            items.forEach(t -> sb.append(t).append("\n"));

        }
        storage.writeToFile(sb.toString());
        return cur;
    }

}
