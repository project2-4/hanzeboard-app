package nl.hanze.hanzeboard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import nl.hanze.hanzeboard.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences tokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private void checkLogin() {
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
