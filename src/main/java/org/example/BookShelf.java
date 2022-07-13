package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();
    public List<Book> books(){
        return Collections.unmodifiableList(this.books);
    }

    public void add(Book... books){
        Arrays.stream(books).forEach( book -> this.books.add(book));
    }

    public List<Book> arrange() {
        //books.sort(Comparator.naturalOrder());
        return arrange(Comparator.naturalOrder());  // /_/  books.stream().sorted().collect(Collectors.toList());

    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }
}
