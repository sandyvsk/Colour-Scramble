package com.app.colorscramble;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.colorscramble.Controller.MainActivityController;
import com.app.colorscramble.Model.RecyclerData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_timer)
    TextView txt_timer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_scramble)
    Button btn_scramble;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private ArrayList<RecyclerData> recyclerDataArrayList;

    MainActivityController mainActivityController;

    CountDownTimer countDownTimer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.w("Activity -->",TAG);

        mainActivityController = new MainActivityController(this);

        //created new array list..
        recyclerDataArrayList = new ArrayList<>();

        recyclerDataArrayList = mainActivityController.getRecyclerDataArrayList();

        setData(recyclerDataArrayList);

        btn_scramble.setOnClickListener(view -> {

            if(countDownTimer!=null){

                countDownTimer.cancel();

                mainActivityController.generateRandomList(recyclerDataArrayList);

            }

            else {

                mainActivityController.generateRandomList(recyclerDataArrayList);
            }

        });

    }

    public void setData(ArrayList<RecyclerData> recyclerDataArrayList) {

        //added data from arraylist to adapter class.
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(recyclerDataArrayList, this);
        //setting grid layout manager to implement grid view.
        // in this method '4' represents number of colums to be displayed in grid view.
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        //at last set adapter to recycler view.
        rv_data.setLayoutManager(layoutManager);

        rv_data.setAdapter(adapter);

    }



    @Override
    public void onBackPressed() {

        showAlert();
    }

    private void showAlert() {

        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        alertdialog.setTitle("Alert");
        alertdialog.setMessage("Are you sure you Want to exit ???");
        alertdialog.setPositiveButton("Yes", (dialog, which) -> {
            super.onBackPressed();

            dialog.dismiss();

            finish();

        });

        alertdialog.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alert=alertdialog.create();
        alertdialog.show();
    }

    public void startTimer(){

            countDownTimer =new CountDownTimer(600000, 1000) {

            public void onTick(long millisUntilFinished) {
                txt_timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                // logic to set the EditText could go here
            }

            public void onFinish() {
                txt_timer.setText("done!");

                recyclerDataArrayList = mainActivityController.getRecyclerDataArrayList();

                setData(recyclerDataArrayList);
            }

        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer!=null){

            countDownTimer.cancel();

        }
    }
}