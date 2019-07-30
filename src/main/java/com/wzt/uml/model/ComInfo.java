package com.wzt.uml.model;

import java.util.Date;

public class ComInfo {
    private int comID;
    private String content;
    private String publisher;
    private Date time;
    private int articleID;

    public int getComID() {
        return comID;
    }

    public void setComID(int comID) {
        this.comID = comID;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }
}
