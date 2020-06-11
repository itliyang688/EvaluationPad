// IUserData.aidl
package com.future_education.module_login;

// Declare any non-default types here with import statements
import com.future_education.module_login.OnUserDataUpdateListener;

interface IUserData {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    boolean isLogin();
    String getToken();
    String getLoginInfo();
    String getStudentInfo();
    void registerListener(in OnUserDataUpdateListener listener);
    void unregisterListener(in OnUserDataUpdateListener listener);
}
