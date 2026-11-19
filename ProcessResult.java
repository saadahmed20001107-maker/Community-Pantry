/**
 * ProcessResult.java
 * Small record of what happened to one request during processing.
 * processPendingRequests() returns a list of these, as described in Part B.
 */
public class ProcessResult {
    private final Request request;
    private final int stockBefore;
    private final int stockAfter;

    public ProcessResult(Request request, int stockBefore, int stockAfter) {
        this.request = request;
        this.stockBefore = stockBefore;
        this.stockAfter = stockAfter;
    }

    public Request getRequest() { return request; }
    public int getStockBefore() { return stockBefore; }
    public int getStockAfter()  { return stockAfter; }
}
