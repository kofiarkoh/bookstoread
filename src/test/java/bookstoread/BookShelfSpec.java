package bookstoread;


import org.example.Book;
import org.example.BookShelf;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("<= BookShelf Specification =>")
public class BookShelfSpec {

    private BookShelf bookShelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeEach
    void init() throws Exception{
        bookShelf = new BookShelf();
        effectiveJava = new Book("Effective Java", "Joshua Bloch",
                LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel",
                LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick " +
                "Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
    }


    @Test
    public void shelfEmptyWhenNoBookAdded() throws Exception{
       // BookShelf shelf = new BookShelf();
        List<Book> books = bookShelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    void book_shelf_contains_two_books_when_two_books_added(){
       // BookShelf bookShelf = new BookShelf();
        bookShelf.add(effectiveJava,codeComplete);
     //   bookShelf.add();
        List<Book> books = bookShelf.books();
        assertEquals(2,books.size(),"book shelf should have 2 books.");
    }

    @Test
    void empty_book_shelf_when_add_is_called_without_books(){
      //  BookShelf bookShelf = new BookShelf();
        bookShelf.add();
        List<Book> books = bookShelf.books();
        assertTrue(books.isEmpty(),"BooksShelf should be empty");
    }

    @Test
    void books_returned_from_bookshelf_is_immutable(){
     //   BookShelf bookShelf = new BookShelf();
        bookShelf.add(effectiveJava,codeComplete);
        List<Book> books = bookShelf.books();
        try{
            books.add(mythicalManMonth);
            fail("should not be able to add book to books");
        }
        catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException,"Should throw UnsupportedOperationException");
        }
    }

    /* FEATURE TWO */

    @Test
    void book_shelf_arranged_by_book_title(){
        bookShelf.add(effectiveJava,codeComplete,mythicalManMonth);
        List<Book> books = bookShelf.arrange();
        assertEquals(Arrays.asList("Code Complete", "Effective Java", "The Mythical Man-Month"), books,
                "books should be arranged lexicographically by title");

    }

    @Test
    void books_in_shelf_are_in_insertion_order_after_calling_arrange(){
        bookShelf.add(effectiveJava,codeComplete,mythicalManMonth);
        bookShelf.arrange();
        List<Book   > books = bookShelf.books();
        assertEquals(Arrays.asList(effectiveJava,codeComplete,mythicalManMonth),books,
                "Books in bookshelf are not in insertion order");
    }

    @Test
    void book_shelf_arranged_by_user_provided_criterion(){
       bookShelf.add(effectiveJava,codeComplete,mythicalManMonth);
        List<Book> books = bookShelf.arrange(Comparator.<Book>naturalOrder().reversed());
        assertEquals(Arrays.asList(mythicalManMonth,effectiveJava,codeComplete),books,
                "Books in a bookshelf are arranged in descending order of book title");

    }
}
