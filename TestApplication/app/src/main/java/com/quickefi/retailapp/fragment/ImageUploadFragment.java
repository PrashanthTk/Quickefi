package com.quickefi.retailapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.quickefi.retailapp.R;
import com.quickefi.retailapp.interfaces.AppPermissionListener;
import com.quickefi.retailapp.util.AppConfig;
import com.quickefi.retailapp.util.Util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ImageUploadFragment extends Fragment implements AppPermissionListener {

    private Context context;
    private Uri uriFilePath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Util.askPermissions(context, AppConfig.PERMISSION_REQUEST_CAMERA_CODE, ImageUploadFragment.this, false, AppConfig.PERMISSION_CAMERA);
                } else if (options[item].equals("Choose from Gallery")) {
                    Util.askPermissions(context, AppConfig.PERMISSION_REQUEST_STORAGE_CODE, ImageUploadFragment.this, false, AppConfig.PERMISSION_READ_EXTERNAL_STORAGE, AppConfig.PERMISSION_WRITE_EXTERNAL_STORAGE);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                    //Replace

                    Fragment fragment = new SearchFragment();

FragmentManager fm = getFragmentManager();
FragmentTransaction transaction = fm.beginTransaction();
fm.popBackStack();//(R.id.navigation_search, fragment);
//transaction.commit();

                }
            }

        });
        builder.show();
    }

    private void chooseImageFromCamera() {
        uriFilePath = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", new File(Environment.getExternalStorageDirectory().getPath(), "IMG_" + Calendar.getInstance().getTimeInMillis()));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFilePath);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, AppConfig.REQUEST_CAMERA_IMAGE);
    }

    private void chooseImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), AppConfig.REQUEST_GALLERY_IMAGE);
    }

    @Override
    public void onPermissionSuccess(int permissionCode) {
        switch (permissionCode) {
            case AppConfig.PERMISSION_REQUEST_CAMERA_CODE:
                chooseImageFromCamera();
                break;
            case AppConfig.PERMISSION_REQUEST_STORAGE_CODE:
                chooseImageFromGallery();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConfig.REQUEST_GALLERY_IMAGE) {
                getGalleryImage(data);
            }
            if (requestCode == AppConfig.REQUEST_CAMERA_IMAGE) {
                getCameraImage();
            }
        }
    }


    private void getGalleryImage(Intent data) {
        uploadImage(data.getData());
    }

    private void getCameraImage() {
        uploadImage(uriFilePath);
    }

    private void uploadImage(Uri uri) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();

        StorageReference storageReferenceChild = storageReference.child("images/" + UUID.randomUUID().toString());
        storageReferenceChild.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });

    }
}
