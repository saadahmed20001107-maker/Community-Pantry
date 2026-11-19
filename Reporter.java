import java.util.ArrayList;
import java.util.List;

/**
 * Reporter.java  (Part B component 6: Reporting)
 * Prints the history, the item list and the low-stock report.
 * It only reads data; it never changes it.
 */
public class Reporter {
    private final Storage storage;

    public Reporter(Storage storage) {
        this.storage = storage;
    }

    // Items sorted by ID so the output is the same every run (a HashMap has no order).
    private List<Item> sortedItems() {
        List<Item> list = new ArrayList<>(storage.getItems().values());
        list.sort((a, b) -> a.getId().compareTo(b.getId()));
        return list;
    }

    public List<Request> displayHistory() {
        List<Request> history = storage.getHistory();
        if (history.isEmpty()) {
            System.out.println("No requests have been registered yet.");
            return history;
        }
        System.out.println();
        System.out.printf("%-8s %-11s %-8s %-9s %s%n",
                "Arrival", "Request ID", "Item ID", "Quantity", "Status");
        for (Request r : history) {
            System.out.printf("%-8d %-11s %-8s %-9d %s%n",
                    r.getArrivalNumber(), r.getId(), r.getItemId(),
                    r.getQuantity(), r.getStatus());
        }
        return history;
    }

    public void displayItems() {
        if (storage.getItems().isEmpty()) {
            System.out.println("No items registered yet.");
            return;
        }
        System.out.println();
        System.out.printf("%-8s %-40s %s%n", "Item ID", "Name", "Stock");
        for (Item i : sortedItems()) {
            System.out.printf("%-8s %-40s %d%n", i.getId(), i.getName(), i.getStock());
        }
    }

    public List<Item> generateLowStockReport(int threshold) {
        Validator.checkThreshold(threshold);
        List<Item> low = new ArrayList<>();
        for (Item i : sortedItems()) {
            if (i.getStock() <= threshold) {
                low.add(i);
            }
        }
        if (low.isEmpty()) {
            System.out.println("No items are at or below the threshold of " + threshold + ".");
        } else {
            System.out.println();
            System.out.println("Low-stock items (stock <= " + threshold + "):");
            System.out.printf("%-8s %-40s %s%n", "Item ID", "Name", "Stock");
            for (Item i : low) {
                System.out.printf("%-8s %-40s %d%n", i.getId(), i.getName(), i.getStock());
            }
        }
        return low;
    }
}
