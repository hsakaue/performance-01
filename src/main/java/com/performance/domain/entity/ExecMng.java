package com.performance.domain.entity;

import java.sql.Timestamp;

public class ExecMng {

    private Timestamp startTime;
    private Timestamp endTime;
    
    public Timestamp getStartTime() {
        return startTime;
    }
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    public Timestamp getEndTime() {
        return endTime;
    }
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
