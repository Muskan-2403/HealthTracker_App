package com.ultimate.infits;

import java.io.Serializable;

public class ChatLogList implements Serializable {

    private String profile_pic;
    private String client_name;
    private String client_msg;
    private String client_time;
    private String read;

    public ChatLogList(String profile_pic, String client_name, String client_msg, String client_time, String read) {
        this.profile_pic = profile_pic;
        this.client_name = client_name;
        this.client_msg = client_msg;
        this.client_time = client_time;
        this.read = read;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_msg() {
        return client_msg;
    }

    public String getClient_time() {
        return client_time;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
}