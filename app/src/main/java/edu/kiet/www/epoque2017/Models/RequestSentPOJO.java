package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyam on 2/20/17.
 */
public class RequestSentPOJO {
    @SerializedName("data")
    @Expose
    private List<RequestSentDataumPOJO> data = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public List<RequestSentDataumPOJO> getData() {
        return data;
    }

    public void setData(List<RequestSentDataumPOJO> data) {
        this.data = data;
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
