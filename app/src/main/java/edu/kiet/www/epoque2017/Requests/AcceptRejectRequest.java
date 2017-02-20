package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.AcceptRejectPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by satyam on 2/20/17.
 */
public interface AcceptRejectRequest {
    @GET("App_controller/accept_reject")
    Call<AcceptRejectPOJO> responseRequest(@Query("inv_id") String inv_id,@Query("status") String status);

}
