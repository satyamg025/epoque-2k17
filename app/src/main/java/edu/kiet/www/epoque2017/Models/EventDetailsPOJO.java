package edu.kiet.www.epoque2017.Models;

/**
 * Created by sooraj on 19-02-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsPOJO {
    @SerializedName("img")
    @Expose
    private List<String> img = new ArrayList<>();
    @SerializedName("event_name")
    @Expose
    private List<String> eventName = new ArrayList<>();
    @SerializedName("event_id")
    @Expose
    private List<String> eventId = new ArrayList<>();
    @SerializedName("short_name")
    @Expose
    private List<String> shortName = new ArrayList<>();
    @SerializedName("type")
    @Expose
    private List<String> type = new ArrayList<>();
    @SerializedName("category")
    @Expose
    private List<String> category = new ArrayList<>();
    @SerializedName("subcategory")
    @Expose
    private List<String> subcategory = new ArrayList<>();
    @SerializedName("tagline")
    @Expose
    private List<String> tagline = new ArrayList<>();
    @SerializedName("min_participation")
    @Expose
    private List<String> minParticipation = new ArrayList<>();
    @SerializedName("max_participation")
    @Expose
    private List<String> maxParticipation = new ArrayList<>();
    @SerializedName("description")
    @Expose
    private List<String> description = new ArrayList<>();
    @SerializedName("time")
    @Expose
    private List<String> time = new ArrayList<>();
    @SerializedName("result_type")
    @Expose
    private List<String> resultType = new ArrayList<>();
    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("reg_closed")
    @Expose
    private Boolean reg_closed;

    @SerializedName("message")
    @Expose
    private String message;

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
    }

    public List<String> getEventId() {
        return eventId;
    }

    public void setEventId(List<String> eventId) {
        this.eventId = eventId;
    }

    public List<String> getShortName() {
        return shortName;
    }

    public void setShortName(List<String> shortName) {
        this.shortName = shortName;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    public List<String> getTagline() {
        return tagline;
    }

    public void setTagline(List<String> tagline) {
        this.tagline = tagline;
    }

    public List<String> getMinParticipation() {
        return minParticipation;
    }

    public void setMinParticipation(List<String> minParticipation) {
        this.minParticipation = minParticipation;
    }

    public List<String> getMaxParticipation() {
        return maxParticipation;
    }

    public void setMaxParticipation(List<String> maxParticipation) {
        this.maxParticipation = maxParticipation;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getResultType() {
        return resultType;
    }

    public void setResultType(List<String> resultType) {
        this.resultType = resultType;
    }

    public Boolean getReg_closed() {
        return reg_closed;
    }

    public void setReg_closed(Boolean reg_closed) {
        this.reg_closed = reg_closed;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}