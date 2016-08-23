package co.alectronic.parsedemo;

import java.util.HashMap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;

import com.parse.ParseCloud;
import com.parse.FunctionCallback;

import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("YOUR_APP_ID")
            .clientKey("YOUR_CLIENT_KEY")
            .server("YOUR_SERVER_URL")
            .build()
        );


        HashMap<String, Object> params = new HashMap<String, Object>();
        ParseCloud.callFunctionInBackground("YOUR_CLOUD_CODE_FUNCTION", params, new FunctionCallback<String>() {
            public void done(String ratings, ParseException e) {
                if (e == null) {
                    System.out.println(ratings);
                }
                else{
                    System.out.println("Error "+e.getMessage());
                }
            }
        });


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL,true);

    }
}
