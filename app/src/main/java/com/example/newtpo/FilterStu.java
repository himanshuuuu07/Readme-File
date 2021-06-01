package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class FilterStu extends AppCompatActivity
{
    private Chip it,cse,mech,el,en,civil;
    private Chip c6_5,c7_5,c7,c8;
    Button apply;
    private String select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_stu);

        it=findViewById(R.id.IT);
        cse=findViewById(R.id.CSE);
        mech=findViewById(R.id.Mech);
        el=findViewById(R.id.EL);
        en=findViewById(R.id.EN);
        civil=findViewById(R.id.Civil);

        apply=findViewById(R.id.filtApply);

        c6_5=findViewById(R.id.CGPA6_5);
        c7=findViewById(R.id.CGPA7);
        c7_5=findViewById(R.id.CGPA7_5);
        c8=findViewById(R.id.CGPA8);

        select=new String();

        CompoundButton.OnCheckedChangeListener checkedChangeListener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    select=buttonView.getText().toString();
                }
                else
                {
                    select="";
                }
            }
        };

        it.setOnCheckedChangeListener(checkedChangeListener);
        cse.setOnCheckedChangeListener(checkedChangeListener);
        mech.setOnCheckedChangeListener(checkedChangeListener);
        el.setOnCheckedChangeListener(checkedChangeListener);
        en.setOnCheckedChangeListener(checkedChangeListener);
        civil.setOnCheckedChangeListener(checkedChangeListener);

        c6_5.setOnCheckedChangeListener(checkedChangeListener);
        c7_5.setOnCheckedChangeListener(checkedChangeListener);
        c7.setOnCheckedChangeListener(checkedChangeListener);
        c8.setOnCheckedChangeListener(checkedChangeListener);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent=new Intent();
                resultIntent.putExtra("data",select);
                setResult(101,resultIntent);
                finish();
            }
        });


    }
}