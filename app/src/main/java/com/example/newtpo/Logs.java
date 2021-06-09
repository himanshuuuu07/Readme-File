package com.example.newtpo;

public class Logs
{
    String updatedBy,time;

    public Logs() {
    }

    public Logs(String updatedBy, String time) {
        this.updatedBy = updatedBy;
        this.time = time;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
