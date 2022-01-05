package com.omdemo;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.rn.bridge.OMDeviceModule;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "omdemo";
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    //初始化如下代码,设置亮度功能时,需要弹窗让用户开启权限
    OMDeviceModule.init(this);
    //YF_XXXGApi
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    OMDeviceModule.onRequestPermissionsResult(requestCode,permissions,grantResults);
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    OMDeviceModule.onActivityResult(requestCode,resultCode,data);
    super.onActivityResult(requestCode, resultCode, data);
  }
}
