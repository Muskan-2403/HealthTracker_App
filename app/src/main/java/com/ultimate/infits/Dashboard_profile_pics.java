package com.ultimate.infits;

import java.io.Serializable;

public class Dashboard_profile_pics implements Serializable {

    private String dashboard_photos;

    public Dashboard_profile_pics(String dashboard_photos) {
        this.dashboard_photos = dashboard_photos;
    }

    public String getDashboard_photos() {
        return dashboard_photos;
    }

    public void setDashboard_photos(String dashboard_photos) {
        this.dashboard_photos = dashboard_photos;
    }
}
