package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.SchedulePOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by satyam on 2/11/17.
 */
public interface ScheduleBearer {
    @GET("App_controller/schedule_bearer")
    Call<SchedulePOJO> requestResponse();
}
