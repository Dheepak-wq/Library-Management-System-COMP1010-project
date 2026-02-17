package Testing;

import Library.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ShelfTest {

    Book b1 = new Book("The Hobbit", "J. R. R. Tolkien", 1937, "ISBN001");
    Book b2 = new Book("The Lord of the Rings", "J. R. R. Tolkien", 1954, "ISBN002");
    Book b3 = new Book("Dune", "Frank Herbert", 1965, "ISBN003");
    Book b4 = new Book("1984", "George Orwell", 1949, "ISBN004");
    Book b5 = new Book("Foundation", "Isaac Asimov", 1951, "ISBN005");
    Book b6 = new Book("Neuromancer", "William Gibson", 1984, "ISBN006");
    Book emptyBook = new Book();

    @Test
    void testConstructor() {
        Shelf shelf = new Shelf("Classics", "Timeless literature");
        assertEquals("Classics", shelf.shelfName);
        assertEquals("Timeless literature", shelf.description);
        assertNotNull(shelf.books);
        assertEquals(0, shelf.books.size());
        assertNotEquals(1, shelf.books.size());
        assertFalse(shelf.books.head != null); // head should be null
    }

    @Test
    void testAddBook() {
        Shelf sciFi = new Shelf("Sci-Fi", "Futuristic tales");
        sciFi.books = new BookList(new Book[] { b3, b5, b6, emptyBook });
        assertNotNull(sciFi.books.head);
        assertEquals(b3, sciFi.books.head.data);
        assertEquals(b5, sciFi.books.head.next.data);
        assertEquals(b6, sciFi.books.head.next.next.data);
        assertEquals(emptyBook, sciFi.books.head.next.next.next.data);
        assertNull(sciFi.books.head.next.next.next.next);
        assertNotEquals(b3, sciFi.books.head.next.data);
        assertNotEquals(b1, sciFi.books.head.data);
    }

    @Test
    void testHasBookTitle() {
        Shelf fantasy = new Shelf("Fantasy", "Magical worlds");
        fantasy.books = new BookList(new Book[] { b1, b2, emptyBook, null });
        assertTrue(fantasy.hasBookTitle("The Hobbit"));
        assertTrue(fantasy.hasBookTitle("the hobbit"));
        assertTrue(fantasy.hasBookTitle("The Lord of the Rings"));
        assertFalse(fantasy.hasBookTitle("Dune"));
        assertFalse(fantasy.hasBookTitle("$"));
        assertFalse(fantasy.hasBookTitle(null));
        assertNotEquals(true, fantasy.hasBookTitle("Dune"));
        assertNotEquals(false, fantasy.hasBookTitle("The Hobbit"));
    }

    @Test
    void testPrintShelf() {
        Shelf fantasy = new Shelf("Fantasy", "Epic adventures");
        fantasy.books = new BookList(new Book[] { b1, b2 });

        Shelf sciFi = new Shelf("Sci-Fi", "Futuristic tales");
        sciFi.books = new BookList(new Book[] { b3, b4 });

        Shelf emptyShelf = new Shelf("Empty Shelf", "No books");
        emptyShelf.books = new BookList(new Book[] {});

        // Capture output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        fantasy.printShelf();
        sciFi.printShelf();
        emptyShelf.printShelf();
        System.setOut(originalOut);
        String printed = out.toString();
        // end of output capture

        assertTrue(printed.contains("Shelf: Fantasy (Epic adventures)"));
        assertTrue(printed.contains("Shelf: Sci-Fi (Futuristic tales)"));
        assertTrue(printed.contains("Shelf: Empty Shelf (No books)"));
        assertTrue(printed.contains(b1.title));
        assertTrue(printed.contains(b2.title));
        assertTrue(printed.contains(b3.title));
        assertTrue(printed.contains(b4.title));
        int i1 = printed.indexOf(b1.title);
        int i2 = printed.indexOf(b2.title);
        assertTrue(i1 >= 0);
        assertTrue(i2 > i1);
        int emptyIdx = printed.indexOf("Shelf: Empty Shelf (No books)");
        assertTrue(emptyIdx >= 0);
        assertFalse(printed.contains("null"));
    }

}
