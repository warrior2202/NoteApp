package com.example.noteappjava.models;

public class SubjectListPogo {
    private String cat_id;
    private String subject_id;
    private String subject;


    public SubjectListPogo(String cat_id, String subject_id, String subject) {
        this.cat_id = cat_id;
        this.subject_id = subject_id;
        this.subject = subject;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
