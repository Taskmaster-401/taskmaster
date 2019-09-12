package com.marishaoza.taskmaster.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.sql.Timestamp;
import java.util.Date;

@DynamoDBDocument
public class HistoryObj {
    private String time;
    private String action;

    public HistoryObj(){}

    public HistoryObj(String action){
        this.action = action;
        Date date = new Date();
        this.time = new Timestamp(date.getTime()).toString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String toString() {
        return String.format("%s @ %s", this.getAction(), this.getTime());
    }
}
