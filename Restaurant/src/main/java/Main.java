import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        Dish dish = new Dish(menu);
        Dish dish2 = new Dish(menu);
        Dish dish3 = new Dish(menu);
        dish.setDish("Pizza", 10, "Tasty");
        dish2.setDish("Hot Dog", 5, "Delicious");
        dish3.setDish("Ice Cream", 10, "Cold and refreshing");

        Order order = new Order();

        List<Dish> firstOrder = new ArrayList<>();
        List<Dish> secondOrder = new ArrayList<>();
        firstOrder.add(dish);
        firstOrder.add(dish2);
        secondOrder.add(dish3);

        order.takeOrder("Jonathan", 1, firstOrder);
        order.takeOrder("Joseph", 2, secondOrder);


        order.loadOrder();

    }
}