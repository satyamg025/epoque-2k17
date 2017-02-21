package edu.kiet.www.epoque2017.Requests;

import edu.kiet.www.epoque2017.Models.ResultPOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by satyam on 2/20/17.
 */
public interface ResultRequest {
    @GET("App_controller/result")
    Call<ResultPOJO> response();
}
