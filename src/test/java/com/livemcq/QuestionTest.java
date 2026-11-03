package com.livemcq;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class QuestionTest {
    @Test
    void shouldCreateQuestionWithValidValues() {

        // Arrange
        Option option1 = new Option(1, "Java");
        Option option2 = new Option(2, "Python");
        List<Option> options = List.of(option1, option2);
        Question question = new Question(1, "What is your favorite programming language?", options, 1);

        // Assert
        assertEquals(1, question.getId());
        assertEquals("What is your favorite programming language?", question.getQuestionText());
        assertEquals(options, question.getOptions());
        assertEquals(1, question.getCorrectOptionId());
    }

    @Test
    void shouldThrowExceptionWhenIdIsZero() {
        Option option1 = new Option(1, "Java");
        Option option2 = new Option(2, "Python");
        List<Option> options = List.of(option1, option2);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(0, "What is your favorite programming language?", options, 1));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNegative() {
        Option option1 = new Option(1, "Java");
        Option option2 = new Option(2, "Python");
        List<Option> options = List.of(option1, option2);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(-1, "What is your favorite programming language?", options, 1));
    }

    @Test
    void shouldThrowExceptionWhenQuestionTextIsNull() {
        Option option1 = new Option(1, "Java");
        Option option2 = new Option(2, "Python");
        List<Option> options = List.of(option1, option2);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, null, options, 1));
    }

    @Test
    void shouldThrowExceptionWhenQuestionTextIsBlank() {
        Option option1 = new Option(1, "Java");
        Option option2 = new Option(2, "Python");
        List<Option> options = List.of(option1, option2);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "   ", options, 1));
    }

    @Test
    void shouldThrowExceptionWhenOptionsIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "What is your favorite programming language?", null, 1));
    }

    @Test
    void shouldThrowExceptionWhenOptionsIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "What is your favorite programming language?", new ArrayList<>(), 1));
    }

    @Test
    void shouldThrowExceptionWhenOptionsContainsNull() {
        Option option = new Option(1, "Java");
        List<Option> options = new ArrayList<>();
        options.add(option);
        options.add(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "What is your favorite programming language?", options, 1));
    }

    @Test
    void shouldThrowExceptionWhenOptionsContainsNullBeforeDuplicateCheck() {
        Option option1 = new Option(1, "Java");
        List<Option> options = new ArrayList<>();
        options.add(option1);
        options.add(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "What is your favorite programming language?", options, 1));
    }

    @Test
    void shouldThrowExceptionWhenCorrectOptionIdDoesNotExist() {
        List<Option> options = List.of(
                new Option(1, "Java"),
                new Option(2, "Python"));

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "What is your favorite programming language?", options, 3));
    }

    @Test
    void shouldThrowExceptionWhenOptionsContainDuplicateIds() {
        List<Option> options = List.of(
                new Option(1, "Java"),
                new Option(1, "Python"));

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(1, "What is your favorite programming language?", options, 1));
    }

    @Test
    void shouldProtectOptionsFromExternalModification() {

        Option option1 = new Option(1, "Java");
        Option option2 = new Option(2, "Python");

        List<Option> options = new ArrayList<>(List.of(option1, option2));

        Question question = new Question(
                1, "What is your favorite programming language?", options, 1);

        // Test 1: Getter দিয়ে পাওয়া List পরিবর্তন করা যাবে না
        List<Option> retrievedOptions = question.getOptions();

        assertThrows(
                UnsupportedOperationException.class,
                () -> retrievedOptions.add(new Option(3, "C++")));

        // Test 2: Original List পরিবর্তন করলেও Question পরিবর্তন হবে না
        options.remove(option2);

        assertEquals(2, question.getOptions().size());
    }
}
