package com.pals.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etAddress, etLicense;
    ImageView ivProfilePic;
    ImageButton ibAddPic, ibNext;
    RadioGroup rgGender;

    DatabaseHandler dbHandler;
    byte imageInByte[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

        etName = (EditText) findViewById(R.id.et_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        etLicense = (EditText) findViewById(R.id.et_license);

        ivProfilePic = (ImageView) findViewById(R.id.iv_profile_pic);
        ibAddPic = (ImageButton) findViewById(R.id.ib_add_pic);
        ibNext = (ImageButton) findViewById(R.id.ib_next);

        rgGender = (RadioGroup) findViewById(R.id.radio_gender);

        dbHandler = new DatabaseHandler(this, null, null, 1);

        ibAddPic.setOnClickListener(this);
        ibNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.ib_add_pic){
            onAddPicButtonClicked();
        } else if(view.getId()==R.id.ib_next){
            onNextButtonClicked();
        }
    }

    private void onAddPicButtonClicked() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            String imgPath = data.getStringExtra("CameraImagePath");
            Bitmap bitmap = data.getParcelableExtra("data");
            Bitmap profileImage = GetBitmapClippedCircle(bitmap);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profileImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            imageInByte = stream.toByteArray();

            ivProfilePic.setImageBitmap(profileImage);
            //profileImage.recycle();
        } else if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            etLicense.setText("");
            etAddress.setText("");
            etName.setText("");
            ivProfilePic.setImageDrawable(this.getDrawable(R.drawable.default_profile_pic));
        }
    }

    public static Bitmap GetBitmapClippedCircle(Bitmap bitmap) {

        Matrix mat = new Matrix();
        mat.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap , 0, 0, bitmap.getWidth(), bitmap.getHeight(), mat, true);
        final Bitmap outputBitmap = Bitmap.createBitmap(rotatedBitmap.getWidth(), rotatedBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        final Path path = new Path();
        path.addCircle(
                (float)(rotatedBitmap.getWidth() / 2)
                , (float)(rotatedBitmap.getHeight() / 2)
                , (float) Math.min(rotatedBitmap.getWidth(), (rotatedBitmap.getHeight() / 2))
                , Path.Direction.CCW);

        final Canvas canvas = new Canvas(outputBitmap);
        canvas.clipPath(path);
        canvas.drawBitmap(rotatedBitmap, 0, 0, null);
        return outputBitmap;
    }

    private void onNextButtonClicked() {

        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String lisence = etLicense.getText().toString();
        String imagePath = "default_path";
        int selectedGender = rgGender.getCheckedRadioButtonId();
        RadioButton rb =  (findViewById(selectedGender));
        String gender = "Male";
        if (rb != null)  {
             gender = rb.getText().toString();
        }
        /*if (name.isEmpty())  {
            etName.setHint("Please Enter Valid name");
            return;
        }
        if (address.isEmpty())  {
            etAddress.setHint("Please Enter Valid Address");
            return;
        }
        if (lisence.isEmpty())  {
            etLicense.setHint("Please Enter Valid Lisence");
            return;
        }*/
        Users user = new Users(name, address, lisence, gender, imageInByte);
        dbHandler.addUser(user);

        Intent nextIntent = new Intent(this, ProfileSetupComplete.class);
        startActivityForResult(nextIntent, 1001);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_db_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.item_show_db){
            Intent dbMenuIntent = new Intent(getApplicationContext(), Database.class);
            startActivity(dbMenuIntent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}
