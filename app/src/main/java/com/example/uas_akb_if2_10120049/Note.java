package com.example.uas_akb_if2_10120049;

import com.google.firebase.Timestamp;

public class Note {
    private String title;
    private String description;
    private String category;
    private Timestamp createdOn;

    public Note() {
        // Empty Constructor needed
    }

    public Note(String title, String description, String category, Timestamp date){
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdOn = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }
}
