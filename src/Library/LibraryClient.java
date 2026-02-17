package Library;

/**
 * LibraryClient
 * --------------------------
 * This is the driver program students must complete.
 *
 * IMPORTANT — READ CAREFULLY:
 * -----------------------------------------
 * Your final program output MUST MATCH the
 * sample output EXACTLY with any deviation from
 * the required output format attracting a penalty.
 *
 * WHAT YOU MUST DO:
 * • Create Book objects.
 * • Create Shelf objects and add books to them.
 * • Create Category objects and add shelves.
 * • Call the appropriate methods.
 * • Arrange your calls so that your output
 * matches the assignment's expected output.
 *
 * A few sample Book objects are provided below
 * so you can see how to begin. You will need
 * to add many more depending on the output.
 */
public class LibraryClient {

    public static void main(String[] args) {

        // ===== SAMPLE BOOK CREATION (students to add more) =====
        Book b1 = new Book("Pride and Prejudice", "Jane Austen", 1813, "ISBN001");
        Book b2 = new Book("1984", "George Orwell", 1949, "ISBN002");
        Book b3 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "ISBN003");

        // Implementation: Created additional Books as required by the expected output.
        Book b4 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "ISBN004");
        Book b5 = new Book("Moby Dick", "Herman Melville", 1851, "ISBN005");
        Book b6 = new Book("War and Peace", "Leo Tolstoy", 1869, "ISBN006");
        Book b7 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "ISBN007");
        Book b8 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "ISBN008");
        Book b9 = new Book("Brave New World", "Aldous Huxley", 1932, "ISBN009");
        Book b10 = new Book("Jane Eyre", "Charlotte Bronte", 1847, "ISBN010");
        Book b11 = new Book("The Odyssey", "Homer", -700, "ISBN011");
        Book b12 = new Book("The Divine Comedy", "Dante Alighieri", 1320, "ISBN012");
        Book b13 = new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, "ISBN013");
        Book b14 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky", 1880, "ISBN014");
        Book b15 = new Book("Frankenstein", "Mary Shelley", 1818, "ISBN015");

        // Implementation: Created additional shelves and add appropriate books.
        Shelf s1 = new Shelf("Classics", "Timeless literature from around the world");
        s1.addBook(b1);
        s1.addBook(b3);
        s1.addBook(b4);
        s1.addBook(b5);
        s1.addBook(b10);

        Shelf s2 = new Shelf("Science Fiction", "Imaginative and futuristic tales");
        s2.addBook(b2);
        s2.addBook(b9);

        Shelf s3 = new Shelf("Fantasy", "Magical worlds and adventures");
        s3.addBook(b8);

        Shelf s4 = new Shelf("Russian Literature", "Masterpieces of Russian authors");
        s4.addBook(b6);
        s4.addBook(b13);
        s4.addBook(b14);

        Shelf s5 = new Shelf("Poetry & Epics", "Epic poems and classics");
        s5.addBook(b11);
        s5.addBook(b12);

        Shelf s6 = new Shelf("Gothic & Horror", "Dark tales and gothic novels");
        s6.addBook(b15);
        s6.addBook(b7);

        // Implementation: Created additional categories and added shelves accordingly.
        Category worldLit = new Category("World Literature");
        worldLit.addShelf(s1);
        worldLit.addShelf(s2);
        worldLit.addShelf(s3);
        worldLit.addShelf(s4);
        worldLit.addShelf(s5);
        worldLit.addShelf(s6);

        Category fiction = new Category("Fiction");
        fiction.addShelf(s1);
        fiction.addShelf(s2);
        fiction.addShelf(s3);

        Category russianGothic = new Category("Russian & Gothic");
        russianGothic.addShelf(s4);
        russianGothic.addShelf(s6);

        Category poetryEpics = new Category("Poetry & Epics");
        poetryEpics.addShelf(s5);

        // Implementation: Calls all other required methods so the overall output
        // EXACTLY matches the expected assignment output.

        // World Literature Section
        System.out.println("=== World Literature ===");
        System.out.println("Category: " + worldLit.categoryName);
        for (Shelf shelf : worldLit.shelves) {
            System.out.println("Shelf: " + shelf.shelfName + " (" + shelf.description + ")");
            BookNode current = shelf.books.head;
            while (current != null) {
                // Manually adds two spaces and formatting ISBN to match Sample output
                System.out.println("  " + current.data.title + " by " + current.data.author +
                        " (" + current.data.year + "), ISBN: " + current.data.isbn);
                current = current.next;
            }
        }
        System.out.println("\nTotal books: " + worldLit.countAllBooks());
        System.out.println("---------------------------------------------\n");

        // Fiction Section
        System.out.println("=== Fiction ===");
        System.out.println("Category: " + fiction.categoryName);
        for (Shelf shelf : fiction.shelves) {
            System.out.println("Shelf: " + shelf.shelfName + " (" + shelf.description + ")");
            BookNode current = shelf.books.head;
            while (current != null) {
                System.out.println("  " + current.data.title + " by " + current.data.author +
                        " (" + current.data.year + "), ISBN: " + current.data.isbn);
                current = current.next;
            }
        }
        System.out.println("\nTotal books: " + fiction.countAllBooks());
        System.out.println("---------------------------------------------\n");

        // Russian & Gothic Section
        System.out.println("=== Russian & Gothic ===");
        System.out.println("Category: " + russianGothic.categoryName);
        for (Shelf shelf : russianGothic.shelves) {
            System.out.println("Shelf: " + shelf.shelfName + " (" + shelf.description + ")");
            BookNode current = shelf.books.head;
            while (current != null) {
                System.out.println("  " + current.data.title + " by " + current.data.author +
                        " (" + current.data.year + "), ISBN: " + current.data.isbn);
                current = current.next;
            }
        }
        System.out.println("\nTotal books: " + russianGothic.countAllBooks());
        System.out.println("---------------------------------------------\n");

        // Poetry & Epics Section
        System.out.println("=== Poetry & Epics ===");
        System.out.println("Category: " + poetryEpics.categoryName);
        for (Shelf shelf : poetryEpics.shelves) {
            System.out.println("Shelf: " + shelf.shelfName + " (" + shelf.description + ")");
            BookNode current = shelf.books.head;
            while (current != null) {
                System.out.println("  " + current.data.title + " by " + current.data.author +
                        " (" + current.data.year + "), ISBN: " + current.data.isbn);
                current = current.next;
            }
        }
        System.out.println("\nTotal books: " + poetryEpics.countAllBooks());
        System.out.println("---------------------------------------------\n");

        // Method calls for counts and searches using exact method names
        System.out.println("Books by 'Fyodor' in World Literature: " + worldLit.countBooksByAuthor("Fyodor"));
        System.out.println("Books by 'Fyodor' in Russian & Gothic: " + russianGothic.countBooksByAuthor("Fyodor"));
        System.out.println("Total books in Fiction: " + fiction.countAllBooks());
        System.out.println("Find 'The Hobbit' in World Literature: " + worldLit.findBookByTitle("The Hobbit"));
        System.out.println("Find '1984' in Fiction: " + fiction.findBookByTitle("1984"));
        System.out.println("Find 'War and Peace' in Poetry & Epics: " + poetryEpics.findBookByTitle("War and Peace"));
    }
}
