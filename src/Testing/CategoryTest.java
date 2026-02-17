package Testing;

import Library.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CategoryTest {

    private Category category;
    private Shelf shelf1;
    private Shelf shelf2;
    private Shelf shelf3;
    private Shelf emptyShelf;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    @BeforeEach
    void setUp() {
        category = new Category("Fiction");

        book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "ISBN001");
        book2 = new Book("The Silmarillion", "J.R.R. Tolkien", 1977, "ISBN002");
        book3 = new Book("Dune", "Frank Herbert", 1965, "ISBN003");
        book4 = new Book("1984", "George Orwell", 1949, "ISBN004");
        shelf1 = new Shelf("Shelf 1", "Fantasy classics");
        shelf1.books = new BookList(new Book[] { book1, book2 });

        shelf2 = new Shelf("Shelf 2", "Sci-Fi hits");
        shelf2.books = new BookList(new Book[] { book3 });

        shelf3 = new Shelf("Shelf 3", "Modern literature");
        shelf3.books = new BookList(new Book[] { book4 });

        emptyShelf = new Shelf("Empty Shelf", "No books");
        emptyShelf.books = new BookList(new Book[] {}); // empty list
    }

    @Test
    void testConstructors() {
        Category defaultCat = new Category();
        Category namedCat = new Category("History");

        assertEquals("", defaultCat.categoryName);
        assertNotNull(defaultCat.shelves);
        assertEquals(0, defaultCat.shelves.size());
        assertEquals("History", namedCat.categoryName);
        assertNotNull(namedCat.shelves);
        assertEquals(0, namedCat.shelves.size());
        assertNotEquals(defaultCat.categoryName, namedCat.categoryName);
    }

    @Test
    void testAddShelf() {

        category.shelves = new java.util.ArrayList<>();
        category.addShelf(shelf1);
        category.addShelf(shelf2);
        category.addShelf(emptyShelf);
        category.addShelf(null);
        assertNotNull(category.shelves);
        int nonNullCount = 0;
        for (int i = 0; i < category.shelves.size(); i++) {
            if (category.shelves.get(i) != null) {
                nonNullCount++;
            }
        }
        assertEquals(3, nonNullCount);
        assertEquals(shelf1, category.shelves.get(0));
        assertEquals(shelf2, category.shelves.get(1));
        assertEquals(emptyShelf, category.shelves.get(2));
        boolean containsNull = false;
        for (int i = 0; i < category.shelves.size(); i++) {
            if (category.shelves.get(i) == null) {
                containsNull = true;
                break;
            }
        }
        assertFalse(containsNull);
    }

    @Test
    void testGetShelf() {
        category.shelves = new java.util.ArrayList<>();
        category.shelves.add(shelf1);
        category.shelves.add(shelf2);
        category.shelves.add(null);
        category.shelves.add(emptyShelf);
        assertEquals(shelf1, category.getShelf(0));
        assertEquals(shelf2, category.getShelf(1));
        assertNull(category.getShelf(2)); // null shelf
        assertEquals(emptyShelf, category.getShelf(3));
        assertNull(category.getShelf(-1));
        assertNull(category.getShelf(4));
        assertNotEquals(shelf3, category.getShelf(1));
        assertNull(category.getShelf(5));
        assertNotNull(category.getShelf(0));
        category.shelves.add(null);
        int nullIndex = category.shelves.size() - 1;
        assertNull(category.getShelf(nullIndex));
    }

    @Test
    void testFindBookByTitle() {
        category.shelves = new java.util.ArrayList<>();
        category.shelves.add(shelf1);
        category.shelves.add(shelf2);
        category.shelves.add(shelf3);
        category.shelves.add(emptyShelf);
        assertEquals(book1, category.findBookByTitle("The Hobbit"));
        assertEquals(book3, category.findBookByTitle("Dune"));
        assertEquals(book4, category.findBookByTitle("1984"));
        assertNull(category.findBookByTitle("Nonexistent"));
        assertNull(category.findBookByTitle(null));
        assertNotEquals(book2, category.findBookByTitle("Dune"));
        assertNotNull(category.findBookByTitle("The Hobbit"));
    }

    @Test
    void testCountBooksByAuthor() {
        
        category.shelves = new java.util.ArrayList<>();
        category.shelves.add(shelf1);
        category.shelves.add(shelf2);
        category.shelves.add(null);
        category.shelves.add(shelf3);
        category.shelves.add(emptyShelf);

        assertEquals(2, category.countBooksByAuthor("Tolkien"));
        assertEquals(1, category.countBooksByAuthor("Herbert"));
        assertEquals(1, category.countBooksByAuthor("Orwell"));
        assertEquals(0, category.countBooksByAuthor("Rowling"));
        assertEquals(0, category.countBooksByAuthor(null));

        assertEquals(2, category.countBooksByAuthor("Tolk"));
        assertEquals(1, category.countBooksByAuthor("Herb"));
        assertEquals(1, category.countBooksByAuthor("Orw"));
        assertEquals(0, category.countBooksByAuthor("Row"));

        assertNotEquals(3, category.countBooksByAuthor("Tolkien"));
        assertTrue(category.countBooksByAuthor("Tolkien") > 0);
        assertFalse(category.countBooksByAuthor("Rowling") > 0);
    }

    @Test
    void testCountAllBooks() {
        category.shelves = new java.util.ArrayList<>();
        category.shelves.add(shelf1);
        category.shelves.add(shelf2);
        category.shelves.add(null);
        category.shelves.add(shelf3);
        category.shelves.add(emptyShelf);

        Shelf shelfWithNullBook = new Shelf("NullBookShelf", "Contains null");
        shelfWithNullBook.books = new BookList(new Book[] { null });
        category.shelves.add(shelfWithNullBook);

        assertEquals(4, category.countAllBooks());
        assertNotEquals(5, category.countAllBooks());
        assertTrue(category.countAllBooks() > 0);
        assertFalse(category.countAllBooks() < 0);

        assertNotNull(category.shelves);
        assertEquals(6, category.shelves.size());
        assertNull(category.shelves.get(2));
        assertNotNull(category.shelves.get(0));

    }

    /**
     * Example expected output from printAllBooks():
     *
     * Category: Fiction
     * Shelf: Shelf 1 (Fantasy classics)
     * The Hobbit by J.R.R. Tolkien (1937) [ISBN001]
     * The Silmarillion by J.R.R. Tolkien (1977) [ISBN002]
     * Shelf: Shelf 2 (Sci-Fi hits)
     * Dune by Frank Herbert (1965) [ISBN003]
     * Shelf: Shelf 3 (Modern literature)
     * 1984 by George Orwell (1949) [ISBN004]
     * Shelf: Empty Shelf (No books)
     */

    @Test
    void testPrintAllBooks() {
        category.shelves = new java.util.ArrayList<>();
        category.shelves.add(shelf1); 
        category.shelves.add(shelf2); 
        category.shelves.add(shelf3); 
        category.shelves.add(emptyShelf); 

        /** Capture output for testing */
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        category.printAllBooks();
        System.setOut(originalOut);
        String printed = out.toString();
        // end of testing String capture

        assertTrue(printed.contains("Category: Fiction"));
        assertTrue(printed.contains("Shelf: Shelf 1 (Fantasy classics)"));
        assertTrue(printed.contains("Shelf: Shelf 2 (Sci-Fi hits)"));
        assertTrue(printed.contains("Shelf: Shelf 3 (Modern literature)"));
        assertTrue(printed.contains("Shelf: Empty Shelf (No books)"));
        assertTrue(printed.contains("The Hobbit"));
        assertFalse(printed.contains("The Lord of the Rings"));
        assertTrue(printed.contains("Dune"));
        assertFalse(printed.contains("Normal People"));
        assertTrue(printed.contains("The Hobbit") &&
                printed.contains("The Silmarillion") &&
                printed.indexOf("The Hobbit") < printed.indexOf("The Silmarillion"));
        assertFalse(printed.contains("null"));
    }

    /**
     * Example output of toStructuredString():
     *
     * Category: Fiction
     * Shelf: Shelf 1 (Fantasy classics)
     * The Hobbit by J.R.R. Tolkien (1937) [ISBN001]
     * The Silmarillion by J.R.R. Tolkien (1977) [ISBN002]
     * Shelf: Shelf 2 (Sci-Fi hits)
     * Dune by Frank Herbert (1965) [ISBN003]
     * Shelf: Shelf 3 (Modern literature)
     * 1984 by George Orwell (1949) [ISBN004]
     * Shelf: Empty Shelf (No books)
     */

    @Test
    void testToStructuredString() {

        category.shelves = new java.util.ArrayList<>();
        category.shelves.add(shelf1);
        category.shelves.add(shelf2);
        category.shelves.add(shelf3);
        category.shelves.add(emptyShelf);
        String structured = category.toStructuredString();
        assertNotNull(structured);
        assertNotEquals("", structured);
        assertTrue(structured.contains("Category: Fiction"));
        assertTrue(structured.contains("Shelf: Shelf 1 (Fantasy classics)"));
        assertTrue(structured.contains("Shelf: Shelf 2 (Sci-Fi hits)"));
        assertTrue(structured.contains("Shelf: Shelf 3 (Modern literature)"));
        assertTrue(structured.contains("Shelf: Empty Shelf (No books)"));
        assertTrue(structured.contains("The Hobbit"));
        assertTrue(structured.contains("The Silmarillion"));
        assertTrue(structured.contains("Dune"));
        assertTrue(structured.contains("1984"));
        int idx1 = structured.indexOf("Shelf: Shelf 1");
        int idx2 = structured.indexOf("Shelf: Shelf 2");
        int idx3 = structured.indexOf("Shelf: Shelf 3");
        int idx4 = structured.indexOf("Shelf: Empty Shelf");
        assertTrue(idx1 < idx2 && idx2 < idx3 && idx3 < idx4);
        assertFalse(structured.contains("Nonexistent"));
        assertFalse(structured.contains("null"));
    }

}
