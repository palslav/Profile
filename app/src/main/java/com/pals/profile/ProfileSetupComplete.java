package com.pals.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileSetupComplete extends AppCompatActivity {

    Button bExitApp;
    TextView tvProfileComplete;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setup_complete);

        bExitApp = (Button) findViewById(R.id.b_exit);
        tvProfileComplete = (TextView) findViewById(R.id.tv_setup_complete);

        dbHandler = new DatabaseHandler(this, null, null, 1);
        String dbString = dbHandler.databasetoString();
        // To View all the fields of Database
        //tvProfileComplete.setText(dbString);

        bExitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        super.onBackPressed();

        finish();
    }
}
