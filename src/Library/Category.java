package Library;

import java.util.ArrayList;

/**
 * Represents a category in the library system.
 * A category contains a collection of shelves, each of which holds books.
 * 
 * Students must implement all constructors and methods.
 */
public class Category {

    /** The name of this category */
    public String categoryName;

    /** List of shelves belonging to this category */
    public ArrayList<Shelf> shelves;

    /**
     * Default constructor.
     * Creates an empty category with no name.
     */
    public Category() {
        // Implementation: Initialized fields to default values
        this.categoryName = "";
        this.shelves = new ArrayList<Shelf>();
    }

    /**
     * Constructs a category with the given name.
     *
     * @param name the name of the category
     */
    public Category(String name) {
        // Implementation: Initialized categoryName and create an empty shelves list
        this.categoryName = name;
        this.shelves = new ArrayList<Shelf>();
    }

    /**
     * Adds a shelf to this category.
     *
     * @param s the shelf to add
     */
    public void addShelf(Shelf s) {
        // Implementation: Added shelf to shelves collection
        if (s != null) {
            this.shelves.add(s);
        }
    }

    /**
     * Retrieves the shelf at the given index.
     *
     * @param index the index of the shelf
     * @return the Shelf at the position, or null if out of bounds
     */
    public Shelf getShelf(int index) {
        // Implementation: Returns the shelf at the given index or null
        if (index >= 0 && index < shelves.size()) {
            return shelves.get(index);
        }
        return null;
    }

    /**
     * Searches all shelves in this category for a book with a matching title.
     *
     * @param title the title to search for
     * @return the matching Book, or null if none found
     */
    public Book findBookByTitle(String title) {
        // Implementation: Searches shelves and books for matching title
        if (title == null)
            return null;

        for (Shelf shelf : shelves) {
            if (shelf != null && shelf.books != null) {
                BookNode current = shelf.books.head;
                while (current != null) {
                    Book b = current.data;
                    if (b != null && b.title != null && b.title.equals(title)) {
                        return b;
                    }
                    current = current.next;
                }
            }
        }
        return null;
    }

    /**
     * Counts all books in this category whose author name contains
     * the given fragment (case-insensitive).
     *
     * @param fragment part of the author's name
     * @return number of matching books
     */
    public int countBooksByAuthor(String fragment) {
        // Implementation: Counts books across shelves using BookList
        if (fragment == null)
            return 0;
        int count = 0;
        String lowerFrag = fragment.toLowerCase();

        for (Shelf shelf : shelves) {
            if (shelf != null && shelf.books != null) {
                BookNode current = shelf.books.head;
                while (current != null) {
                    Book b = current.data;
                    if (b != null && b.author != null &&
                            b.author.toLowerCase().contains(lowerFrag)) {
                        count++;
                    }
                    current = current.next;
                }
            }
        }
        return count;
    }

    /**
     * Counts all books across all shelves in this category.
     *
     * @return total number of books
     */
    public int countAllBooks() {
        // Implementation: Sums sizes of all BookLists
        int total = 0;
        for (Shelf shelf : shelves) {
            if (shelf != null && shelf.books != null) {
                BookNode current = shelf.books.head;
                while (current != null) {
                    if (current.data != null) {
                        total++;
                    }
                    current = current.next;
                }
            }
        }
        return total;
    }

    /**
     * Prints information for every shelf and its books.
     */
    public void printAllBooks() {
        // Implementation: Prints category name and all shelf contents
        System.out.println(toStructuredString());
    }

    /**
     * Produces a structured string containing category name,
     * shelf information, and all books.
     *
     * @return formatted multi-line string representation
     */
    public String toStructuredString() {
        // Implementation: Builds and return formatted string of all category contents
        String result = "Category: " + this.categoryName + "\n";

        for (Shelf shelf : shelves) {
            if (shelf != null) {
                result += "Shelf: " + shelf.shelfName + " (" + shelf.description + ")\n";

                if (shelf.books != null) {
                    BookNode current = shelf.books.head;
                    while (current != null) {
                        Book b = current.data;
                        if (b != null) {
                            result += b.title + " by " + b.author + " (" + b.year + ") [" + b.isbn + "]\n";
                        }
                        current = current.next;
                    }
                }
            }
        }
        return result;
    }
}
