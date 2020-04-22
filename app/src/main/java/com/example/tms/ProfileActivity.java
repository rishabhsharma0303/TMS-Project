package com.example.tms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
Button logout;
TextView logout_tv;
TextView name;
SharedPreferences sharedPreferences;
ImageView circleImageView;
Dialog customDialog;
LinearLayout camera,photos;
  private StorageReference mStorageRef;
  private String cameraFilePath;



    private Uri imageAddress;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int SELECT_PHOTO = 100;
    //private StorageReference mStorageRef;
    private static final int CAMERA_PIC_REQUEST = 1337;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView)findViewById(R.id.name);
        logout_tv=(TextView)findViewById(R.id.log_out);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        circleImageView=(ImageView)findViewById(R.id.profile_image);
        sharedPreferences = getSharedPreferences("Myprf", MODE_PRIVATE);
        String userName=sharedPreferences.getString(Constants.USERNAME, "Default_email");
        name.setText(userName);

        customDialog=new Dialog(ProfileActivity.this);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCancelable(false);
        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        customDialog.setCanceledOnTouchOutside(true);


        logout_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("Myprf", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(Constants.LOGINSTATUS, "f");
                editor.commit();

                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
                finish();
            }
        });
       //loadWithGlide();
        /*circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
customDialog.show();
camera=customDialog.findViewById(R.id.camera);
photos=customDialog.findViewById(R.id.photo);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();

            }
        });
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });*/
       // logout=(Button)findViewById(R.id.logout_btn);
       /* logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences settings = getSharedPreferences("Myprf", MODE_PRIVATE);
               SharedPreferences.Editor editor = settings.edit();
               // editor.remove("logged");
              //  editor.clear();
               // editor.commit();
               ;

                editor.putString(Constants.LOGINSTATUS, "f");
                editor.commit();

                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
                finish();
            }
        });*/
    }

    private void loadWithGlide() {
        mStorageRef=FirebaseStorage.getInstance().getReference("images/");
        // ImageView in your Activity
       // ImageView imageView = findViewById(R.id.showimag);

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        Glide.with(this /* context */)
                .load(mStorageRef)
                .into(circleImageView);
    }

    public void choose(View view) {
        customDialog.show();
        camera=customDialog.findViewById(R.id.camera);
        photos=customDialog.findViewById(R.id.photo);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                openCamera();
            }
        });
//camera.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//
//            openCamera();
//
//        //  Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//       // startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
//    }
//});
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                openGallery();
            }
        });

    }

    private void openCamera()  {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, CAMERA_PIC_REQUEST);

    }


    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();
                    imageAddress=selectedImage;
                    imageInsertion();


                    if (selectedImage != null) {
                      //  circleImageView.setImageURI(selectedImage);
                        loadWithGlide();
                       // imageInsertion();
                    }

                }
           case CAMERA_PIC_REQUEST:

//                circleImageView.setImageBitmap(bitmap);
               if(requestCode == RESULT_OK){
                   Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                   circleImageView.setImageBitmap(bitmap);
                   //imageInsertion();
               }
        }
    }

    private void imageInsertion() {
        Uri test=imageAddress;
        String t=null;
        if(imageAddress!=null){
            StorageReference ref=mStorageRef.child("images/"+imageAddress.getLastPathSegment());

            //  StorageReference ref=mStorageRef.child("Your Path" + "/" + "Image Name" + ".jpg");
            ref.putFile(imageAddress)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(ProfileActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                            //Picasso.get().load().into();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileActivity.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {
            Toast.makeText(ProfileActivity.this,"null",Toast.LENGTH_SHORT).show();
        }
    }


}
