package Library;

/**
 * A node in a singly linked list of Book objects.
 * Each node holds a Book and a reference to the next node.
 * 
 * Students must complete all constructors.
 */
public class BookNode {

    /** The book stored in this node */
    public Book data;

    /** Reference to the next node in the list */
    public BookNode next;

    /**
     * Default constructor.
     * Initializes this node with no book and no next reference.
     */
    public BookNode() {
        // Implementation: Initialized fields to default values
        this.data = null;
        this.next = null;
    }

    /**
     * Constructs a node containing the given Book.
     *
     * @param data the Book to store in this node
     */
    public BookNode(Book data) {
        // Implementation: Initialized this node with the provided Book

        // Assigned the passed Book object to the data field
        this.data = data;
        // The next reference should be null by default until linked
        this.next = null;
    }
}
