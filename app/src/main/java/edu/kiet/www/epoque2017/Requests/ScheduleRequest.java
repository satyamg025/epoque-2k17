package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.SchedulePOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by satyam on 2/10/17.
 */
public interface ScheduleRequest {
    @GET("App_controller/schedule")
    Call<SchedulePOJO> requestResponse();
}
