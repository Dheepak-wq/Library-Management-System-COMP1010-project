package Library;

public class BookList {
    public BookNode head;

    /**
     * Default constructor for an empty BookList.
     */
    public BookList() {
        // Implementation: Initialized head to an empty list state
        head = null;
    }

    /**
     * 
     * @param book the book to add at the end of the list
     *             Creates a book node at the end of a list using the book parameter
     *             as the book node's data.
     */
    public void add(Book book) {
        // Implementation: Created a book node at the end of the list
        if (book == null)
            return;
        if (head == null) {
            head = new BookNode(book);
        } else {
            BookNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new BookNode(book);
        }
    }

    /**
     * 
     * @return the amount of nodes within the list
     */
    public int size() {
        // Implementation: Implemented a count of all nodes in the list
        int count = 0;
        BookNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * @param title the title to search for
     * @return true if a matching title exists, otherwise false
     */
    public boolean containsTitle(String title) {
        // Implementation: Implemented title search
        if (title == null)
            return false;
        BookNode current = head;
        while (current != null) {
            if (current.data.title.equalsIgnoreCase(title)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Recursively checks whether any book's author field contains the given
     * fragment.
     * 
     * @param node     the current node in the recursive traversal
     * @param fragment the substring to search for in the author name
     * @return true if found, otherwise false
     */
    public boolean containsAuthor(BookNode node, String fragment) {
        // Implementation: Implemented recursive author fragment search
        if (node == null || fragment == null)
            return false;
        if (node.data.author.toLowerCase().contains(fragment.toLowerCase())) {
            return true;
        }
        return containsAuthor(node.next, fragment);
    }

    /**
     * Recursively searches the list for a book with the given ISBN code.
     * 
     * @param node the current node in the recursive traversal
     * @param code the ISBN to look for
     * @return the matching Book, or null if not found
     */
    public Book findBookByISBN(BookNode node, String code) {
        // Implementation: Implemented recursive ISBN search
        if (node == null || code == null)
            return null;
        if (node.data.isbn.equals(code)) {
            return node.data;
        }
        return findBookByISBN(node.next, code);
    }

    /**
     * Recursively counts how many books have an author containing the given
     * fragment.
     * 
     * @param node     the current node in the recursive traversal
     * @param fragment the author substring to match
     * @return the number of books whose authors contain the fragment
     */
    public int countBooksByAuthor(BookNode node, String fragment) {
        // Implementation: Implemented recursive count by author fragment
        if (node == null || fragment == null) {
            return 0;
        }

        int count = 0;
        if (node.data != null && node.data.author != null && node.data.author.contains(fragment)) {
            count = 1;
        }

        return count + countBooksByAuthor(node.next, fragment);
    }

    /**
     * Recursively builds and returns a string representation of the list
     * starting from the given node.
     * Hint : uses each book's short info.
     * 
     * @param node the starting node
     * @return a string showing book info separated by " -> "
     */
    public String toStringFromNode(BookNode node) {
        // Implementation: Implements a recursive toString functionality

        // Base case
        if (node == null) {
            return "";
        }

        // Gets info for current book
        String currentInfo = node.data.title + " (" + node.data.year + ")";

        // If last node, returns without adding an arrow
        if (node.next == null) {
            return currentInfo;
        }

        // Recursive step current + arrow + result of rest of list
        return currentInfo + " -> " + toStringFromNode(node.next);
    }

    /** DO NOT MODIFY */
    /**
     * Test-only constructor to create a BookList with predefined contents
     * without using add(). Not required for students to use.
     */
    public BookList(Book[] books) {
        if (books == null || books.length == 0) {
            head = null;
            return;
        }

        head = new BookNode(books[0]);
        BookNode current = head;

        for (int i = 1; i < books.length; i++) {
            current.next = new BookNode(books[i]);
            current = current.next;
        }
    }
}
