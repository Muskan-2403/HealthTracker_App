package com.ultimate.infits;

import java.io.Serializable;

public class EventList implements Serializable {
    private String cal_apt_type;
    private String cal_apt_clientname;
    private String cal_apt_time;

    public EventList(String cal_apt_type, String cal_apt_clientname, String cal_apt_time) {
        this.cal_apt_type = cal_apt_type;
        this.cal_apt_clientname = cal_apt_clientname;
        this.cal_apt_time = cal_apt_time;
    }

    public String getCal_apt_type() {
        return cal_apt_type;
    }

    public String getCal_apt_clientname() {
        return cal_apt_clientname;
    }

    public String getCal_apt_time() {
        return cal_apt_time;
    }
}
