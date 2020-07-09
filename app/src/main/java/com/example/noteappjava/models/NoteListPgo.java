package com.example.noteappjava.models;

public class NoteListPgo {
    String id;
    String notes_title;
    String notes_pdf;


    public NoteListPgo(String id, String notes_title, String notes_pdf) {
        this.id = id;
        this.notes_title = notes_title;
        this.notes_pdf = notes_pdf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes_title() {
        return notes_title;
    }

    public void setNotes_title(String notes_title) {
        this.notes_title = notes_title;
    }

    public String getNotes_pdf() {
        return notes_pdf;
    }

    public void setNotes_pdf(String notes_pdf) {
        this.notes_pdf = notes_pdf;
    }
}
