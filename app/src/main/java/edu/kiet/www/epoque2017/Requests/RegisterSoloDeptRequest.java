package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.RegisterCancelPOJO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sooraj on 21-02-2017.
 */

public interface RegisterSoloDeptRequest {
    @FormUrlEncoded
    @POST("App_controller/solo_event_dept")
        Call<RegisterCancelPOJO> request(@Field("event_id") String event_id);
}
