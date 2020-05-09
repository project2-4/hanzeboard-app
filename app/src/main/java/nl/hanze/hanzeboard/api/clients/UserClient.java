package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.requests.LoginRequest;
import nl.hanze.hanzeboard.api.responses.LoginResponse;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Tag;

public interface UserClient {

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest login);

    @POST("auth/me")
    Call<UserResponse> me();

}
