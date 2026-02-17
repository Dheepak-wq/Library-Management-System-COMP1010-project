package Library;

/**
 * Represents a book in the library system.
 * Stores title, author, year of publication, and ISBN number.
 * 
 * <p>
 * Students must implement all methods and constructors.
 * </p>
 */
public class Book {

    /** Title of the book */
    public String title;

    /** Author of the book */
    public String author;

    /** Year the book was published */
    public int year;

    /** ISBN identifier for the book */
    public String isbn;

    /**
     * Default constructor.
     * Initializes all fields to default values.
     */
    public Book() {
        // Implementation: Initialized all fields with default values
        this.title = "";
        this.author = "";
        this.year = 0;
        this.isbn = "";

    }

    /**
     * Constructs a book with the given data.
     *
     * @param title  the book's title
     * @param author the book's author
     * @param year   publication year
     * @param isbn   ISBN code
     */
    public Book(String title, String author, int year, String isbn) {
        // Implementation: Assigned parameter values to fields
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;

    }

    /**
     * Checks whether this book's ISBN matches the given code exactly.
     *
     * @param code the ISBN to compare
     * @return true if the ISBNs match exactly; false otherwise
     */
    public boolean hasISBN(String code) {
        // Implementation: Implemented exact ISBN comparison
        return this.isbn.equals(code);
    }

    /**
     * Checks whether this book's title matches the given title,
     * ignoring case.
     *
     * @param t the title to compare
     * @return true if titles match ignoring case; false otherwise
     */
    public boolean titleEquals(String t) {
        // Implementation: Implemented case-insensitive title comparison
        return this.title.equalsIgnoreCase(t);
    }

    /**
     * Determines whether the book's author name contains the provided
     * fragment, ignoring case.
     *
     * @param fragment part of an author's name
     * @return true if fragment is found; false otherwise
     */
    public boolean authorContains(String fragment) {
        // Implementation: Implemented case-insensitive substring check
        return this.author.toLowerCase().contains(fragment.toLowerCase());
    }

    /**
     * Returns a short string with title and publication year.
     *
     * @return a compact string describing the book
     */
    public String shortInfo() {
        // Implementation: Built and returned a formatted short description
        return this.title + " (" + this.year + ")";
    }

    /**
     * Returns a full string representation of the book, including author,
     * year, and ISBN.
     *
     * @return descriptive string for the book
     */
    @Override
    public String toString() {
        // Implementation: Built and returned full formatted description
        return this.title + " by " + this.author + " (" + this.year + "), ISBN: " + this.isbn;
    }

    /**
     * Compares this book with another for equality using ISBN.
     *
     * @param other the other book to compare
     * @return true if ISBNs match and other is not null; false otherwise
     */
    public boolean equals(Book other) {
        // Implementation: Compared ISBN values
        return other != null && this.isbn.equals(other.isbn);
    }
}
