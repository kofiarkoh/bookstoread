package bookstoread;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FailureAndErrorTest {

    @Test
    @Disabled
    void stringIsNotEmpty(){
        String name = "";
        assertFalse(name.isEmpty());
    }

    @Test @Disabled
    void thisMethodThrowsException(){
        String name = null;
        assertTrue(name.isEmpty());
    }
}
