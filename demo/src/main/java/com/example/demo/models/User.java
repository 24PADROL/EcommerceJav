public class User {
    private String username;
    private String email;
    private String password;
    private List<Order> orderHistory;

    public void register() {}
    public boolean login(String email, String password) {}
    public List<Order> viewOrderHistory() {}
}
