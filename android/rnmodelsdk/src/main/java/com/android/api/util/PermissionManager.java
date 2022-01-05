package com.android.api.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PermissionManager {
    private static PermissionManager instance;
    private static final int REQUEST_CRITICAL_PERMISSIONS = 10;
    //用户所有权限
    private String[] permissions = null;
    private WeakReference<Activity> activity = null;
    private OnPermissionListener onPermissionListener;

    public static PermissionManager getInstance() {
        if (instance == null) {
            synchronized (PermissionManager.class){
                if (instance == null){
                    instance = new PermissionManager();
                }
            }
        }
        return instance;
    }

    public void requestPermissions(Activity mAvtivity){
        requestPermissions(mAvtivity,null);
    }

    public void requestPermissions(Activity mAvtivity,OnPermissionListener listener){
        this.activity = new WeakReference<>(mAvtivity);
        this.onPermissionListener = listener;
        getPermisson(mAvtivity);
    }

    //获取权限
    @TargetApi(Build.VERSION_CODES.M)
    private void getPermisson(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            //得到自己的包名
            String pkgName = pi.packageName;

            PackageInfo pkgInfo = pm.getPackageInfo(pkgName, PackageManager.GET_PERMISSIONS);
            String sharedPkgList[] = pkgInfo.requestedPermissions;
            List<String> permissionLst = new ArrayList();
            if (sharedPkgList.length > 0)
                for (int i = 0; i < sharedPkgList.length; i++) {
                    String permName = sharedPkgList[i];
                    //判断是否有这个权限
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&activity.get().checkSelfPermission(permName)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (permName != null && permName.equalsIgnoreCase("android.permission.WRITE_SETTINGS")){
                            continue;
                        }else {
                            permissionLst.add(permName);
                        }
                    }
                }


            if (permissionLst.size() > 0) {
                permissions = new String[permissionLst.size()];
                for (int i = 0; i < permissionLst.size(); i++) {
                    permissions[i] = permissionLst.get(i);
                }
            }

            if (permissions != null && permissions.length > 0) {
                requestPermission();
            }
        } catch (Exception e) {
            Log.e("##ddd", "Could'nt retrieve permissions for package");
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.get().requestPermissions(permissions, REQUEST_CRITICAL_PERMISSIONS);
        }
    }

    /**
     * 在Activity中的onRequestPermissionsResult中调用
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return true 代表对该requestCode感兴趣，并已经处理掉了。false 对该requestCode不感兴趣，不处理。
     */
    public boolean requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        //是否全部权限已授权
        boolean isAllGranted = false;
        if(requestCode == REQUEST_CRITICAL_PERMISSIONS){
            isAllGranted = true;
            for(int result : grantResults){
                if (result != PackageManager.PERMISSION_DENIED) {
                    continue;
                }
                isAllGranted = false;
                break;
            }
            if(isAllGranted){
                //已全部授权
            }else{
                //权限有缺失
            }
        }
        if(onPermissionListener!=null){
            onPermissionListener.onPermission(isAllGranted);
        }
        return isAllGranted;
    }

    public interface OnPermissionListener{
        void onPermission(boolean granted);
    }
}
