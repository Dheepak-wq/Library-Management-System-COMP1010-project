package Testing;
import Library.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookNodeTest {

    
    Book b1 = new Book("The Hobbit", "J. R. R. Tolkien", 1937, "ISBN001");
    Book b2 = new Book("Dune Messiah", "Frank Herbert", 1969, "ISBN003");
    Book b3 = new Book("Animal Farm", "George Orwell", 1945, "ISBN004");
    Book b4 = new Book("Neuromancer", "William Gibson", 1984, "ISBN006");
    Book emptyBook = new Book();

    @Test
    void testConstructor() {
        BookNode node1 = new BookNode(b1);
        BookNode node2 = new BookNode(b2);
        BookNode node3 = new BookNode(b3);
        assertEquals(b1, node1.data);
        assertNull(node1.next);
        assertEquals(b2, node2.data);
        assertNull(node2.next);
        assertEquals(b3, node3.data);
        assertNull(node3.next);
        BookNode defaultNode = new BookNode();
        assertNull(defaultNode.data);
        assertNull(defaultNode.next);
        BookNode nodeEmpty = new BookNode(emptyBook);
        assertEquals(emptyBook, nodeEmpty.data);
        assertNull(nodeEmpty.next);
        assertNotEquals(node1.data, nodeEmpty.data);
        BookNode nullBookNode = new BookNode(null);
        assertNull(nullBookNode.data);
        assertNull(nullBookNode.next);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        assertEquals(node2, node1.next);
        assertEquals(node3, node2.next);
        assertNull(node3.next);
        defaultNode.next = nullBookNode;
        assertEquals(nullBookNode, defaultNode.next);
        assertNull(nullBookNode.next);
        assertNotEquals(node2.data, node1.data) ;   
    }   
}  


