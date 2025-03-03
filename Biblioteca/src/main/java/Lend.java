import java.io.IOException;
import java.util.Date;

public class Lend {
    private final Library library;
    Book book;
    Members member;
    Date date;

    public Lend(Library library) {
        this.library = library;
    }

    public String lendBook(Book book, Members member, Date date) throws IOException {
        if (book.qty <= 0) {
            return "Book not available";
        }

        book.qty -= 1;
        this.book = book;
        this.member = member;
        this.date = date;

        library.addLend(this);
        library.saveData("books.txt", "book");
        return "\n⚠️ Update ⚠️ | Book \"" + book.title + "\" lent to \"" + member.name + "\" on " + date + "\n";
    }

    public String returnBook(int bookId, Members member, Date date) throws IOException {
        if (this.book == null || this.book.id != bookId) {
            return "Book not found";
        }

        for(Lend lend : library.lendList){
            if(lend.book.id == bookId && lend.member.id == member.id){
                book.qty += 1;
                library.returnLend(book.id, member, date);
                library.saveData("books.txt", "book");
                return "\n⚠️ Update ⚠️ | Book returned by " + member.name + " at " + date + "\n";
            }
        }
        return "Member \"" + member.name + "\" did not lend this book";
    }

    @Override
    public String toString() {
        return "Book: " + book.title + " | Member: " + member.name + " | Date: " + date;
    }

    public String formatToSave(){
        return book.id + "," + member.id + "," + date;
    }
}