package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.responses.staff.StaffMessageResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StaffClient {

    @GET("courses/1/staff")
    Call<StaffMessageResponse> getStaffMessage();
}
