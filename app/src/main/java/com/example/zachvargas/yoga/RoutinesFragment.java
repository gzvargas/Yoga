package com.example.zachvargas.yoga;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoutinesFragment extends Fragment {


    public RoutinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent=inflater.inflate(R.layout.fragment_routines, container, false);

        //getting the LinearLayout which will hold the routines
        LinearLayout routineContainer = (LinearLayout)parent.findViewById(R.id.routineContainer);

        for (int i = 0; i < 10; i++) {
            View routineItem = inflater.inflate(R.layout.routine_item, null);

            //changing the text of the routines (just junk data for now;
            TextView routineNameText = (TextView) routineItem.findViewById(R.id.routineName);
            TextView routineDifficultyText =
                    (TextView)routineItem.findViewById(R.id.routineDifficulty);

            routineNameText.setText("Junk Routine Name");
            routineDifficultyText.setText("Junk Routine Difficulty");

            //adding the routine to the container
            routineContainer.addView(routineItem);

            routineItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent newActivity=new Intent(getActivity(),PlayerActivity.class);
                    startActivity(newActivity);
                }
            });
        }

        return parent;
    }
}
