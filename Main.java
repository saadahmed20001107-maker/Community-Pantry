import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 * Console menu for the Community Pantry Management System (Version 1).
 * It only reads input and prints; the real work is done by the components.
 */
public class Main {

    private static final Storage storage = new Storage();
    private static final ItemManager itemManager = new ItemManager(storage);
    private static final RequestManager requestManager = new RequestManager(storage, itemManager);
    private static final Processor processor = new Processor(storage, itemManager);
    private static final Reporter reporter = new Reporter(storage);

    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.println("=== Community Pantry Management System (Version 1) ===");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = ask("Choose an option: ");
            if (choice == null) {          // input ended (e.g. piped file)
                break;
            }
            try {
                switch (choice.trim()) {
                    case "1": registerItem();   break;
                    case "2": findItem();       break;
                    case "3": registerRequest(); break;
                    case "4": processRequests(); break;
                    case "5": reporter.displayHistory(); break;
                    case "6": lowStock();       break;
                    case "7": running = false;  break;
                    case "8": loadSampleData(); break;
                    default:
                        System.out.println("Please enter a number from 1 to 8.");
                }
            } catch (IllegalArgumentException e) {
                // Validation failed: nothing was saved, so just show why.
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye.");
        in.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Register item");
        System.out.println("2. Find item");
        System.out.println("3. Register request");
        System.out.println("4. Process pending requests (FIFO)");
        System.out.println("5. Display all request statuses / history");
        System.out.println("6. List low-stock items");
        System.out.println("7. Exit");
        System.out.println("8. Load assignment sample data");
    }

    // Prints the prompt and returns the typed line, or null if there is no more input.
    private static String ask(String prompt) {
        System.out.print(prompt);
        if (!in.hasNextLine()) {
            return null;
        }
        return in.nextLine();
    }

    private static String askRequired(String prompt) {
        String text = ask(prompt);
        return (text == null) ? "" : text;
    }

    private static void registerItem() {
        String id = askRequired("Item ID (e.g. R001): ");
        String name = askRequired("Item name (1-" + Validator.MAX_NAME_LENGTH + " characters): ");
        int stock = Validator.parseWholeNumber(askRequired("Initial stock (" + Validator.MIN_STOCK + "-" + Validator.MAX_STOCK + "): "), "Initial stock");
        Item item = itemManager.registerItem(id, name, stock);
        System.out.println("Item registered: " + item);
    }

    private static void findItem() {
        String id = askRequired("Item ID to find: ");
        Item item = itemManager.findItem(id);
        if (item == null) {
            System.out.println("Item not found.");
        } else {
            System.out.println("Found: " + item);
        }
    }

    private static void registerRequest() {
        String reqId = askRequired("Request ID (e.g. Q001): ");
        String itemId = askRequired("Item ID: ");
        int qty = Validator.parseWholeNumber(askRequired("Quantity (" + Validator.MIN_QUANTITY + "-" + Validator.MAX_QUANTITY + "): "), "Quantity");
        Request r = requestManager.registerRequest(reqId, itemId, qty);
        System.out.println("Request registered: " + r);
    }

    private static void processRequests() {
        List<ProcessResult> results = processor.processPendingRequests();
        if (results.isEmpty()) {
            System.out.println("No pending requests. Stock unchanged.");
            reporter.displayItems();
            return;
        }
        System.out.println();
        System.out.printf("%-6s %-11s %-8s %-4s %-7s %-10s %s%n",
                "Order", "Request ID", "Item ID", "Qty", "Before", "Status", "Stock after");
        for (ProcessResult p : results) {
            Request r = p.getRequest();
            System.out.printf("%-6d %-11s %-8s %-4d %-7d %-10s %d%n",
                    r.getArrivalNumber(), r.getId(), r.getItemId(), r.getQuantity(),
                    p.getStockBefore(), r.getStatus(), p.getStockAfter());
        }
        System.out.println();
        System.out.println("Final stock:");
        reporter.displayItems();
    }

    private static void lowStock() {
        int threshold = Validator.parseWholeNumber(
                askRequired("Low-stock threshold (0 or more): "), "Threshold");
        reporter.generateLowStockReport(threshold);
    }

    // The dataset from the brief, so the demo and regression tests start the same way each time.
    private static void loadSampleData() {
        // Check every sample ID first, so a clash changes nothing (no half-loaded data).
        String[] itemIds = {"R001", "O001"};
        String[] requestIds = {"Q001", "Q002", "Q003"};
        for (String id : itemIds) {
            if (itemManager.findItem(id) != null) {
                System.out.println("Sample data could not be loaded: item " + id
                        + " already exists. Nothing was changed.");
                return;
            }
        }
        for (Request used : storage.getHistory()) {
            for (String id : requestIds) {
                if (used.getId().equals(id)) {
                    System.out.println("Sample data could not be loaded: request " + id
                            + " already exists. Nothing was changed.");
                    return;
                }
            }
        }
        try {
            itemManager.registerItem("R001", "Rice packs", 10);
            itemManager.registerItem("O001", "Oil bottles", 4);
            requestManager.registerRequest("Q001", "R001", 3);
            requestManager.registerRequest("Q002", "O001", 5);
            requestManager.registerRequest("Q003", "R001", 7);
            System.out.println("Sample data loaded: 2 items, 3 pending requests.");
        } catch (IllegalArgumentException e) {
            System.out.println("Sample data could not be loaded: " + e.getMessage());
        }
    }
}
