// OnUserDataUpdateListener.aidl
package com.future_education.module_login;

// Declare any non-default types here with import statements
//parcelable OnUserDataUpdateListener;
interface OnUserDataUpdateListener {
    void onLoginStateUpdate(boolean isLogin);
    void onTokenUpdate(String token);
    void onLoginInfoUpdate(String loginInfo);
    void onStudentInfoUpdate(String studentInfo);
}