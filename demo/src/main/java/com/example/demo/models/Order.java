public class Order {
    private String orderID;
    private User user;
    private Map<Product, Integer> items;
    private String status;

    public void placeOrder() {}
    public void updateStatus(String newStatus) {}
}
