import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Library library = new Library();
        library.loadData("books.txt", "book");
        library.loadData("members.txt", "member");
        library.loadData("lends.txt", "lend");
        mainScreen(library);
    }

    public static void mainScreen(Library library) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n---- Welcome to the Library System 😊📙 -----");
        System.out.println("What would you like to do today?\n");
        System.out.println("1 - Insert a Book");
        System.out.println("2 - Remove a Book");
        System.out.println("3 - Lend a Book");
        System.out.println("4 - Return a Book");
        System.out.println("5 - List Books");
        System.out.println("6 - Insert a Member");
        System.out.println("7 - List Members");
        System.out.println("8 - List Lends");
        System.out.println("9 - Exit");

        int Choose = sc.nextInt();

        switch(Choose){
            case 1:
                try{
                    insertBook(library);
                    break;
                } catch (Exception e){
                    System.out.println("Error: " + e);
                    break;
                }
            case 2:
                try{
                    removeBook(library);
                    break;
                } catch (IOException e){
                    System.out.println("Error: " + e);
                    break;
                }
            case 3:
                try{
                    lendBook(library);
                    break;
                } catch (Exception e){
                    System.out.println("Error: " + e);
                    break;
                }

            case 4:
                try{
                    returnBook(library);
                    break;
                } catch (Exception e){
                    System.out.println("Error: " + e);
                    break;
                }

            case 5:
                try{
                    System.out.println("\nBook list 📖");
                    library.listBooks();
                    mainScreen(library);
                    break;
                } catch(Exception e){
                    System.out.println("Error: " + e);
                    break;
                }
            case 6:
                try{
                    insertMember(library);
                    break;
                } catch (Exception e){
                    System.out.println("Error: " + e);
                    break;
                }
            case 7:
                try{
                    System.out.println("\nMembers list 🧑‍🦰");
                    library.listMembers();
                    mainScreen(library);
                    break;
                } catch(Exception e){
                    System.out.println("Error: " + e);
                    break;
                }
            case 8:
                try{
                    System.out.println("\nLends list 📓");
                    library.listLends();
                    mainScreen(library);
                    break;
                } catch(Exception e){
                    System.out.println("Error: " + e);
                    break;
                }
            case 9:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        sc.close();
    }

    public static void lendBook(Library library) throws IOException {
        Lend lend = new Lend(library);  // Pass library to constructor
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the ID of the book: ");
        int bookId = sc.nextInt();

        Book bookToLend = new Book(library);
        for (Book book : library.collection) {
            if (book.id == bookId) {
                bookToLend = book;
                break;
            }
        }

        System.out.println("Enter the ID of the member: ");
        int memberId = sc.nextInt();

        Members memberWhoLent = new Members(library);
        for (Members member : library.membersList) {
            if (member.id == memberId) {
                memberWhoLent = member;
                break;
            }
        }

        Date date = new Date();
        System.out.println(lend.lendBook(bookToLend, memberWhoLent, date));
        library.saveData("lends.txt", "lend");
        mainScreen(library);
    }

    public static void returnBook(Library library) throws IOException {
        Lend lend = new Lend(library);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the ID of the book: ");
        int bookId = sc.nextInt();

        Book bookToReturn = null;
        for (Book book : library.collection) {
            if (book.id == bookId) {
                bookToReturn = book;
                break;
            }
        }

        if (bookToReturn == null) {
            System.out.println("Book not found");
            mainScreen(library);
            return;
        }

        System.out.println("Enter the ID of the member: ");
        int memberId = sc.nextInt();

        Members memberWhoReturned = null;
        for (Members member : library.membersList) {
            if (member.id == memberId) {
                memberWhoReturned = member;
                break;
            }
        }

        if (memberWhoReturned == null) {
            System.out.println("Member not found");
            mainScreen(library);
            return;
        }

        Date date = new Date();
        lend.book = bookToReturn;
        lend.member = memberWhoReturned;
        System.out.println(lend.returnBook(bookToReturn.id, memberWhoReturned, date));
        library.saveData("lends.txt", "lend");
        mainScreen(library);
    }

    public static void insertMember(Library library) throws IOException {
        Members member = new Members(library);
        Scanner sc = new Scanner(System.in);

        library.loadData("members.txt", "member");

        System.out.println("Enter the member's name:");
        String memberName = sc.nextLine();

        System.out.println("Enter the member's email:");
        String memberEmail = sc.nextLine();

        member.setMember(memberName, memberEmail);
        library.saveData("members.txt", "member");

        System.out.println(memberName);

        mainScreen(library);
    }

    public static void insertBook(Library library) throws IOException {
        Book book = new Book(library);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the book's title: ");
        String bookTitle = sc.nextLine();

        System.out.println("Enter the book's author: ");
        String bookAuthor = sc.nextLine();

        System.out.println("Enter the book's ISBN: ");
        String bookISBN = sc.nextLine();

        System.out.println("Enter the book quantity: ");
        int bookQty = sc.nextInt();

        book.setBook(bookTitle, bookAuthor, bookISBN, bookQty);
        library.saveData("books.txt", "book");

        mainScreen(library);
    }

    public static void removeBook(Library library) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the ID of the book you want to remove: ");
        int bookId = sc.nextInt();

        System.out.println(library.removeBook(bookId));
        library.saveData("books.txt", "book");

        mainScreen(library);
    }
}