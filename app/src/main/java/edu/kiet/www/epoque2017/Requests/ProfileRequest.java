package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.ProfilePOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by satyam on 2/21/17.
 */
public interface ProfileRequest {
    @GET("App_controller/reg_events_v2")
    Call<ProfilePOJO> response();
}
