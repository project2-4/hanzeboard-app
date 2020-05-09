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

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        SharedPreferences tokens = getApplicationContext().getSharedPreferences("tokens", Context.MODE_PRIVATE);

        TextView tokenTextField = findViewById(R.id.tokenTextView);
        tokenTextField.setText(tokens.getString(getString(R.string.key_jwt_token), "unknown"));

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener((View v) -> {
            tokens
                    .edit()
                    .remove(getResources().getString(R.string.key_jwt_token))
                    .apply();

            Intent loginIntent = new Intent(this, MainActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
        });
    }
}
