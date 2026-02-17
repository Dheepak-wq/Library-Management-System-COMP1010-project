package Library;

/**
 * Represents a shelf in the library. Each shelf has a name, a description,
 * and contains a list of books stored in a BookList.
 * 
 * Students must implement all methods and the constructor.
 */
public class Shelf {

    /** The name of the shelf */
    public String shelfName;

    /** A short description of the shelf */
    public String description;

    /** The list of books stored on this shelf */
    public BookList books;

    /**
     * Constructs a shelf with the given name and description.
     *
     * @param name the shelf's name
     * @param desc the shelf's description
     */
    public Shelf(String name, String desc) {
        // Implementation: Initialized fields and create a new BookList
        this.shelfName = name;
        this.description = desc;
        this.books = new BookList(); // initialises an empty list
    }

    /**
     * Adds a Book to the shelf.
     *
     * @param b the Book to add
     */
    public void addBook(Book b) {
        // Implementation: Added the book to the BookList
        if (this.books != null) {
            this.books.add(b); // adds book to BookList.add()
        }
    }

    /**
     * Checks whether the shelf contains a book with the given title.
     *
     * @param title the title to search for
     * @return true if the title exists on the shelf; false otherwise
     */
    public boolean hasBookTitle(String title) {
        // Implementation: Checks the BookList for the given title
        if (title == null || this.books == null) {
            return false;
        }
        BookNode current = this.books.head;
        while (current != null) {
            if (current.data != null && title.equalsIgnoreCase(current.data.title)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Prints the shelf information and the list of books it contains.
     */
    public void printShelf() {
        // Implementation: Prints shelf name, description, and books
        System.out.println("Shelf: " + shelfName + " (" + description + ")");
        if (this.books != null && this.books.head != null) {
            // Uses the recursion toStringfromNode method from BookList
            System.out.println(this.books.toStringFromNode(this.books.head));
        }
    }
}
