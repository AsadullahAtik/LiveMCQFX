package com.livemcq;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionTest {

    @Test
    void shouldCreateOptionWithValidValues() {

        // Arrange
        Option option = new Option(1, "Java");

        // Assert
        assertEquals(1, option.getId());
        assertEquals("Java", option.getText());
        
    }
    @Test
void shouldThrowExceptionWhenIdIsZero() {

    assertThrows(
        IllegalArgumentException.class,
        () -> new Option(0, "Java")
    );
}

@Test
void shouldThrowExceptionWhenIdIsNegative() {

    assertThrows(
        IllegalArgumentException.class,
        () -> new Option(-1, "Java")
    );
}
@Test
void shouldThrowExceptionWhenTextIsNull() {

    assertThrows(
        IllegalArgumentException.class,
        () -> new Option(1, null)
    );}
    @Test 
    void shouldThrowExceptionWhenTextIsBlank() {

        assertThrows(
            IllegalArgumentException.class,
            () -> new Option(1, "   ")
        );
    }


}

// ├── shouldCreateOptionWithValidValues()
// ├── shouldThrowExceptionWhenIdIsZero()
// ├── shouldThrowExceptionWhenIdIsNegative()
// ├── shouldThrowExceptionWhenTextIsNull()
// ├── shouldThrowExceptionWhenTextIsEmpty()
// ├── shouldThrowExceptionWhenTextIsBlank()
// └── shouldRemoveSurroundingWhitespaceFromText()
