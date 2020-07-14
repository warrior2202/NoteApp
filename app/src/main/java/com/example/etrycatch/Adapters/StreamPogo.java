package com.example.etrycatch.Adapters;

import android.widget.TextView;

public class StreamPogo {
    private String id;
    private String cat_name;
    private String stream;
    private String stream_name;

    public StreamPogo(String id, String cat_name, String stream, String stream_name) {
        this.id = id;
        this.cat_name = cat_name;
        this.stream = stream;
        this.stream_name = stream_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getStream_name() {
        return stream_name;
    }

    public void setStream_name(String stream_name) {
        this.stream_name = stream_name;
    }
}
