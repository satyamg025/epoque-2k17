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
    @SerializedName("event_id")
    @Expose
    private List<String> eventId = null;
    @SerializedName("team_leader_bool")
    @Expose
    private List<Boolean> teamLeaderBool = null;
    @SerializedName("update")
    @Expose
    private Boolean update;
    @SerializedName("service")
    @Expose
    private Boolean service;
    @SerializedName("epk_2k18")
    @Expose
    private Boolean epk_2k18;
    @SerializedName("reg_closed")
    @Expose
    private Boolean reg_closed;
    @SerializedName("spon_img")
    @Expose
    private List<String> sponImg = null;

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

    public List<String> getEventId() {
        return eventId;
    }

    public void setEventId(List<String> eventId) {
        this.eventId = eventId;
    }

    public List<Boolean> getTeamLeaderBool() {
        return teamLeaderBool;
    }

    public void setTeamLeaderBool(List<Boolean> teamLeaderBool) {
        this.teamLeaderBool = teamLeaderBool;
    }

    public Boolean getEpk_2k18() {
        return epk_2k18;
    }

    public void setEpk_2k18(Boolean epk_2k18) {
        this.epk_2k18 = epk_2k18;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public List<String> getSponImg() {
        return sponImg;
    }

    public void setSponImg(List<String> sponImg) {
        this.sponImg = sponImg;
    }

    public Boolean getReg_closed() {
        return reg_closed;
    }

    public void setReg_closed(Boolean reg_closed) {
        this.reg_closed = reg_closed;
    }



}
