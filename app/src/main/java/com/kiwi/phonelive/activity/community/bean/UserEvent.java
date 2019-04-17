package com.kiwi.phonelive.activity.community.bean;

public class UserEvent {
    private String type;

    public String getType() {
        return type;
    }

    public UserEvent(String type, String satus) {
        this.type = type;
        this.satus = satus;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    private String satus;
}
