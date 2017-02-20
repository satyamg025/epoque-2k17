package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyam on 2/20/17.
 */
public class RequestSentDataumPOJO {
    @SerializedName("Team_leader")
    @Expose
    private String teamLeader;
    @SerializedName("Team_name")
    @Expose
    private String teamName;
    @SerializedName("invitation_to")
    @Expose
    private List<String> invitationTo = null;
    @SerializedName("status")
    @Expose
    private List<String> status = null;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("invitation_to_name")
    @Expose
    private List<String> invitationToName = null;
    @SerializedName("team_leader_name")
    @Expose
    private String teamLeaderName;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("team_leader_bool")
    @Expose
    private Boolean teamLeaderBool;

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<String> getInvitationTo() {
        return invitationTo;
    }

    public void setInvitationTo(List<String> invitationTo) {
        this.invitationTo = invitationTo;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getInvitationToName() {
        return invitationToName;
    }

    public void setInvitationToName(List<String> invitationToName) {
        this.invitationToName = invitationToName;
    }

    public String getTeamLeaderName() {
        return teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Boolean getTeamLeaderBool() {
        return teamLeaderBool;
    }

    public void setTeamLeaderBool(Boolean teamLeaderBool) {
        this.teamLeaderBool = teamLeaderBool;
    }
}
