/**
 * RequestManager.java  (Part B component 3: Request management)
 * Registers requests: checks them, gives an arrival number, sets PENDING,
 * then adds them to the queue and to the history list.
 */
public class RequestManager {
    private final Storage storage;
    private final ItemManager itemManager;

    public RequestManager(Storage storage, ItemManager itemManager) {
        this.storage = storage;
        this.itemManager = itemManager;
    }

    public Request registerRequest(String requestId, String itemId, int quantity) {
        String reqId = Validator.checkRequestId(requestId);
        String itmId = Validator.checkItemId(itemId);
        Validator.checkQuantity(quantity);

        // History keeps every request, so this also blocks reuse of IDs
        // that were already processed.
        for (Request r : storage.getHistory()) {
            if (r.getId().equals(reqId)) {
                throw new IllegalArgumentException(
                    "Request ID " + reqId + " has already been used in this session.");
            }
        }
        if (itemManager.findItem(itmId) == null) {
            throw new IllegalArgumentException("Item " + itmId + " does not exist.");
        }

        // Only now, with all checks passed, is the arrival number used up.
        Request request = new Request(reqId, itmId, quantity,
                                      storage.takeNextArrivalNumber());
        storage.getPendingQueue().add(request);
        storage.getHistory().add(request);
        return request;
    }
}
