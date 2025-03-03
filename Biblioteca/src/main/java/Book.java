import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Book {
    public int id;
    public String title;
    public String author;
    public String isbn;
    public int qty;
    Library library;
    private static int lastId = 0;

    public Book(Library library){
        this.library = library;
    }


    public void setBook(String title, String author, String isbn, int qty) throws IOException {
        IdManager idManager = new IdManager();
        this.id = idManager.generateNewId(lastId, "books.txt");
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.qty = qty;

        library.addBook(this);
    }

    public String formatToSave(){
        return id + "," + title + "," + author + "," + isbn + "," + qty;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | by " + author +  " | ISBN: " + isbn + " | Quantity: " + qty;
    }
}