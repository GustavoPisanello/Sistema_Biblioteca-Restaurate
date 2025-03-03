import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library {
    public final List<Book> collection = new ArrayList<>();
    public final List<Members> membersList = new ArrayList<>();
    public final List<Lend> lendList = new ArrayList<>();

    public void addBook(Book book){
        collection.add(book);
    }

    public String removeBook(int bookId){
        for(Book book: collection){
            if(book.id == bookId){
                collection.remove(book);
                return "\n⚠️ Update ⚠️ | The book \"" + book.title + "\" has been removed successfully!\n     New Book List:";
            }
        }
        return "Book not Found";
    }

    public void listBooks() {
        for (Book book : collection) {
            System.out.println(book);
        }
    }

    public void addMember(Members member){
        membersList.add(member);
    }

    public void listMembers(){
        for(Members members: membersList){
            System.out.println(members);
        }
    }

    public void addLend(Lend lend){
        lendList.add(lend);
    }

    public void returnLend(int bookId, Members member, Date date) throws IOException {
        for(Lend lend: lendList){
            if(lend.book.id == bookId && lend.member.id == member.id){
                lendList.remove(lend);
                return;
            }
        }
    }

    public void listLends(){
        if(lendList.isEmpty()){
            System.out.println("There is no lends yet");
            return;
        }
        for(Lend lend: lendList){
            System.out.println(lend);
        }
    }

    public void saveData(String archiveName, String dataType) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archiveName))){
            if(dataType.equals("book")) {
                for (Book book : collection) {
                    writer.write(book.formatToSave());
                    writer.newLine();
                }
            }
            else if(dataType.equals("member")){
                for (Members member: membersList){
                    writer.write(member.formatToSave());
                    writer.newLine();
                }
            }
            else if (dataType.equals("lend")){
                for (Lend lend: lendList){
                    writer.write(lend.formatToSave());
                    writer.newLine();
                }
            }
        }
    }

    public void loadData(String archiveName, String dataType) throws IOException {
        File file = new File(archiveName);
        if (!file.exists()){
            System.out.println("📂 No data file found. Creating a new one.");
            file.createNewFile();
            return;
        }

        if(dataType.equals("book")) {
            collection.clear();
        }
        else if(dataType.equals("member")){
            membersList.clear();
        }
        else if (dataType.equals("lend")){
            lendList.clear();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archiveName))){
            String line;
            while ((line = reader.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()){
                    continue;
                }
                String[] values = line.split(",");
                try {
                    if(dataType.equals("book")) {
                        Book book = new Book(this);
                        book.id = Integer.parseInt(values[0].trim()); // trim() removes leading and trailing whitespaces
                        book.title = values[1].trim();
                        book.author = values[2].trim();
                        book.isbn = values[3].trim();
                        book.qty = Integer.parseInt(values[4].trim());

                        collection.add(book);
                    }
                    else if(dataType.equals("member")){
                        Members member = new Members(this);
                        member.id = Integer.parseInt(values[0].trim());
                        member.name = values[1].trim();
                        member.email = values[2].trim();

                        membersList.add(member);
                    }
                    else if (dataType.equals("lend")){
                        Lend lend = new Lend(this);
                        lend.book = collection.stream().filter(book -> book.id == Integer.parseInt(values[0].trim())).findFirst().orElse(null);
                        lend.member = membersList.stream().filter(member -> member.id == Integer.parseInt(values[1].trim())).findFirst().orElse(null);
                        lend.date = new Date(Long.parseLong(values[2].trim()));

                        lendList.add(lend);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Error due converting line: " + line + " | " + e.getMessage());
                }
            }
        }
    }
}