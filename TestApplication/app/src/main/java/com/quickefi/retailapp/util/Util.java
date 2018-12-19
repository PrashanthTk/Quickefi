package com.quickefi.retailapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.quickefi.retailapp.R;
import com.quickefi.retailapp.interfaces.AppPermissionListener;

import java.util.List;

public class Util {

    private static void openSettingsDialog(final Context context, final int REQUEST_CODE, final boolean isFinish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings(context, REQUEST_CODE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (isFinish)
                    ((AppCompatActivity) context).finish();
            }
        });
        builder.show();
    }

    private static void openSettings(Context context, int REQUEST_CODE) {
        Intent intentOpenSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uriOpenSettings = Uri.fromParts("package", context.getPackageName(), null);
        intentOpenSettings.setData(uriOpenSettings);
        ((AppCompatActivity) context).startActivityForResult(intentOpenSettings, REQUEST_CODE);
    }

    public static void askPermissions(final Context context, final int REQUEST_CODE, final AppPermissionListener appPermissionListener, final boolean isFinish, String... permissions) {
        Dexter.withActivity((AppCompatActivity) context).withPermissions(permissions).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    if (appPermissionListener != null)
                        appPermissionListener.onPermissionSuccess(REQUEST_CODE);
                }
                if (report.isAnyPermissionPermanentlyDenied()) {
                    openSettingsDialog(context, REQUEST_CODE, isFinish);
                }
                if (report.getDeniedPermissionResponses().size() > 0) {
                    openSettingsDialog(context, REQUEST_CODE, isFinish);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).onSameThread().check();
    }
    public static void getOrderfromString(String orderstring)
    {
        //Split string here
    }

}
