package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.CancelEventPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by satyam on 2/21/17.
 */
public interface CancelRequest {
    @GET("App_controller/cancel_event")
    Call<CancelEventPOJO> response(@Query("event_id") String event_id);
}
