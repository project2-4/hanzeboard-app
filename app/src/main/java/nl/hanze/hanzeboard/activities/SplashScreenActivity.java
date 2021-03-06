package nl.hanze.hanzeboard.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nl.hanze.hanzeboard.activities.overview.OverviewActivity;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Lifecycle method onCreate, sets the contentView of this class and initializes the variables/
     * properties necessary for this class a.k.a. in order for this class to function properly.
     *
     * @param savedInstanceState a bundle used to pass data between activities.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserClient userClient = API.createService(this, UserClient.class);

        Call<UserResponse> user = userClient.me();
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() != null && response.code() != 401) {
                    startActivity(new Intent(SplashScreenActivity.this, OverviewActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
