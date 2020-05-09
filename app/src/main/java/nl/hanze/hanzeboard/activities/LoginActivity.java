package nl.hanze.hanzeboard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.requests.LoginRequest;
import nl.hanze.hanzeboard.api.responses.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserClient userClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userClient = API.createService(this, UserClient.class);

        loginButton = findViewById(R.id.loginButton);
        emailEditText = findViewById(R.id.emailText);
        passwordEditText = findViewById(R.id.passwordText);

        loginButton.setOnClickListener(this::checkLogin);
    }

    private void checkLogin(View v) {
        LoginRequest loginRequest = new LoginRequest(emailEditText.getText().toString(), passwordEditText.getText().toString());

        loginButton.setEnabled(false);

        Call<LoginResponse> loginResponseCall = userClient.login(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.body() != null) {
                    Toast.makeText(LoginActivity.this, "Succesvol ingelogd", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, OverviewActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Inloggegevens niet correct!", Toast.LENGTH_LONG).show();
                    loginButton.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                loginButton.setEnabled(true);
            }
        });
    }
}
