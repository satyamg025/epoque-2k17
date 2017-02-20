package edu.kiet.www.epoque2017.Models;

/**
 * Created by sooraj on 19-02-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsPOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("event_name")
    @Expose
    private List<String> eventName = new ArrayList<>();
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
    @SerializedName("img_url")
    @Expose
    private List<String> imgUrl = new ArrayList<>();
    @SerializedName("apex_stu_name")
    @Expose
    private List<String> apexStuName = new ArrayList<>();
    @SerializedName("apex_stu_phone")
    @Expose
    private List<String> apexStuPhone = new ArrayList<>();
    @SerializedName("apex_fac_name")
    @Expose
    private List<String> apexFacName = new ArrayList<>();
    @SerializedName("apex_fac_phone")
    @Expose
    private List<String> apexFacPhone = new ArrayList<>();
    @SerializedName("apex_fac_dept")
    @Expose
    private List<String> apexFacDept = new ArrayList<>();

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

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
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

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getApexStuName() {
        return apexStuName;
    }

    public void setApexStuName(List<String> apexStuName) {
        this.apexStuName = apexStuName;
    }

    public List<String> getApexStuPhone() {
        return apexStuPhone;
    }

    public void setApexStuPhone(List<String> apexStuPhone) {
        this.apexStuPhone = apexStuPhone;
    }

    public List<String> getApexFacName() {
        return apexFacName;
    }

    public void setApexFacName(List<String> apexFacName) {
        this.apexFacName = apexFacName;
    }

    public List<String> getApexFacPhone() {
        return apexFacPhone;
    }

    public void setApexFacPhone(List<String> apexFacPhone) {
        this.apexFacPhone = apexFacPhone;
    }

    public List<String> getApexFacDept() {
        return apexFacDept;
    }

    public void setApexFacDept(List<String> apexFacDept) {
        this.apexFacDept = apexFacDept;
    }

}