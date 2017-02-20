package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.RequestSentPOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by satyam on 2/20/17.
 */
public interface RequestSentRequest {
    @GET("App_controller/request_sent")
    Call<RequestSentPOJO> responseRequest();
}
