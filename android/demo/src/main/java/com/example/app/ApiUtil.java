package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApiUtil {

    //https://blog.csdn.net/weixin_33788424/article/details/117800333
    private static Map<String, Object> objects = new HashMap<>();
    private static String getClassName() {
        String model = android.os.Build.MODEL;
        model = model.trim();
        model = model.replaceAll(" ", "");
        model = model.toUpperCase();
        String classname = "com.android.api." + model + "Api";
        return classname;
    }

    public static void init(final Activity activity) {
        Log.i("uu_", "OMDeviceModule==init==========");
//        VolumeManager.getInstance().init(activity, new Handler());
//        ScreenBrightnessApi.getApi().allowModifySettings(activity);
        String classname = getClassName();
        if (classname == null) {
            Log.i("uu_", "没有找到目标类，初始化失败");
            return;
        }
        try {
            Object object = objects.get(classname.toUpperCase());
            if (object == null) {
                Class<?> clasz = Class.forName(classname);
                if (clasz != null) {
                    Constructor<?> con = clasz.getConstructor(Activity.class);
                    object = con.newInstance(activity);
                    objects.put(classname.toUpperCase(), object);
                }else{
                    Log.i("uu_", "0初始化失败"+object);
                }
            }
        } catch (InstantiationException e) {
            Log.i("uu_", "1初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.i("uu_", "2初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.i("uu_", "3初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.i("uu_", "4初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.i("uu_", "5初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        String classname = getClassName();
        if (classname == null)
            return;
        try {
            Object object = objects.get(classname.toUpperCase());
            if (object == null) {
                Log.i("uu_", "onRequestPermissionsResult 失败，object is null");
                return;
            }

            Method method = object.getClass().getMethod("onRequestPermissionsResult", int.class,String[].class,int[].class);
            if (method == null) {
                Log.i("uu_", "onRequestPermissionsResult 失败，method is null");
                return;
            }
            Object result = method.invoke(object, requestCode, permissions,grantResults);
        } catch (NoSuchMethodException e) {
            Log.i("uu_", "NoSuchMethodException onRequestPermissionsResult 失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.i("uu_", "printStackTrace onRequestPermissionsResult 失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.i("uu_", "InvocationTargetException onRequestPermissionsResult 失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        String classname = getClassName();
        if (classname == null)
            return;
        try {
            Object object = objects.get(classname.toUpperCase());
            if (object == null) {
                Log.i("uu_", "onActivityResult 失败，object is null");
                return;
            }

            Method method = object.getClass().getMethod("onActivityResult", int.class, int.class, Intent.class);
            if (method == null) {
                Log.i("uu_", "onActivityResult 失败，method is null");
                return;
            }
            Object result = method.invoke(object, requestCode, resultCode,data);
        } catch (NoSuchMethodException e) {
            Log.i("uu_", "1 onActivityResult 失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.i("uu_", "2 onActivityResult 失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.i("uu_", "3 onActivityResult 失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        }
    }
}
