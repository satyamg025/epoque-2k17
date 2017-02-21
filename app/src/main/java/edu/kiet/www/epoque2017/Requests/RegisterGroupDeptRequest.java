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

public interface RegisterGroupDeptRequest {
    @FormUrlEncoded
    @POST("App_controller/group_event_dept")
    Call<RegisterCancelPOJO> request(@Field("team_name") String team_name, @Field("event_id") String event_id, @Field("members") String members);

}
