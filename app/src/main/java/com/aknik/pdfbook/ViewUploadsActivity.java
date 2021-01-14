package com.aknik.pdfbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUploadsActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    //the listview
    ListView listView;
EditText editText ;
    //search
    SearchView searchView ;
    Query query;
    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    //list to store uploads data
    List<Upload> uploadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        editText=findViewById(R.id.etSearch);
        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                Upload upload = uploadList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewUploadsActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("you wante to ");

                builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(upload.getUrl()));
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Read", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                        Intent intent = new Intent(ViewUploadsActivity.this, MainActivity2.class);;
                        intent.putExtra("url", upload.getUrl());
                        startActivity(intent);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("aaa");
        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }
                String[] uploads = new String[uploadList.size()];
                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getName();
                }
                //displaying it to list
                //Adapter adapter ;
                 adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                 listView.setAdapter(adapter);
               // Adapter contactAdabter = new Adapter ( this , R.layout.activity_adapter , uploadList );
               // listView.setAdapter ( contactAdabter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.getFilter().filter(s);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.bottom_nav_menu , menu );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId ()) {

            case R.id.Login:

                Intent intent = new Intent(ViewUploadsActivity.this, Login.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected ( item );
    }
}