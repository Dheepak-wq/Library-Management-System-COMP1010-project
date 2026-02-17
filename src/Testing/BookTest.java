package Testing;
import Library.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    Book empty = new Book();
    Book b1 = new Book("The Hobbit", "J. R. R. Tolkien", 1937, "ISBN001");
    Book b2 = new Book("The Lord of the Rings", "J. R. R. Tolkien", 1954, "ISBN002");
    Book b3 = new Book("Dune", "Frank Herbert", 1965, "ISBN003");
    Book b4 = new Book("1984", "George Orwell", 1949, "ISBN004");
    Book b5 = new Book("Foundation", "Isaac Asimov", 1951, "ISBN005");
    Book b6 = new Book("Neuromancer", "William Gibson", 1984, "ISBN006");
    Book b7 = new Book("Brave New World", "Aldous Huxley", 1932, "ISBN007");
    Book b8 = new Book("Fahrenheit 451", "Ray Bradbury", 1953, "ISBN008");
    Book b9 = new Book("The Catcher in the Rye", "J. D. Salinger", 1951, "ISBN009");
    Book b10 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "ISBN010");
    Book b11 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "ISBN011");
    Book b12 = new Book("Moby-Dick", "Herman Melville", 1851, "ISBN012");
    Book b13 = new Book("Pride and Prejudice", "Jane Austen", 1813, "ISBN013");
    Book b14 = new Book("The Martian", "Andy Weir", 2011, "ISBN014");
    Book b15 = new Book("The Name of the Wind", "Patrick Rothfuss", 2007, "ISBN015");

    @Test
    void testConstructor() {
        assertEquals("", empty.title);
        assertEquals("", empty.author);
        assertEquals(0, empty.year);
        assertEquals("", empty.isbn);
        Book custom = new Book("Dune", "Frank Herbert", 1965, "1111");
        assertEquals("Dune", custom.title);
        assertEquals("Frank Herbert", custom.author);
        assertEquals(1965, custom.year);
        assertEquals("1111", custom.isbn);
        Book custom2 = new Book("1984", "George Orwell", 1949, "2222");
        assertEquals("1984", custom2.title);
        assertEquals("George Orwell", custom2.author);
        assertEquals(1949, custom2.year);
        assertEquals("2222", custom2.isbn);
        Book custom3 = new Book("Fake Book", "Nobody", 0, "0000");
        assertNotEquals("", custom3.title);
        assertNotEquals("", custom3.author);
        assertNotEquals(1, custom3.year);
        assertNotEquals("1234", custom3.isbn);
    }

    @Test
    void testHasISBN() {
        assertTrue(b1.hasISBN("ISBN001"));
        assertTrue(b4.hasISBN("ISBN004"));
        assertTrue(b10.hasISBN("ISBN010"));
        assertTrue(b15.hasISBN("ISBN015"));
        assertFalse(b1.hasISBN("isbn001"));
        assertFalse(b2.hasISBN("ISBN999"));
        assertFalse(b3.hasISBN(""));
        assertFalse(b5.hasISBN("001ISBN"));
        assertTrue(empty.hasISBN(""));
        assertFalse(empty.hasISBN("ANY"));
        assertTrue(new Book("", "", 0, "X").hasISBN("X"));
    }

    @Test
    void testTitleEquals() {
        assertTrue(b1.titleEquals("The Hobbit"));
        assertTrue(b1.titleEquals("the hobbit"));
        assertTrue(b3.titleEquals("dune"));
        assertTrue(b4.titleEquals("1984"));
        assertTrue(b10.titleEquals("To Kill a Mockingbird"));
        assertFalse(b8.titleEquals("Dune"));
        assertFalse(b2.titleEquals("The Hobbit"));
        assertFalse(b5.titleEquals("Foundation Trilogy"));
        assertFalse(b12.titleEquals("Moby Dick"));
        assertFalse(b13.titleEquals("Pride & Prejudice"));
        assertFalse(empty.titleEquals(null));
        assertTrue(new Book("", "", 0, "").titleEquals(""));
    }

    @Test
    void testAuthorContains() {
        assertTrue(b1.authorContains("tolk"));
        assertTrue(b2.authorContains("TOL"));
        assertTrue(b4.authorContains("Orw"));
        assertTrue(b6.authorContains("gib"));
        assertTrue(b7.authorContains("Huxley"));
        assertFalse(b3.authorContains("Rowling"));
        assertFalse(b5.authorContains("Rowling"));
        assertFalse(b8.authorContains("King"));
        assertFalse(b9.authorContains("Lewis"));
        assertFalse(b15.authorContains("Martin"));
        assertFalse(empty.authorContains("X"));
        assertTrue(new Book("Title", "SomeAuthor", 0, "1").authorContains("Some"));
        assertTrue(new Book("T", "", 0, "").authorContains("")); 
    }

    @Test
    void testShortInfo() {
        assertEquals("The Hobbit (1937)", b1.shortInfo());
        assertEquals("The Lord of the Rings (1954)", b2.shortInfo());
        assertEquals("Dune (1965)", b3.shortInfo());
        assertEquals("1984 (1949)", b4.shortInfo());
        assertEquals("Foundation (1951)", b5.shortInfo());
        assertNotEquals("The Martian (1938)", b14.shortInfo());
        assertNotEquals("The Great Gatsby", b11.shortInfo());
        assertNotEquals("The Hobbit - 1937", b1.shortInfo());
        assertNotEquals("Dune (1966)", b3.shortInfo());
        assertNotEquals("1984 by George Orwell (1949)", b4.shortInfo());
        assertEquals(" (0)", empty.shortInfo());
        assertNotEquals("Empty (1)", empty.shortInfo());
        assertEquals("EdgeCase (2025)", new Book("EdgeCase", "Author", 2025, "999").shortInfo());
    }

    @Test
    void testToString() {
        assertEquals("The Hobbit by J. R. R. Tolkien (1937), ISBN: ISBN001", b1.toString());
        assertEquals("The Lord of the Rings by J. R. R. Tolkien (1954), ISBN: ISBN002", b2.toString());
        assertEquals("Dune by Frank Herbert (1965), ISBN: ISBN003", b3.toString());
        assertEquals("1984 by George Orwell (1949), ISBN: ISBN004", b4.toString());
        assertEquals("Foundation by Isaac Asimov (1951), ISBN: ISBN005", b5.toString());
        assertNotEquals("The Hobbit (1937)", b1.toString());
        assertNotEquals("Dune (1965)", b3.toString());
        assertNotEquals("1984 by George Orwell", b4.toString());
        assertNotEquals("Foundation by Isaac Asimov", b5.toString());
        assertNotEquals("The Lord of the Rings (1954)", b2.toString());
        assertEquals(" by  (0), ISBN: ", empty.toString());
        assertNotEquals("Empty (0)", empty.toString());
    }

    @Test
    void testEquals() {
        Book b1copy = new Book("Some Title", "Someone", 1111, "ISBN001");
        Book b2mirror = new Book("Doesn't matter", "No one", 0, "ISBN002");
        Book different = new Book("Different", "Nobody", 2000, "DIFF123");
        Book emptyBook = new Book();
        assertTrue(b1.equals(b1copy));
        assertTrue(b1copy.equals(b1));
        assertTrue(b2.equals(b2mirror));
        assertTrue(b2mirror.equals(b2));
        assertTrue(b5.equals(new Book("Other Title", "Other Author", 9999, "ISBN005")));
        assertFalse(b1.equals(different));
        assertFalse(different.equals(b1));
        assertFalse(b4.equals(b5));
        assertFalse(b5.equals(b4));
        assertFalse(b3.equals(null));
        assertFalse(emptyBook.equals(null));
        assertFalse(empty.equals(b1));
        assertTrue(empty.equals(new Book("", "", 0, "")));
    }
}
