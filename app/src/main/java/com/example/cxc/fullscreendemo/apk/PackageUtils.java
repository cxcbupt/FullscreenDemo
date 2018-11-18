package com.example.cxc.fullscreendemo.apk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

public class PackageUtils {
    private static final String TAG = "PackageUtils";

    public static final String FILE_PROVIDER_AUTHORITY = "com.example.cxc.fullscreendemo.fileProvider";


    public static void onInstallApkBtnClick(final Context context, final String localAPKPath) {
        File apkFile = new File(localAPKPath);
        if (apkFile != null && apkFile.exists()) {
            Uri apkUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                apkUri = FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, apkFile);
            } else {
//            chmodApk(localAPKPath);
                apkUri = Uri.fromFile(new File(localAPKPath));
            }

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(intent);
        }
    }

    /**
     * 获取程序在手机内存中的文件根目录
     */
    public static String getFilePath(Context context) {
        return context != null ? context.getApplicationContext().getFilesDir().getAbsolutePath() : null;
    }

    public static void onApkDownLoadCompleted(Context context, String localApkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri contentUri = FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, new File(localApkPath));
        Log.i(TAG, "download complete, contentUri: " + contentUri + ", localApkPath: " + localApkPath);
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
    }
}
