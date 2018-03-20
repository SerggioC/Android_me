/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    private static final String ARRAY_LIST_BUNDLE = "ArrayList_Bundle";
    private static final String LIST_INDEX_BUNDLE = "List_Index_Bundle";
    VelocityTracker mVelocityTracker = null;
    private List<Integer> mListBodyParts;
    private Integer mListIndex = 0;
    private GestureDetector gestureDetector;
    private View.OnClickListener clickListener;
    private ImageView imageView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public BodyPartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mListBodyParts = savedInstanceState.getIntegerArrayList(ARRAY_LIST_BUNDLE);
            mListIndex = savedInstanceState.getInt(LIST_INDEX_BUNDLE);
        }
    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewOnClick(1);
                Log.i("Sergio>", this + " onClick normal");
            }
        };
        imageView.setOnClickListener(clickListener);

        gestureDetector = new GestureDetector(getContext(), new MyGestureListener());

        // This touch listener passes everything on to the gesture detector.
        // That saves us the trouble of interpreting the raw touch events
        // ourselves.
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // pass the events to the gesture detector
                // a return value of true means the detector is handling it
                // a return value of false means the detector didn't
                // recognize the event
                return gestureDetector.onTouchEvent(event);

            }
        };

        imageView.setOnTouchListener(touchListener);


        // Set the image to the first in our list of head images
        if (mListBodyParts != null && mListIndex >= 0) {
            imageView.setImageResource(mListBodyParts.get(mListIndex));
        } else {
            imageView.setImageResource(mListBodyParts.get(0));
            mListIndex = 0;
        }


        // Return the rootView
        return rootView;
    }

    private void updateViewOnClick(Integer direction) { // direction: +/-1
        mListIndex += direction;
        int listSize_1 = mListBodyParts.size() - 1;
        if (mListIndex > listSize_1) {
            mListIndex = 0;
        } else if (mListIndex < 0) {
            mListIndex = listSize_1;
        }
        imageView.setImageResource(mListBodyParts.get(mListIndex));
    }

    public void setListBodyParts(List<Integer> mListBodyParts) {
        this.mListBodyParts = mListBodyParts;
    }

    public void setListIndex(Integer mListIndex) {
        this.mListIndex = mListIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(ARRAY_LIST_BUNDLE, (ArrayList<Integer>) mListBodyParts);
        outState.putInt(LIST_INDEX_BUNDLE, mListIndex);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            updateViewOnClick(1);
            Log.i("Sergio>", this + " onSingleTapUp");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("Sergio>", this + " onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

//            float distance = e1.getX() - e2.getX();
//            Log.v("Sergio>", this + " onScroll distance= " + distance);
//
//            float speed = Math.abs(distance / (e1.getEventTime() - e2.getEventTime()));
//            Log.i("Sergio>", this + " onScroll speed= " + speed);
//
//            if (e1.getX() < e2.getX() && Math.abs(distanceX) > 50 && speed > 0.3) {
//                updateViewOnClick(1);
//                Log.w("Sergio>", "Left to Right scroll performed 0]--->>> " + distanceX);
//            }
//
//            if (e1.getX() > e2.getX() && Math.abs(distanceX) > 50 && speed > 0.3) {
//                updateViewOnClick(-1);
//                Log.w("Sergio>", "Right to Left scroll performed  <<<---[0 " + distanceX);
//            }


            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int PIXELS_PER_SECOND = 100;
            float maxFlingVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
            float velocityPercentX = velocityX / maxFlingVelocity;          // the percent is a value in the range of (0, 1]
            float normalizedVelocityX = velocityPercentX * PIXELS_PER_SECOND;  // where PIXELS_PER_SECOND is a device-independent measurement


            if (e1.getY() <= e2.getY() && e1.getX() > e2.getX()) {
                updateViewOnClick(-1);
                Log.w("Sergio>", "Up to Down + Right to Left swipe performed");
            }

            if (e1.getY() <= e2.getY() && e1.getX() < e2.getX()) {
                updateViewOnClick(1);
                Log.w("Sergio>", "Up to Down + Left to Right swipe performed");
            }

            if (e1.getY() >= e2.getY() && e1.getX() < e2.getX()) {
                updateViewOnClick(1);
                Log.w("Sergio>", "Down to Up + Left to Right swipe performed");
            }

            if (e1.getY() >= e2.getY() && e1.getX() > e2.getX()) {
                updateViewOnClick(-1);
                Log.w("Sergio>", "Down to Up + Right to Left swipe performed");
            }

            Log.i("Sergio>", this + " onFling velocity X" + velocityX + "\n" +
                    "e1.getX()=" + e1.getX() + "\n" +
                    "e2.getX()=" + e2.getX() + "\n" +
                    "e1.getY()=" + e1.getY() + "\n" +
                    "e2.getY()=" + e2.getY());


            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("Sergio>", this + " onShowPress");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("Sergio>", this + " onDown");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("Sergio>", this + " onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("Sergio>", this + " onDoubleTapEvent");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("Sergio>", this + " onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.i("Sergio>", this + " onContextClick");
            return true;
        }
    }
}
