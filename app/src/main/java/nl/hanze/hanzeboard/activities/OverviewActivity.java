package nl.hanze.hanzeboard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewActivity extends AppCompatActivity {

    private UserClient userClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        TextView tokenTextView = findViewById(R.id.tokenTextView);
        userClient = API.createService(this, UserClient.class);

        Call<UserResponse> userCall = userClient.me();
        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.body() != null) {
                    UserResponse userResponse = response.body();
                    tokenTextView.setText(userResponse.getName());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                startActivity(new Intent(OverviewActivity.this, LoginActivity.class));
                finish();
            }
        });

        findViewById(R.id.logoutButton).setOnClickListener(this::logout);
    }

    private void logout(View v) {
        Call<ResponseBody> logoutCall = userClient.logout();

        logoutCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                startActivity(new Intent(OverviewActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                startActivity(new Intent(OverviewActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
