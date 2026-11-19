/**
 * ItemManager.java  (Part B component 2: Item management)
 * Registers items and finds them by ID.
 */
public class ItemManager {
    private final Storage storage;

    public ItemManager(Storage storage) {
        this.storage = storage;
    }

    // Validates everything first, so a failed call saves nothing.
    public Item registerItem(String itemId, String name, int stock) {
        String id = Validator.checkItemId(itemId);
        String cleanName = Validator.checkName(name);
        Validator.checkStock(stock);

        if (storage.getItems().containsKey(id)) {
            throw new IllegalArgumentException("Item ID " + id + " already exists.");
        }
        Item item = new Item(id, cleanName, stock);
        storage.getItems().put(id, item);
        return item;
    }

    // Returns the item, or null if the ID is well-formed but not in the system.
    // A badly formed ID throws, so the user sees a format message instead.
    public Item findItem(String itemId) {
        String id = Validator.checkItemId(itemId);
        return storage.getItems().get(id);
    }
}
