package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.responses.AvatarResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProfileClient {

    @Multipart
    @POST("students/avatar")
    Call<AvatarResponse> avatar(@Part MultipartBody.Part avatar);
}
