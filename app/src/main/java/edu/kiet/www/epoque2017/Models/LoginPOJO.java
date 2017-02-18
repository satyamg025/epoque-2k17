package edu.kiet.www.epoque2017.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by satyam on 2/10/17.
 */
public class LoginPOJO {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("key")
    @Expose
    private String key;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

