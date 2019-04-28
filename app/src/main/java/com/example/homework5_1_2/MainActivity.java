package com.example.homework5_1_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout imageSetter;
    ImageView background;

    EditText imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (!(permissionStatus == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button change = findViewById(R.id.changeBtn);
        Button imageChanger = findViewById(R.id.imageBtn);
        imageSetter = findViewById(R.id.imageSetter);
        background = findViewById(R.id.background);
        imageName = findViewById(R.id.imageAdress);
        imageChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSetter.setVisibility(View.VISIBLE);
            }
        });
        Button cancelBtn = findViewById(R.id.cancelBtn);
        Button okBtn = findViewById(R.id.okBtn);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(imageName.getText().toString());
                clearForm();
            }
        });
        change.setOnClickListener(this);
        NumerCreator numerCreator = new NumerCreator(
                (TextView) findViewById(R.id.text),
                (Button) findViewById(R.id.btn0),
                (Button) findViewById(R.id.btn1),
                (Button) findViewById(R.id.btn2),
                (Button) findViewById(R.id.btn3),
                (Button) findViewById(R.id.btn4),
                (Button) findViewById(R.id.btn5),
                (Button) findViewById(R.id.btn6),
                (Button) findViewById(R.id.btn7),
                (Button) findViewById(R.id.btn8),
                (Button) findViewById(R.id.btn9),
                (Button) findViewById(R.id.btndot),
                (Button) findViewById(R.id.btnsign),
                (Button) findViewById(R.id.btnC));

        Calculate calculate = new Calculate(
                numerCreator,
                (Button) findViewById(R.id.btnMinus),
                (Button) findViewById(R.id.btnMulty),
                (Button) findViewById(R.id.btnpercent),
                (Button) findViewById(R.id.btnPlus),
                (Button) findViewById(R.id.btnDel),
                (Button) findViewById(R.id.btnEqual));

        NumerCreator numerCreatorEngenier = new NumerCreator(
                (TextView) findViewById(R.id.text),
                (Button) findViewById(R.id.newBtn0),
                (Button) findViewById(R.id.newBtn1),
                (Button) findViewById(R.id.newBtn2),
                (Button) findViewById(R.id.newBtn3),
                (Button) findViewById(R.id.newBtn4),
                (Button) findViewById(R.id.newBtn5),
                (Button) findViewById(R.id.newBtn6),
                (Button) findViewById(R.id.newBtn7),
                (Button) findViewById(R.id.newBtn8),
                (Button) findViewById(R.id.newBtn9),
                (Button) findViewById(R.id.newBtndot),
                (Button) findViewById(R.id.newBtnsign),
                (Button) findViewById(R.id.newBtnC));

        Calculate calculateEngenier = new Calculate(
                numerCreatorEngenier,
                (Button) findViewById(R.id.newBtnMinus),
                (Button) findViewById(R.id.newBtnMulty),
                (Button) findViewById(R.id.newBtnpercent),
                (Button) findViewById(R.id.newBtnPlus),
                (Button) findViewById(R.id.newBtnDel),
                (Button) findViewById(R.id.newBtnEqual));

    }

    @Override
    public void onClick(View v) {

        if(findViewById(R.id.common).getVisibility() == View.GONE){
            findViewById(R.id.common).setVisibility(View.VISIBLE);
            findViewById(R.id.engenier).setVisibility(View.GONE);
        } else {
            findViewById(R.id.common).setVisibility(View.GONE);
            findViewById(R.id.engenier).setVisibility(View.VISIBLE);
        }

    }

    public void changeImage(String adress){

        File imgFile = getImage(adress);

        if(imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            background.setImageBitmap(myBitmap);

        } else {
            Toast.makeText(MainActivity.this,
                    "Файл не найден",
                    Toast.LENGTH_SHORT).show();
        }



    }

    public File getImage(String imageName){
        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), imageName);
        return image;
    }



    private void clearForm(){
        imageSetter.setVisibility(View.GONE);

    }
}
