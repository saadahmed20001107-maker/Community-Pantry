/**
 * Request.java
 * One request for an item. The program gives it an arrival number when it
 * is registered, and the status starts as PENDING.
 */
public class Request {

    public enum Status { PENDING, FULFILLED, REJECTED }

    private final String id;
    private final String itemId;
    private final int quantity;
    private final int arrivalNumber;
    private Status status;

    public Request(String id, String itemId, int quantity, int arrivalNumber) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.arrivalNumber = arrivalNumber;
        this.status = Status.PENDING;
    }

    public String getId()          { return id; }
    public String getItemId()      { return itemId; }
    public int getQuantity()       { return quantity; }
    public int getArrivalNumber()  { return arrivalNumber; }
    public Status getStatus()      { return status; }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "#" + arrivalNumber + " " + id + " | item " + itemId
                + " | qty " + quantity + " | " + status;
    }
}
