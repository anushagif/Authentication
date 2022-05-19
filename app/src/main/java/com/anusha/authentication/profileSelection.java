package com.anusha.authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profileSelection extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    Button profileSelectionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        initializeUI();

        profileSelectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                Toast.makeText(profileSelection.this, "" + id + "Selected", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initializeUI() {
        radioButton1 = findViewById(R.id.radioBtn1);
        radioButton2 = findViewById(R.id.radioBtn2);
        radioGroup = findViewById(R.id.radioGroup);
        profileSelectionBtn = findViewById(R.id.profileSelection);
    }
}