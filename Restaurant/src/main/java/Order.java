import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Dish> dishes = new ArrayList<>();
    private String client;
    private int orderNumber;
    private static final List<Order> allOrders = new ArrayList<>();

    public void takeOrder(String client, int orderNumber, List<Dish> dishes) throws IOException {
        
        this.client = client;
        this.orderNumber = orderNumber;
        this.dishes = new ArrayList<>(dishes);


        allOrders.removeIf(order -> order.orderNumber == orderNumber);
        allOrders.add(this);
        saveAllOrders();
    }

    private double getTotalPrice() {
        double totalPrice = 0;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order Number: " + orderNumber + " | Client: " + client + " | Total Price: " + getTotalPrice() + " | Ordered Dishes: " + dishes.toString();
    }

    private void saveAllOrders() throws IOException {
        allOrders.clear();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
            for (Order order : allOrders) {
                writer.write(order.toString());
                writer.newLine();
            }
        }
    }

    public void loadOrder() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}