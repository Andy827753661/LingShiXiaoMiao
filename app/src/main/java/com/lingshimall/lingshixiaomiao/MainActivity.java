package com.lingshimall.lingshixiaomiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup main_radiogroup;
    private RadioButton main_radiobutton_home, main_radiobutton_sale, main_radiobutton_subject, main_radiobutton_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        main_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.main_radiobutton_home:
                        break;
                    case R.id.main_radiobutton_sale:
                        break;
                    case R.id.main_radiobutton_subject:
                        break;
                    case R.id.main_radiobutton_mine:
                        break;
                }

            }
        });
    }

    private void init() {
        main_radiogroup= (RadioGroup) findViewById(R.id.main_radiogroup);
        main_radiobutton_home= (RadioButton) findViewById(R.id.main_radiobutton_home);
        main_radiobutton_sale= (RadioButton) findViewById(R.id.main_radiobutton_sale);
        main_radiobutton_subject= (RadioButton) findViewById(R.id.main_radiobutton_subject);
        main_radiobutton_mine= (RadioButton) findViewById(R.id.main_radiobutton_mine);
    }




}
