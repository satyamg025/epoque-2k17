package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.EventDetailsPOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sooraj on 19-02-2017.
 */

public interface EventDetailsRequest {
    @GET("App_controller/event_details")
    Call<EventDetailsPOJO> request();
}
