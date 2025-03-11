import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu {
    public List<Dish> menu = new ArrayList<Dish>();

    public void addDish(Dish dish) throws IOException {
        menu.add(dish);
        saveMenu();
    }

    public void removeDish(String name) throws IOException {
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getName().equals(name)) {
                iterator.remove();
            }
        }
        saveMenu();
    }

    public void saveMenu() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))) {
            for (Dish dish : menu) {
                writer.write(dish.toString());
                writer.newLine();
            }
        }

    }

    public void loadMenu() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
