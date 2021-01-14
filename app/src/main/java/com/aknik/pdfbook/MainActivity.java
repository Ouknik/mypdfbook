package com.aknik.pdfbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aknik.pdfbook.Upload;
import com.aknik.pdfbook.ViewUploadsActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    EditText editText , Name;
    Button button ;
    StorageReference storageReference ;
    DatabaseReference databaseReference ;
    private  static  int r9amta3tswira=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        Name=findViewById(R.id.Name);
        button=findViewById(R.id.btn);
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("aaa");
        button.setEnabled(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdf();
            }
        });

    }

    // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"),r9amta3tswira );
        Toast.makeText(MainActivity.this,"hna oslnah",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if(requestCode==r9amta3tswira && requestCode==RESULT_OK ){
        button.setEnabled(true);
        editText.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPDF(data.getData());
            }

        });
        Toast.makeText(MainActivity.this,"dkhlt ga3 hna",Toast.LENGTH_SHORT).show();
        // }
    }


    private void uploadPDF(Uri data) {
        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("File is loding ... ");
        progressDialog.show();
        StorageReference storageReferenc = storageReference.child("upload"+System.currentTimeMillis()+".pdf");
        storageReferenc.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri =uriTask.getResult();

                        Upload putPDF = new Upload(Name.getText().toString(),uri.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                        Toast.makeText(MainActivity.this,"fille upload",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double prog =(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("file uploaded"+(int) prog+"%");
            }
        });
    }

    public void go(View view) {
        Intent intent =new Intent(MainActivity.this, DeleteBook.class);
        startActivity(intent);

    }
}