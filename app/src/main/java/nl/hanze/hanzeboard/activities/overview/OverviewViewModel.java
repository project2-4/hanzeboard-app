package nl.hanze.hanzeboard.activities.overview;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewViewModel extends ViewModel {
    private MutableLiveData<UserResponse> mUser = new MutableLiveData<>();
    private UserClient userClient;

    public void init(Context context) {
        userClient = API.createService(context, UserClient.class);
        Call<UserResponse> userCall = userClient.me();

        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                mUser.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<UserResponse> getUser() {
        return mUser;
    }
}
