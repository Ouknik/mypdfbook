package com.aknik.pdfbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InterfacePrograme extends AppCompatActivity {
Button the_book;
Button riwayati ;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_programe);
        the_book=findViewById(R.id.the_Book);
        riwayati=findViewById(R.id.riwayati);
        imageView=findViewById(R.id.image);
    }
    public void the_book(View view) {
        Intent intent = new Intent(InterfacePrograme.this,ViewUploadsActivity.class);
        startActivity(intent);
        finish();
    }
    public void riwayati(View view) {
        Intent intent = new Intent(InterfacePrograme.this,MySite.class);
        startActivity(intent);
        finish();
    }

}