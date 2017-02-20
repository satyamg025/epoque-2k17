package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by satyam on 2/20/17.
 */
public class RequestReceivedPOJO {
    @SerializedName("data")
    @Expose
    private RequestReceivedDataumPOJO data;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public RequestReceivedDataumPOJO getData() {
        return data;
    }

    public void setData(RequestReceivedDataumPOJO data) {
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
