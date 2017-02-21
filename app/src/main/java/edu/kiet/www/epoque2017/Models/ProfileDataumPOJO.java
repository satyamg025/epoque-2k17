package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyam on 2/21/17.
 */
public class ProfileDataumPOJO {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("event_category")
    @Expose
    private List<String> eventCategory = null;
    @SerializedName("event_type")
    @Expose
    private List<String> eventType = null;
    @SerializedName("event_name")
    @Expose
    private List<String> eventName = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(List<String> eventCategory) {
        this.eventCategory = eventCategory;
    }

    public List<String> getEventType() {
        return eventType;
    }

    public void setEventType(List<String> eventType) {
        this.eventType = eventType;
    }

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
    }

}
