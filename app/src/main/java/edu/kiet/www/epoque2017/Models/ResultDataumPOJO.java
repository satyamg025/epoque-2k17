package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyam on 2/20/17.
 */
public class ResultDataumPOJO {
    @SerializedName("event_winner_name")
    @Expose
    private List<List<String>> eventWinnerName = null;
    @SerializedName("event_name")
    @Expose
    private List<String> eventName = null;

    public List<List<String>> getEventWinnerName() {
        return eventWinnerName;
    }

    public void setEventWinnerName(List<List<String>> eventWinnerName) {
        this.eventWinnerName = eventWinnerName;
    }

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
    }
}
