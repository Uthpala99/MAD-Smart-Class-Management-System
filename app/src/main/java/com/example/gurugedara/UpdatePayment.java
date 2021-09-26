package com.example.gurugedara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePayment extends AppCompatActivity {

    EditText p_register;
    EditText p_name;
    EditText p_slip;
    EditText p_bank;
    EditText p_month;
    Button p_button;
    String x, y, z, c, v, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_payment);

        p_register = findViewById(R.id.p_register2);
        p_name = findViewById(R.id.p_name2);
        p_slip = findViewById(R.id.p_slip2);
        p_month = findViewById(R.id.p_month2);
        p_bank = findViewById(R.id.p_bank2);
        p_button = findViewById(R.id.p_button_update);
        getAndSetIntentData();
        p_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment_MyDataBaseHelper myDB = new Payment_MyDataBaseHelper(UpdatePayment.this);

                 y = p_register.getText().toString().trim();
                 v = p_name.getText().toString().trim();
                 c = p_slip.getText().toString().trim();
                 b = p_month.getText().toString().trim();
                 z = p_bank.getText().toString().trim();



                myDB.updateData(x, y, z, c, v, b);

            }
        });



    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("registe") &&
                getIntent().hasExtra("mont") && getIntent().hasExtra("ban") &&
                getIntent().hasExtra("sli") && getIntent().hasExtra("nam")) {

            x = getIntent().getStringExtra("id");
            y = getIntent().getStringExtra("registe");
            b = getIntent().getStringExtra("mont");
            z = getIntent().getStringExtra("ban");
            c = getIntent().getStringExtra("sli");
            v = getIntent().getStringExtra("nam");

            p_register.setText(y);
            p_name.setText(v);
            p_slip.setText(c);
            p_month.setText(b);
            p_bank.setText(z);


        } else {
            Toast.makeText(this, "NO DATA.", Toast.LENGTH_SHORT).show();
        }

    }

}