package com.li.vhr.model;

import java.util.Date;

public class ChatMsg {
    //消息从哪里来
    private String from;

    //消息去向哪里
    private String to;

    //消息的内容
    private String content;

    //消息的日期
    private Date date;

    private String fromNickname;

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
