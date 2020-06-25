package nl.hanze.hanzeboard.activities.overview.profile;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.ViewModel;
import org.jetbrains.annotations.NotNull;
import java.io.File;

import nl.hanze.hanzeboard.activities.overview.OverviewViewModel;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.ProfileClient;
import nl.hanze.hanzeboard.api.responses.AvatarResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    private ProfileClient profileClient;
    private String avatarUrl;

    public void init(Context context) {
        profileClient = API.createService(context, ProfileClient.class);
    }

    /**
     * @param profileUrl
     * @return
     */
    public boolean setProfileUrl(Uri profileUrl, File avatar, ProfileFragment fragment, OverviewViewModel overviewViewModel) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), avatar);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", avatar.getName(), requestFile);
        Call<AvatarResponse> avatarCall = profileClient.avatar(body);

        avatarCall.enqueue(new Callback<AvatarResponse>() {
            @Override
            public void onResponse(@NotNull Call<AvatarResponse> call, @NotNull Response<AvatarResponse> response) {
                if (response.body() != null) {
                    avatarUrl = API.STORAGE_URL + response.body().getUser().getAvatarUrl();
                    fragment.setAvatar(avatarUrl);
                    overviewViewModel.updateAvatar(avatarUrl);
                }
            }

            @Override
            public void onFailure(@NotNull Call<AvatarResponse> call, @NotNull Throwable t) {
                System.out.println("FAILURE");
                System.out.println(t.getMessage());
            }
        });

        return false;
    }
}
