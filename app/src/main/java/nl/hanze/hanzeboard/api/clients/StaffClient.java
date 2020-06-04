package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.responses.staff.StaffMessageResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StaffClient {

    @GET("staff/me")
    Call<StaffMessageResponse> getStaffMessage();
}
