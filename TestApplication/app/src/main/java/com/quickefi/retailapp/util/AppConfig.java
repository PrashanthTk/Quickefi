package com.quickefi.retailapp.util;

import android.Manifest;

public class AppConfig {


    //    REQUEST_CODE_RESULT
    public static final int REQUEST_CAMERA_IMAGE = 1;
    public static final int REQUEST_GALLERY_IMAGE = 2;


    //    PERMISSION REQUEST CODE
    public static final int PERMISSION_REQUEST_CAMERA_CODE = 11;
    public static final int PERMISSION_REQUEST_STORAGE_CODE = 13;


    //    PERMISSIONS
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

}
