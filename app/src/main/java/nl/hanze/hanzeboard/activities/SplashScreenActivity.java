package nl.hanze.hanzeboard.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.requests.LoginRequest;
import nl.hanze.hanzeboard.api.responses.LoginResponse;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       UserClient userClient = API.createService(this, UserClient.class);
//        LoginRequest login = new LoginRequest("dylan@hanzeboard.nl", "hanzeboard");
//
//        Call<LoginResponse> loginResponseCall = userClient.login(login);
//        loginResponseCall.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                if(response.body() != null) {
//                    Toast.makeText(SplashScreenActivity.this, "Login success: " + response.body().getAccessToken(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(SplashScreenActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


        Call<UserResponse> user =  userClient.me();
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                System.out.println("x");
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                System.out.println("y");
            }
        });

//        startActivity(new Intent(this, MainActivity.class));
//        finish();
    }
}
