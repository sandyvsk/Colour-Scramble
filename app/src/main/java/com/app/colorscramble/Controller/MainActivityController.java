package com.app.colorscramble.Controller;


import android.graphics.Color;

import com.app.colorscramble.MainActivity;
import com.app.colorscramble.Model.RecyclerData;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivityController implements IHomeController{

    MainActivity mainActivity;


    int[] colors = new int[]{Color.RED,Color.RED,Color.RED,Color.RED,

            Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,

            Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,

            Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW};


    public MainActivityController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public ArrayList<RecyclerData> getRecyclerDataArrayList()
    {
        ArrayList<RecyclerData> recyclerDataArrayList = new ArrayList<>();

        for(int i=0;i<16;i++){

            recyclerDataArrayList.add(new RecyclerData(i,colors[i],false));

        }

        return(recyclerDataArrayList);
    }

    @Override
    public void generateRandomList(ArrayList<RecyclerData> recyclerDataArrayList) {

        // Shuffling the list
        Collections.shuffle(recyclerDataArrayList);

        mainActivity.startTimer();

        mainActivity.setData(recyclerDataArrayList);
    }
}
