package edu.kiet.www.epoque2017.Models;

/**
 * Created by satyam on 2/10/17.
 */

import java.util.List;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchedulePOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ScheduleDatumPOJO> data = null;

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

    public List<ScheduleDatumPOJO> getData() {
        return data;
    }

    public void setData(List<ScheduleDatumPOJO> data) {
        this.data = data;
    }

}