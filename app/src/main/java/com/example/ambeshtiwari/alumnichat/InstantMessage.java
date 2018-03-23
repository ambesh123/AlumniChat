package com.example.ambeshtiwari.alumnichat;

/**
 * Created by Ambesh Tiwari on 10-02-2018.
 */

public class InstantMessage {
    private String message;
    private String author;

    InstantMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public InstantMessage() {
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
