package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyam on 2/20/17.
 */
public class RequestReceivedDataumPOJO {
    @SerializedName("invited_by")
    @Expose
    private List<String> invitedBy = null;
    @SerializedName("event_img")
    @Expose
    private List<String> eventImg = null;
    @SerializedName("event_name")
    @Expose
    private List<String> eventName = null;
    @SerializedName("status")
    @Expose
    private List<String> status = null;
    @SerializedName("invite_id")
    @Expose
    private List<String> inviteId = null;
    @SerializedName("reg_closed")
    @Expose
    private Boolean reg_closed;

    public List<String> getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(List<String> invitedBy) {
        this.invitedBy = invitedBy;
    }

    public List<String> getEventImg() {
        return eventImg;
    }

    public void setEventImg(List<String> eventImg) {
        this.eventImg = eventImg;
    }

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getInviteId() {
        return inviteId;
    }

    public void setInviteId(List<String> inviteId) {
        this.inviteId = inviteId;
    }

    public Boolean getReg_closed() {
        return reg_closed;
    }

    public void setReg_closed(Boolean reg_closed) {
        this.reg_closed = reg_closed;
    }




}
