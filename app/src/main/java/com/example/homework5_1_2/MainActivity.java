package com.example.homework5_1_2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
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
    Bitmap image;
    EditText imageAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button change = (Button) findViewById(R.id.changeBtn);
        Button imageChanger = (Button) findViewById(R.id.imageBtn);
        imageSetter = (LinearLayout) findViewById(R.id.imageSetter);
        background = (ImageView) findViewById(R.id.background);
        imageAdress = (EditText) findViewById(R.id.imageAdress);
        imageChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSetter.setVisibility(View.VISIBLE);
            }
        });
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        Button okBtn = (Button) findViewById(R.id.okBtn);
        Button choiseBtn = (Button) findViewById(R.id.choiseBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });
        choiseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiseImage();
                clearForm();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(imageAdress.getText().toString());
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

        File imgFile = new File(adress);

        if(imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            background.setImageBitmap(myBitmap);

        } else {
            Toast.makeText(MainActivity.this,
                    "Файл не найден",
                    Toast.LENGTH_SHORT).show();
        }



    }

    public void choiseImage(){
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        final int ACTIVITY_SELECT_IMAGE = 1234;
        startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    image = BitmapFactory.decodeFile(filePath);
                    background.setImageBitmap(image);

                }
        }

    };

    private void clearForm(){
        imageSetter.setVisibility(View.GONE);

    }
}
