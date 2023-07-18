package com.example.splityourbill;


import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public Button createGroupButton;
    LinearLayout layout;
    public Button addTransButton, showTransButton;
    public Button resetAll, settleUp;
    ListView lv1;
    ImageView i1;
    TextView t1,t2,t3,t4;
    TextView top, topName;

    dataBaseHelper dataBaseHelper = new dataBaseHelper(MainActivity.this);
    ArrayAdapter personArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        top = findViewById(R.id.textViewTop);
        topName = findViewById(R.id.textViewTopName);

        createGroupButton = findViewById(R.id.createGroupButton);
        addTransButton = findViewById(R.id.addTransButton);
        resetAll = findViewById(R.id.resetButton);
        settleUp = findViewById(R.id.splitButton);
        showTransButton = findViewById(R.id.showTransButton);
        i1 = findViewById(R.id.i1);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        layout = findViewById(R.id.linearlayout);
        lv1 = findViewById(R.id.lv1);


        showTransButton.setOnClickListener(v -> {
            if (v.getId() == R.id.showTransButton) {
                Intent showTransIntent = new Intent(MainActivity.this, ViewTransaction.class);
                startActivity(showTransIntent);
            }
        });

        ShowPerson(dataBaseHelper);
        int numberOfPerson = dataBaseHelper.getEveryOne().size();
        int numberOfTrans = dataBaseHelper.getEveryTrans().size();

        if (numberOfPerson > 0) {
            createGroupButton.setVisibility(View.GONE);
            addTransButton.setVisibility(View.VISIBLE);
            resetAll.setVisibility(View.VISIBLE);
            i1.setVisibility(View.GONE);
            t1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            t4.setVisibility(View.GONE);
            lv1.setVisibility(View.VISIBLE);
            top.setVisibility(View.VISIBLE);
            topName.setVisibility(View.VISIBLE);

            layout.setBackground(ContextCompat.getDrawable(this, R.drawable.background));


            settleUp.setVisibility(View.VISIBLE);
            settleUp.setEnabled(false);
            showTransButton.setVisibility(View.VISIBLE);
            showTransButton.setEnabled(false);

        }
        if (numberOfTrans > 0) {
            settleUp.setEnabled(true);

            showTransButton.setEnabled(true);
        }


        settleUp.setOnClickListener(v -> {
            if (v.getId() == R.id.splitButton) {
                Intent settleUpIntent = new Intent(MainActivity.this, SettleUp.class);
                startActivity(settleUpIntent);
            }
        });

        createGroupButton.setOnClickListener(v -> {
            if (v.getId() == R.id.createGroupButton) {
                Intent createGroupIntent = new Intent(MainActivity.this, createGroupAddName.class);
                startActivity(createGroupIntent);
            }
        });




        resetAll.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmation")
                    .setMessage("Are you sure you want to reset?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dataBaseHelper.clearDatabase();
                        finish();
                        startActivity(getIntent());
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // Do nothing or handle the cancel action
                    })
                    .show();
        });



        addTransButton.setOnClickListener(v -> {
            if (v.getId() == R.id.addTransButton) {
                Intent addTransIntent = new Intent(MainActivity.this, addTransDetails.class);
                startActivity(addTransIntent);
            }
        });

    }

    private void ShowPerson(dataBaseHelper dataBaseHelper) {
        lv1 = findViewById(R.id.lv1);
//        personArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryOne());
//        lv1.setAdapter(personArrayAdapter);


        customNameBaseAdapter customNameBaseAdapter = new customNameBaseAdapter(getApplicationContext(), dataBaseHelper.getEveryOne());
        lv1.setAdapter(customNameBaseAdapter);
    }

    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    //on back pressed
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}