package Testing;
import Library.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookListTest {

    Book b1 = new Book("The Hobbit", "J. R. R. Tolkien", 1937, "ISBN001");
    Book b2 = new Book("Dune Messiah", "Frank Herbert", 1969, "ISBN002");
    Book b3 = new Book("Animal Farm", "George Orwell", 1945, "ISBN003");
    Book b4 = new Book("Neuromancer", "William Gibson", 1984, "ISBN004");
    Book b5 = new Book("Foundation", "Isaac Asimov", 1951, "ISBN005");
    Book b6 = new Book("Brave New World", "Aldous Huxley", 1932, "ISBN006");
    Book b7 = new Book("Fahrenheit 451", "Ray Bradbury", 1953, "ISBN007");
    Book b8 = new Book("The Catcher in the Rye", "J. D. Salinger", 1951, "ISBN008");
    Book b9 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "ISBN009");
    Book b10 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "ISBN010");
    Book emptyBook = new Book();

    @Test
    void testConstructor() {
        BookList emptyList = new BookList();
        assertNull(emptyList.head);
        assertEquals(0, emptyList.size());
    }

    @Test
    void testAdd() {
        
        BookList list = new BookList();
        list.add(b10);
        list.add(null);
        assertEquals(b10, list.head.data);
        assertNull(list.head.next);
        BookList list1 = new BookList();
        list1.add(b1);
        list1.add(b2);
        list1.add(b3);
        assertEquals(b1, list1.head.data);
        assertEquals(b2, list1.head.next.data);
        assertEquals(b3, list1.head.next.next.data);
        assertNull(list1.head.next.next.next);
        BookList list2 = new BookList();
        list2.add(b4);
        list2.add(b5);
        list2.add(b6);
        list2.add(b7);
        list2.add(b8);
        list2.add(b9);
        list2.add(b10);
        assertEquals(b4, list2.head.data);
        assertEquals(b10, list2.head.next.next.next.next.next.next.data);
        assertNull(list2.head.next.next.next.next.next.next.next);
    }

    @Test
    void testSize() {
        BookList list1 = new BookList(new Book[] { b1, b2, b3 });
        assertEquals(3, list1.size());
        BookList list2 = new BookList(new Book[] { b4, b5, b6, b7, b8, b9, b10 });
        assertEquals(7, list2.size());
        BookList emptyList = new BookList(new Book[] {});
        assertEquals(0, emptyList.size());
    }

    @Test
    void testContainsTitle() {
        BookList list = new BookList(new Book[] { b1, b3, b6, emptyBook });
        assertTrue(list.containsTitle("The Hobbit"));
        assertTrue(list.containsTitle("animal farm"));
        assertTrue(list.containsTitle("Brave New World"));
        assertFalse(list.containsTitle("Dune Messiah"));
        assertFalse(list.containsTitle("#$%$%"));
        assertFalse(list.containsTitle(null));
        assertNotEquals(true, list.containsTitle("Nonexistent Book"));
        assertNotNull(list.containsTitle("The Hobbit"));
    }

    @Test
    void testContainsAuthor() {
        BookList list = new BookList(new Book[] { b1, b2, b3, b4, b5 });
        assertTrue(list.containsAuthor(list.head, "tolk"));
        assertTrue(list.containsAuthor(list.head, "Herbert"));
        assertTrue(list.containsAuthor(list.head, "Orwell"));
        assertTrue(list.containsAuthor(list.head, "Gibson"));
        assertFalse(list.containsAuthor(list.head, "Rowling"));
        assertFalse(list.containsAuthor(list.head, "D."));
        assertNotNull(list.containsAuthor(list.head, "tolk"));
        assertFalse(list.containsAuthor(null, "tolk"));
        assertFalse(list.containsAuthor(list.head, null));
        assertNotEquals(true, list.containsAuthor(list.head, "Nonexistent"));
    }

    @Test
    void testFindBookByISBN() {
        BookList list = new BookList(new Book[] { b1, b4, b7 });
        assertEquals(b1, list.findBookByISBN(list.head, "ISBN001"));
        assertEquals(b4, list.findBookByISBN(list.head, "ISBN004"));
        assertEquals(b7, list.findBookByISBN(list.head, "ISBN007"));
        assertNull(list.findBookByISBN(list.head, "ISBN999"));
        assertNull(list.findBookByISBN(null, "ISBN007"));
        assertNull(list.findBookByISBN(list.head, null));
        assertNotEquals(b1, list.findBookByISBN(list.head, "ISBN004"));
        assertNotNull(list.findBookByISBN(list.head, "ISBN001"));
    }

    @Test
    void testCountBooksByAuthor() {
        BookList list = new BookList(new Book[] { b1, b2, b3, b2, b2, b5, b2, b8 });
        assertEquals(2, list.countBooksByAuthor(list.head, "J."));
        assertEquals(4, list.countBooksByAuthor(list.head, "Herbert"));
        assertEquals(1, list.countBooksByAuthor(list.head, "Orwell"));
        assertEquals(1, list.countBooksByAuthor(list.head, "Asimov"));
        assertEquals(0, list.countBooksByAuthor(list.head, "Rowling"));
        assertEquals(0, list.countBooksByAuthor(list.head, "F."));
        assertEquals(0, list.countBooksByAuthor(null, "J."));
        assertEquals(0, list.countBooksByAuthor(list.head, null));
        assertNotEquals(3, list.countBooksByAuthor(list.head, "Herbert"));
        assertNotNull(list.countBooksByAuthor(list.head, "Asimov"));
    }

    @Test
    void testToStringFromNode() {
        
        Book book1 = new Book("Book1", "Author1", 2001, "ISBN001");
        Book book2 = new Book("Book2", "Author2", 2002, "ISBN002");
        Book book3 = new Book("Book3", "Author3", 2003, "ISBN003");
        Book book4 = new Book("Book4", "Author4", 2004, "ISBN004");
        BookList list = new BookList(new Book[] { book1, book2, book3 });
        String expectedChain = "Book1 (2001) -> Book2 (2002) -> Book3 (2003)";
        String chainResult = list.toStringFromNode(list.head);
        assertEquals(expectedChain, chainResult);
        assertNotEquals("Book1 by Author1", chainResult);
        assertNotNull(chainResult);
        assertTrue(chainResult.contains("Book1 (2001)"));
        assertTrue(chainResult.contains("Book2 (2002)"));
        assertTrue(chainResult.contains("Book3 (2003)"));
        assertFalse(chainResult.contains("Book4 (2004)"));
        BookList emptyList = new BookList(new Book[] {});
        assertEquals("", emptyList.toStringFromNode(emptyList.head));
        assertNotEquals("something", emptyList.toStringFromNode(emptyList.head));
        assertNotNull(emptyList.toStringFromNode(emptyList.head));
        BookList singleList = new BookList(new Book[] { book4 });
        String expectedSingle = "Book4 (2004)";
        String singleResult = singleList.toStringFromNode(singleList.head);
        assertEquals(expectedSingle, singleResult);
        assertNotEquals(expectedChain, singleResult);
        assertTrue(singleResult.contains("Book4 (2004)"));
        assertFalse(singleResult.contains("Book1 (2001)"));
        assertEquals("", list.toStringFromNode(null));
        assertNotEquals(expectedChain, list.toStringFromNode(null));
    }

}
