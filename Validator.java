/**
 * Validator.java  (Part B component 1: Input / validation)
 * Cleans and checks everything the user types. Every method either returns
 * a clean value or throws IllegalArgumentException with a message the menu
 * can print. Nothing here touches stored data, so a bad input changes nothing.
 */
public class Validator {

    // Limits from the brief, kept in one place. Main builds its prompts from these too.
    public static final int MAX_NAME_LENGTH = 40;
    public static final int MIN_STOCK = 0;
    public static final int MAX_STOCK = 1000;
    public static final int MIN_QUANTITY = 1;
    public static final int MAX_QUANTITY = 1000;

    // Trim and uppercase, as the brief asks. null becomes "".
    public static String cleanId(String raw) {
        if (raw == null) {
            return "";
        }
        return raw.trim().toUpperCase();
    }

    // Item ID: one letter plus three digits, e.g. R001
    public static String checkItemId(String raw) {
        String id = cleanId(raw);
        if (!id.matches("[A-Z][0-9]{3}")) {
            throw new IllegalArgumentException(
                "Item ID must be one letter followed by three digits (e.g. R001).");
        }
        return id;
    }

    // Request ID: Q plus three digits, e.g. Q001
    public static String checkRequestId(String raw) {
        String id = cleanId(raw);
        if (!id.matches("Q[0-9]{3}")) {
            throw new IllegalArgumentException(
                "Request ID must be Q followed by three digits (e.g. Q001).");
        }
        return id;
    }

    // Name: 1 to 40 characters after trimming
    public static String checkName(String raw) {
        String name = (raw == null) ? "" : raw.trim();
        if (name.length() < 1 || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                "Item name must be 1 to " + MAX_NAME_LENGTH + " characters.");
        }
        return name;
    }

    // Turns text into an int. Letters, blanks, decimals or huge values all
    // end up here as a clear error instead of crashing the program.
    public static int parseWholeNumber(String text, String label) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(
                label + " must be a whole number in the allowed range.");
        }
    }

    // Initial stock: 0 to 1000
    public static int checkStock(int stock) {
        if (stock < MIN_STOCK || stock > MAX_STOCK) {
            throw new IllegalArgumentException(
                "Initial stock must be a whole number from " + MIN_STOCK + " to " + MAX_STOCK + ".");
        }
        return stock;
    }

    // Request quantity: 1 to 1000
    public static int checkQuantity(int quantity) {
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(
                "Quantity must be a whole number from " + MIN_QUANTITY + " to " + MAX_QUANTITY + ".");
        }
        return quantity;
    }

    // Low-stock threshold: any whole number that is not negative
    public static int checkThreshold(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException(
                "Threshold cannot be negative.");
        }
        return threshold;
    }
}
