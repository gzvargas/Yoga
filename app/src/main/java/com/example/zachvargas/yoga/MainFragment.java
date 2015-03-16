package com.example.zachvargas.yoga;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachvargas.yoga.Model.Backend;
import com.example.zachvargas.yoga.Model.Pose;
import com.example.zachvargas.yoga.Model.User;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View parent = inflater.inflate(R.layout.fragment_main, container, false);

        // Inflate the layout for this fragment
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        User currUser = User.findById(User.class, Long.parseLong(prefs.getString("loggedInId", null)));

        //loading all the poses and displaying them to the user
        Backend.loadPoses(currUser, new Backend.BackendCallback() {
            @Override
            public void onRequestCompleted(Object result) {
                final ArrayList<Pose> poses = (ArrayList<Pose>) result;

                //getting the LinearLayout which will hold the pose list
                final LinearLayout poseContainer = (LinearLayout) parent.findViewById(R.id.poseContainer);

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < poses.size(); i++) {
                            Pose currPose = poses.get(i);
                            View poseItem = inflater.inflate(R.layout.pose_item, null);

                            TextView poseNameText = (TextView) poseItem.findViewById(R.id.poseName);
                            TextView poseDifficultyText =
                                    (TextView) poseItem.findViewById(R.id.poseDifficulty);

                            poseNameText.setText(currPose.name);
                            poseDifficultyText.setText("Difficulty : " + currPose.difficulty);

                            new DownloadImageTask((ImageView) poseItem.findViewById(R.id.poseImage))
                                    .execute(currPose.image_url);

                            //adding the pose to the container
                            poseContainer.addView(poseItem);
                        }
                        //hiding the progress bar
                        ProgressBar progress_bar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_poses);
                        progress_bar.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onRequestFailed(final String message) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        ProgressBar progress_bar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_poses);
                        progress_bar.setVisibility(View.GONE);
                    }
                });
            }
        });
        return parent;

    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
