package com.pals.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePic extends AppCompatActivity{
    private ImageView ivSavedProfilePic;
    private TextView tvSavedProfileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_pic_layout);

        ivSavedProfilePic = (ImageView)findViewById(R.id.iv_saved_profile_pic);
        ivSavedProfilePic.setClickable(true);

        //finish the activity (dismiss the image dialog) if the user clicks
        //anywhere on the image
        ivSavedProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
