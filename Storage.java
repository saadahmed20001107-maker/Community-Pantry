import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Storage.java  (Part B component 4: Storage)
 * Holds the three collections chosen in B1 and nothing else:
 *   HashMap  - items by ID (fast lookup)
 *   Queue    - pending requests (FIFO), built on ArrayDeque
 *   ArrayList - every request ever registered (history, keeps arrival order)
 * Everything stays in memory in Version 1.
 */
public class Storage {
    private final Map<String, Item> items = new HashMap<>();
    private final Queue<Request> pendingQueue = new ArrayDeque<>();
    private final List<Request> history = new ArrayList<>();
    private int nextArrivalNumber = 1;

    public Map<String, Item> getItems()       { return items; }
    public Queue<Request> getPendingQueue()   { return pendingQueue; }
    public List<Request> getHistory()          { return history; }

    // Hands out 1, 2, 3 ... in registration order
    public int takeNextArrivalNumber() {
        return nextArrivalNumber++;
    }
}
