import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Members {
    public int id;
    public String name;
    public String email;
    Library library;
    public static int lastId = 0;

    public Members(Library library){
        this.library = library;
    }

    public void setMember(String nome, String email) throws IOException {
        IdManager idManager = new IdManager();
        this.id = idManager.generateNewId(lastId, "members.txt");
        this.name = nome;
        this.email = email;

        library.addMember(this);
    }

    public String formatToSave(){
        return id + "," + name + "," + email;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Email: " + email;
    }
}
