package com.google.android.gms.location.sample.locationaddress;

import android.location.Location;

/**
 * Created by Uday on 18-09-2016.
 */
public class ApplicationData {
    private static ApplicationData mInstance= null;

    public Location gLastLocation;

    protected ApplicationData(){}

    public static synchronized ApplicationData getInstance(){
        if(null == mInstance){
            mInstance = new ApplicationData();
        }
        return mInstance;
    }
}
