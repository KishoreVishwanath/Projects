package com.google.android.gms.location.sample.locationaddress;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Uday on 24-09-2016.
 */
public class WebServiceAccessLayer {

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params) {
        // Show Progress Dialog
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        //client.setBasicAuth("username","password/token");
        //client.setEnableRedirects(true);
        try {
            client.get("http://24x7fscimap.ampersandsquare.com/Sample/login/dologin", params, new AsyncHttpResponseHandler() {
                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(String response) {
                    try {
                        Boolean EmplStatus = false;
                        // JSON Object
                        JSONObject obj = new JSONObject(response);
                        EmplStatus = obj.getBoolean("status");
                        // When the JSON response has status boolean value assigned with true
                        if (EmplStatus) {
                            // Navigate to Home screen
                            System.out.println("Authenticated");
                        }
                        // Else display error message
                        else {
                            System.out.println("Not Authenticated");
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                // When the response returned by REST has Http response code other than '200'
                @Override
                public void onFailure(int statusCode, Throwable error,
                                      String content) {
                    // Hide Progress Dialog
                    // When Http response code is '404'
                    if (statusCode == 404) {
                    }
                    // When Http response code is '500'
                    else if (statusCode == 500) {
                    }
                    // When Http response code other than 404, 500
                    else {
                    }
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
