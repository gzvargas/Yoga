package com.example.zachvargas.yoga;


import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachvargas.yoga.Model.Backend;
import com.example.zachvargas.yoga.Model.Routine;
import com.example.zachvargas.yoga.Model.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoutinesFragment extends Fragment {

    private static final String TAG = "RoutinesFragment"; //used for logging


    public RoutinesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View parent = inflater.inflate(R.layout.fragment_routines, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        User currUser = User.findById(User.class, Long.parseLong(prefs.getString("loggedInId", null)));
        Backend.loadRoutines(currUser, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                final ArrayList<Routine> routines = (ArrayList<Routine>) result;
                Log.d(TAG, "Routine backend load success. Loaded " + routines.size() + " poses.");

                //getting the LinearLayout which will hold the routines
                final LinearLayout routineContainer = (LinearLayout) parent.findViewById(R.id.routineContainer);

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < routines.size(); i++) {
                            Routine currRoutine = routines.get(i);
                            View routineItem = inflater.inflate(R.layout.routine_item, null);

                            routineItem.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    Intent newActivity=new Intent(getActivity(), PlayerActivity.class);
                                    startActivity(newActivity);
                                }
                            });

                            //changing the text of the routines (just junk data for now;
                            TextView routineNameText = (TextView) routineItem.findViewById(R.id.routineName);
                            TextView routineDifficultyText =
                                    (TextView) routineItem.findViewById(R.id.routineDifficulty);

                            routineNameText.setText(currRoutine.name);
                            routineDifficultyText.setText("Difficulty : " + currRoutine.difficulty);

                            //adding the routine to the container
                            routineContainer.addView(routineItem);
                        }
                        ProgressBar progress_bar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_routines);
                        progress_bar.setVisibility(View.GONE);
                /*for (Routine routine : routines) {
                    Log.d(TAG, "Routine: " + routine.toString());
                }*/
                    }
                });
            }

            @Override
            public void onRequestFailed(final String message) {
                Log.d(TAG, "Received error from Backend: " + message);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        ProgressBar progress_bar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_routines);
                        progress_bar.setVisibility(View.GONE);
                    }
                });
            }
        });
        return parent;
    }
}
