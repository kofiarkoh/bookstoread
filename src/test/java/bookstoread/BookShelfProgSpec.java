package bookstoread;

import org.example.Book;
import org.example.BookShelf;
import org.example.Progress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@DisplayName("A bookshelf progress")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfProgSpec {

    private BookShelf bookShelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;
    private Book refactoring;

    @BeforeEach
    void init(Map<String,Book> books) throws Exception{
        bookShelf = new BookShelf();

        effectiveJava =books.get("Effective Java");
        codeComplete = books.get("Code Complete");
        mythicalManMonth = books.get("The Mythical Man-Month");
        cleanCode =books.get("Clean Code");
        refactoring = books.get("Refactoring: Improving the Design of Existing Code");
       /* effectiveJava = new Book("Effective Java","Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008, Month.AUGUST, 1));
        refactoring = new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", LocalDate.of(2002, Month.MARCH, 9));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, Month.JUNE, 9));
    */
    }

    @Test
    @DisplayName("is 0% completed and 100% to read when no book is read yet")
    void progress_100_percent_unread(){
        bookShelf.add(effectiveJava,cleanCode,mythicalManMonth,refactoring);
        Progress progress = bookShelf.progress();
        assertThat(progress.getCompleted()).isEqualTo(0);
        assertThat(progress.getToRead()).isEqualTo(100);
    }

    @Test
    @DisplayName("is 40% completed and 60% to read when 2 books are finished and 3 books are not read yet")
    void progress_with_completed_and_read_percentages(){
        bookShelf.add(effectiveJava,cleanCode,mythicalManMonth,refactoring,codeComplete);
        effectiveJava.startedReadingOn(LocalDate.of(2016, Month.JULY, 1));
        effectiveJava.finishedReadingOn(LocalDate.of(2016, Month.JULY, 31));
        cleanCode.startedReadingOn(LocalDate.of(2016, Month.AUGUST, 1));
        cleanCode.finishedReadingOn(LocalDate.of(2016, Month.AUGUST, 31));
        Progress progress = bookShelf.progress();
        assertThat(progress.getCompleted()).isEqualTo(40);
        assertThat(progress.getToRead()).isEqualTo(60);
    }


    @Nested
    @DisplayName("bookshelf searc")
    class BookShelfSearch{

        @BeforeEach
        void setup(){
            bookShelf.add(codeComplete,effectiveJava,mythicalManMonth,cleanCode);
        }

        @Test
        @DisplayName("should find book with title containing test")
        void should_find_book_with_title_containing_test(){
            List<Book> books = bookShelf.findBookByTitle("code");
            assertThat(books.size()).isEqualTo(2);
        }
    }
}
