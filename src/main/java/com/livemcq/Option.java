package com.livemcq;
public class Option {
    private final int id;
    private final String text;

    public Option(int id, String text) {
        
        if(id <= 0) {
            throw new IllegalArgumentException("Option ID must be positive");
        }
        if(text == null || text.isBlank()) {
            throw new IllegalArgumentException("Option text must not be null or blank");
        }
        this.id = id;
        this.text = text.strip();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
