/*package com.example.zachvargas.yoga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zachvargas.yoga.Model.Backend;
import com.example.zachvargas.yoga.Model.User;

import java.util.List;


public class LoginActivity extends Activity {

    private ProgressBar progBar;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        progBar.setVisibility(View.GONE);
    }

    public void login(View view){
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        login("test@test.com", "12345678");
    }
    *//* API Methods *//*
    private void login(String email, String password) {
        final Context currContext = this;
        Log.d(TAG, "Attempting to login with email: " + email + " password: " + password);
        Backend.logIn(email, password, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                final User user = (User) result;
                Log.d(TAG, "Login success. User: " + user.toString());

                runOnUiThread(new Runnable() {
                    public void run() {
                        //Must check db for user with existing backendId.  If doesn't already exist, then save
                        //.find is how to query for objects saved with Sugar
                        List<User> users = User.find(User.class, "backend_id = ?",  new Integer(user.backendId).toString());
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(currContext);
                        SharedPreferences.Editor editor = prefs.edit();
                        if (users.size() == 0) {
                            user.save();
                            //This is NOT the backend id, this is the sugar id
                            editor.putString("loggedInId", Long.toString(user.getId()));
                            editor.commit();
                        } else {
                            User currUser = users.get(0);
                            currUser.authToken = user.authToken;
                            currUser.tokenExpiration = user.tokenExpiration;
                            currUser.save();
                            //This is NOT the backend id, this is the sugar id
                            editor.putString("loggedInId", Long.toString(currUser.getId()));
                            editor.commit();
                        }

                        Intent intent = new Intent(currContext, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onRequestFailed(final String message) {

                //NOTE: parameter validation and filtering is handled by the backend, just show the
                //returned error message to the user
                Log.d(TAG, "Received error from Backend: " + message);
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

*//*    private void loginUser(String email, String password) {

        progBar.setVisibility(View.VISIBLE);

        Log.d(null, "Attempting to login with email: " + email + " password: " + password);
        Backend.logIn(email, password, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                User user = (User) result;
                Log.d(null, "Login success. User: " + user.toString());

                //TESTING
                loadPoses(user);
            }

            @Override
            public void onRequestFailed(String message) {

                //NOTE: parameter validation and filtering is handled by the backend, just show the
                //returned error message to the user
                Log.d(null, "Received error from Backend: " + message);
            }
        });
    }

    private void loadPoses(final User user) {
        Log.d(null, "Attempting to download poses from backend");

        Backend.loadPoses(user, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                ArrayList<Pose> poses = (ArrayList<Pose>) result;
                Log.d(null, "Pose backend load success. Loaded " + poses.size() + " poses.");

                for (Pose pose: poses) {
                    Log.d(null, "Pose: " + pose.toString());
                }

                //TESTING
                loadRoutines(user);
            }

            @Override
            public void onRequestFailed(String message) {
                Log.d(null, "Received error from Backend: " + message);
            }
        });
    }

    private void loadRoutines(final User user) {
        Log.d(null, "Attempting to download routines from backend");

        Backend.loadRoutines(user, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                ArrayList<Routine> routines = (ArrayList<Routine>) result;
                Log.d(null, "Routine backend load success. Loaded " + routines.size() + " routines.");

                for (Routine routine : routines) {
                    Log.d(null, "Routine: " + routine.toString());
                }

            }

            @Override
            public void onRequestFailed(String message) {
                Log.d(null, "Received error from Backend: " + message);
            }
        });
    }*//*
}*/

package com.example.zachvargas.yoga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zachvargas.yoga.Model.Backend;
import com.example.zachvargas.yoga.Model.User;

import java.util.List;


public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private ProgressBar progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        progBar.setVisibility(View.GONE);
    }

    public void login(View view){
        //EditText emailView = (EditText)findViewById(R.id.username);
        //EditText pwView = (EditText) findViewById(R.id.password);
        login("test@test.com", "12345678");
        progBar.setVisibility(View.VISIBLE);
    }

    /* API Methods */
    private void login(String email, String password) {
        final Context currContext = this;
        Log.d(TAG, "Attempting to login with email: " + email + " password: " + password);
        Backend.logIn(email, password, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                final User user = (User) result;
                Log.d(TAG, "Login success. User: " + user.toString());

                runOnUiThread(new Runnable() {
                    public void run() {
                        //Must check db for user with existing backendId.  If doesn't already exist, then save
                        //.find is how to query for objects saved with Sugar
                        List<User> users = User.find(User.class, "backend_id = ?", new Integer(user.backendId).toString());
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(currContext);
                        SharedPreferences.Editor editor = prefs.edit();
                        if (users.size() == 0) {
                            user.save();
                            //This is NOT the backend id, this is the sugar id
                            editor.putString("loggedInId", Long.toString(user.getId()));
                            editor.commit();
                        } else {
                            User currUser = users.get(0);
                            currUser.authToken = user.authToken;
                            currUser.tokenExpiration = user.tokenExpiration;
                            currUser.save();
                            //This is NOT the backend id, this is the sugar id
                            editor.putString("loggedInId", Long.toString(currUser.getId()));
                            editor.commit();
                        }

                        Intent intent = new Intent(currContext, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onRequestFailed(final String message) {

                //NOTE: parameter validation and filtering is handled by the backend, just show the
                //returned error message to the user
                Log.d(TAG, "Received error from Backend: " + message);
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}