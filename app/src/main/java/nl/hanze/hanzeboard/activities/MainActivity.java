package nl.hanze.hanzeboard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.conscrypt.Conscrypt;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.Security;
import java.util.Objects;

import nl.hanze.hanzeboard.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences tokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokens = getApplicationContext().getSharedPreferences("tokens", Context.MODE_PRIVATE);

        if(tokens.contains(getResources().getString(R.string.key_jwt_token))) {
            // JWT token already exists
            Intent overviewIntent = new Intent(this, OverviewActivity.class);
            overviewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(overviewIntent);
        }

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener((View v) -> {
            checkLogin();
        });
    }

    private void checkLogin() {
        Security.insertProviderAt(Conscrypt.newProvider(), 1);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://date.jsontest.com/")
                .build();

        client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d("MAIN", e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            Log.d("MAIN", Objects.requireNonNull(response.body()).string());
                        }
                    }
                }
            );

//        tokens
//                .edit()
//                .putString(getResources().getString(R.string.key_jwt_token), "token_hier")
//                .apply();
//
//        Intent overviewIntent = new Intent(this, OverviewActivity.class);
//        overviewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        startActivity(overviewIntent);
    }
}
