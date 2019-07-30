package com.wzt.uml.model;

import java.util.Date;

public class Arrangement {
    private int arrangeID;
    private Date time;
    private String content;
    private String publisher;

    public int getArrangeID() {
        return arrangeID;
    }

    public void setArrangeID(int arrangeID) {
        this.arrangeID = arrangeID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
