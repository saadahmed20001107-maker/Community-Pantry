/**
 * Item.java
 * One pantry item. Only the stock can change after the item is created,
 * and only through reduceStock(), which the Processing component calls.
 */
public class Item {
    private final String id;
    private final String name;
    private int stock;

    public Item(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public String getId()   { return id; }
    public String getName() { return name; }
    public int getStock()   { return stock; }

    // Caller must check first that quantity <= stock (see Processor.updateStock).
    public void reduceStock(int quantity) {
        stock = stock - quantity;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | stock " + stock;
    }
}
