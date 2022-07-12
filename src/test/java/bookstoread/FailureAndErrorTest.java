package bookstoread;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FailureAndErrorTest {

    @Test
    void stringIsNotEmpty(){
        String name = "";
        assertFalse(name.isEmpty());
    }

    @Test
    void thisMethodThrowsException(){
        String name = null;
        assertTrue(name.isEmpty());
    }
}
