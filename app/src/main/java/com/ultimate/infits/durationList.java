package com.ultimate.infits;

import java.io.Serializable;

public class durationList implements Serializable {
    private int time1;

    public durationList(int time) {
        this.time1 = time;
    }

    public int getTime() {
        return time1;
    }
}
