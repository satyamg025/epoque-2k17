package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.ChangeRequestPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by satyam on 2/20/17.
 */
public interface ChangeRequest {
    @GET("App_controller/change_request")
    Call<ChangeRequestPOJO> requestResponse(@Query("change_from") String change_from,@Query("change_to") String change_to,@Query("event_id") String event_id);
}
