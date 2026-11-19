import java.util.ArrayList;
import java.util.List;

/**
 * Processor.java  (Part B component 5: Processing)
 * Takes requests off the front of the queue (FIFO) and decides each one.
 * It is the only class that changes stock or a request's status.
 */
public class Processor {
    private final Storage storage;
    private final ItemManager itemManager;

    public Processor(Storage storage, ItemManager itemManager) {
        this.storage = storage;
        this.itemManager = itemManager;
    }

    // Same logic as the B2 pseudocode.
    public List<ProcessResult> processPendingRequests() {
        List<ProcessResult> results = new ArrayList<>();

        while (!storage.getPendingQueue().isEmpty()) {
            Request request = storage.getPendingQueue().poll();

            if (request.getStatus() == Request.Status.PENDING) {
                Item item = itemManager.findItem(request.getItemId());
                int before = item.getStock();

                if (before >= request.getQuantity()) {
                    updateStock(item.getId(), request.getQuantity());
                    request.setStatus(Request.Status.FULFILLED);
                } else {
                    request.setStatus(Request.Status.REJECTED);
                }
                results.add(new ProcessResult(request, before, item.getStock()));
            }
        }
        return results;
    }

    // Safety net: refuses to take more than is in stock, so stock can never go below 0.
    public void updateStock(String itemId, int quantity) {
        Item item = itemManager.findItem(itemId);
        if (quantity > item.getStock()) {
            throw new IllegalArgumentException(
                "Not enough stock of " + item.getId() + "; stock left unchanged.");
        }
        item.reduceStock(quantity);
    }
}
