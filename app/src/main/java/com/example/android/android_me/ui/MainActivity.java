package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Sergio on 20/03/2018.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageCickedOnFragmentListener {

    public static final String HEAD_INDEX = "headIndex";
    public static final String BODY_INDEX = "bodyIndex";
    public static final String FEET_INDEX = "feetIndex";
    private int headIndex;
    private int bodyIndex;
    private int feetIndex;
    private boolean isTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTwoPane = getResources().getBoolean(R.bool.isTwoPane);
    }

    @Override
    public void onImageClicked(int position) {
        int bodyPartIndex = position / 12;
        int index = position - 12 * bodyPartIndex;
        Button nextButton = (Button) findViewById(R.id.next_button);

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();


        if (isTwoPane) {
            nextButton.setVisibility(View.GONE);
            BodyPartFragment fragment = new BodyPartFragment();

            switch (bodyPartIndex) {
                case 0: {
                    headIndex = index;

                    // Set the list of image id's for the head fragment and set the position to the second image in the list
                    fragment.setImageIds(AndroidImageAssets.getHeads());
                    fragment.setListIndex(headIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.head_container, fragment)
                            .commit();

                    break;
                }
                case 1: {
                    bodyIndex = index;

                    fragment.setListIndex(bodyIndex);
                    fragment.setImageIds(AndroidImageAssets.getBodies());
                    fragmentManager.beginTransaction()
                            .replace(R.id.body_container, fragment)
                            .commit();
                    break;
                }
                case 2: {
                    feetIndex = index;

                    fragment.setListIndex(feetIndex);
                    fragment.setImageIds(AndroidImageAssets.getLegs());
                    fragmentManager.beginTransaction()
                            .replace(R.id.leg_container, fragment)
                            .commit();

                    break;
                }
            }

        } else {
            switch (bodyPartIndex) {
                case 0: {
                    headIndex = index;
                    break;
                }
                case 1: {
                    bodyIndex = index;
                    break;
                }
                case 2: {
                    feetIndex = index;
                    break;
                }
            }

            Bundle bundle = new Bundle(3);
            bundle.putInt(HEAD_INDEX, headIndex);
            bundle.putInt(BODY_INDEX, bodyIndex);
            bundle.putInt(FEET_INDEX, feetIndex);

            Toast.makeText(this,
                    "Clicked Position " + position + "\n" +
                            "Index= " + bodyPartIndex +"\n" +
                            "list Index = " + index,
                    Toast.LENGTH_SHORT).show();


            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            nextButton.setOnClickListener(new View.OnClickListener() {

                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });

        }


    }
}
