package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.LoginPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by satyam on 2/10/17.
 */
public interface LoginRequest {
    @GET("App_controller/login_epk")
    Call<LoginPOJO> responseRequest(@Query("fcm_id") String fcm_id);
}
