package com.example.gurugedara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPayment extends AppCompatActivity {


    EditText p_register;
    EditText p_name;
    EditText p_slip;
    EditText p_bank;
    EditText p_month;
    Button p_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);


        p_register = findViewById(R.id. p_register);
        p_name = findViewById(R.id.p_name);
        p_slip = findViewById(R.id.p_slip);
        p_month = findViewById(R.id.p_month);
        p_bank = findViewById(R.id.p_bank);
        p_button = findViewById(R.id.p_button);

        p_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Payment_MyDataBaseHelper myDB = new  Payment_MyDataBaseHelper (AddPayment.this);
                myDB. addPayment(
                        p_register.getText().toString().trim(),
                        p_name.getText().toString().trim(),
                        p_slip.getText().toString().trim(),
                        p_month.getText().toString().trim(),
                        p_bank.getText().toString().trim());

            }
        });

    }
}