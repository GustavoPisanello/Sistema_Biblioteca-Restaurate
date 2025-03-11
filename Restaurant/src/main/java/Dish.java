import java.io.IOException;

public class Dish {
    String dishName;
    int dishPrice;
    String dishDescription;
    Menu menu;

    public Dish(Menu menu){
        this.menu = menu;
    }

    public void setDish(String dishName, int dishPrice, String dishDescription) throws IOException {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;

        menu.addDish(this);
    }

    public String getName(){
        return this.dishName;
    }

    public int getPrice(){return this.dishPrice; }

    @Override
    public String toString() {
        return "Dish: " + dishName + " | Price: " + dishPrice + " | Description: " + dishDescription;
    }
}
