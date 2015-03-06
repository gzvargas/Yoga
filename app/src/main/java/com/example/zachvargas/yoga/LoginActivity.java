package com.example.zachvargas.yoga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.zachvargas.yoga.Model.Backend;
import com.example.zachvargas.yoga.Model.Pose;
import com.example.zachvargas.yoga.Model.Routine;
import com.example.zachvargas.yoga.Model.User;

import java.util.ArrayList;


public class LoginActivity extends Activity {

    ProgressBar progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        progBar.setVisibility(View.GONE);
    }

    public void login(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        loginUser("test@test.com", "12345678");
    }

    private void loginUser(String email, String password) {

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
    }
}
