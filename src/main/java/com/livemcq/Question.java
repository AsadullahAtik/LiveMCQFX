package com.livemcq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final int id;
    private final String questionText;
    private final List<Option> options;
    private final int correctOptionId;

    public Question(
            int id,
            String questionText,
            List<Option> options,
            int correctOptionId) {
        if (id <= 0) {
            throw new IllegalArgumentException("Question ID must be positive");
        }
        if (questionText == null || questionText.isBlank()) {
            throw new IllegalArgumentException("Question text must not be null or blank");
        }

        if (options == null) {
            throw new IllegalArgumentException("Options must not be null");
        }
        List<Option> optionsCopy = new ArrayList<>(options);
        if (optionsCopy.isEmpty()) {
            throw new IllegalArgumentException("Options must not be empty");
        }
        boolean found = false;
        for (Option option : optionsCopy) {
            if (option == null) {
                throw new IllegalArgumentException("Options must not contain null values");
            }
            if (option.getId() == correctOptionId) {
                found = true;

            }
        }
        if (!found) {
            throw new IllegalArgumentException("Correct option ID must match an option in the options list");
        }
        for (int i = 0; i < optionsCopy.size(); i++) {

            for (int j = i + 1; j < optionsCopy.size(); j++) {

                if (optionsCopy.get(i).getId() == optionsCopy.get(j).getId()) {
                    throw new IllegalArgumentException(
                            "Duplicate option ID: " + optionsCopy.get(i).getId());
                }

            }
        }
        this.id = id;
        this.questionText = questionText.strip();
        this.options = optionsCopy;
        this.correctOptionId = correctOptionId;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Option> getOptions() {
        return Collections.unmodifiableList(options);
    }

    public int getCorrectOptionId() {
        return correctOptionId;
    }

}
