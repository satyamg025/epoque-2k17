package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sooraj on 21-02-2017.
 */

public class RegisterCancelPOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("msg")
    @Expose
    private String message;

    public Boolean getError() {
        return error;
    }


    public String getMessage() {
        return message;
    }
    public String getStatus() {
        return status;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
