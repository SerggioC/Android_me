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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState != null) return;

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create a new head BodyPartFragment
        BodyPartFragment headFragment = new BodyPartFragment();

        headFragment.setListBodyParts(AndroidImageAssets.getHeads());
        headFragment.setListIndex(0);
        // Add the fragment to its container using a FragmentManager and a Transaction

        fragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .commit();

        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setListBodyParts(AndroidImageAssets.getBodies());
        bodyFragment.setListIndex(0);
        fragmentManager.beginTransaction()
                .add(R.id.body_container, bodyFragment)
                .commit();

        BodyPartFragment legsFragment = new BodyPartFragment();
        legsFragment.setListBodyParts(AndroidImageAssets.getLegs());
        legsFragment.setListIndex(0);
        fragmentManager.beginTransaction()
                .add(R.id.feet_container, legsFragment)
                .commit();

    }


    void testLists() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        List<Integer> list = new ArrayList<Integer>();


        // ArrayList add
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println("ArrayList add:  \n" + duration);

        // ArrayList get
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            arrayList.get(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("ArrayList get:  \n" + duration);

        // ArrayList set
        startTime = System.nanoTime();
        for (int i = 99999; i >= 0; i--) {
            arrayList.set(i, i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("ArrayList set:  \n" + duration);

        // ArrayList remove
        startTime = System.nanoTime();
        for (int i = 99999; i >= 0; i--) {
            arrayList.remove(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("ArrayList remove:  \n" + duration);


        // List add
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("List add:  \n" + duration);

        // List get
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.get(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("List get:  \n" + duration);

        // List set
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.set(i, i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("List set:  \n" + duration);

        // List remove
        startTime = System.nanoTime();
        for (int i = 99999; i >= 0; i--) {
            list.remove(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("List remove: \n" + duration);


        // LinkedList add
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("LinkedList add: \n" + duration);

        // LinkedList get
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedList.get(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("LinkedList get: \n" + duration);

        // LinkedList set
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedList.set(i, i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("LinkedList set:  \n" + duration);

        // LinkedList remove
        startTime = System.nanoTime();
        for (int i = 99999; i >= 0; i--) {
            linkedList.remove(i);
        }
        duration = System.nanoTime() - startTime;
        System.out.println("LinkedList remove:  \n" + duration);


    }


}
