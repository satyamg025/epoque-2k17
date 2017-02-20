package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyam on 2/10/17.
 */
public class ScheduleDatumPOJO {

    @SerializedName("event_name")
    @Expose
    private List<String> eventName = null;
    @SerializedName("start_time")
    @Expose
    private List<String> startTime = null;
    @SerializedName("end_time")
    @Expose
    private List<String> endTime = null;
    @SerializedName("place")
    @Expose
    private List<String> place = null;
    @SerializedName("date")
    @Expose
    private List<String> date = null;
    @SerializedName("type")
    @Expose
    private List<String> type = null;

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public void setStartTime(List<String> startTime) {
        this.startTime = startTime;
    }

    public List<String> getEndTime() {
        return endTime;
    }

    public void setEndTime(List<String> endTime) {
        this.endTime = endTime;
    }

    public List<String> getPlace() {
        return place;
    }

    public void setPlace(List<String> place) {
        this.place = place;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

}