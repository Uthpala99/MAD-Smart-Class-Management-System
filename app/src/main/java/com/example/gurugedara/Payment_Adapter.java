package com.example.gurugedara;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Payment_Adapter extends RecyclerView.Adapter<Payment_Adapter.MyViewHolder>{

    private Context context;
    private ArrayList payment_id,registernum, name,slip,bank,month;
    Activity activity;


    Payment_Adapter( Activity activity,
                    Context  context,
                    ArrayList  payment_id,
                    ArrayList registernum,
                    ArrayList name,
                    ArrayList slip,
                    ArrayList bank,
                    ArrayList month

                     ){
        this.activity = activity;
        this.context = context;
        this.payment_id = payment_id;
        this.registernum = registernum;
        this.name = name;
        this.slip = slip;
        this.bank = bank;
        this.month = month;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.payment_raw,parent,false);
        return new Payment_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder. payment_name_txt.setText(String.valueOf(name.get(position)));
        holder.payment_slip_txt.setText(String.valueOf(slip.get(position)));
        holder.payment_bank_txt.setText(String.valueOf(bank.get(position)));
        holder.payment_month_txt.setText(String.valueOf(month.get(position)));
        holder.payment_register_txt.setText(String.valueOf(registernum.get(position)));
        holder.payment_id_txt.setText(String.valueOf(payment_id.get(position)));
        holder. mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,UpdatePayment.class);
                intent.putExtra("id",String.valueOf(payment_id.get(position)));
                intent.putExtra("registe",String.valueOf(registernum.get(position)));
                intent.putExtra("mont",String.valueOf(month.get(position)));
                intent.putExtra("ban",String.valueOf(bank.get(position)));
                intent.putExtra("sli",String.valueOf(slip.get(position)));
                intent.putExtra("nam",String.valueOf(name.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });



    }

    @Override
    public int getItemCount() {
        return payment_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView payment_name_txt,payment_slip_txt,payment_bank_txt,payment_month_txt,payment_register_txt,payment_id_txt;
      LinearLayout mainLayout2;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            payment_name_txt = itemView.findViewById(R.id.payment_name_txt);
            payment_slip_txt = itemView.findViewById(R.id.payment_slip_txt);
            payment_bank_txt = itemView.findViewById(R.id.payment_bank_txt);
            payment_month_txt = itemView.findViewById(R.id.payment_month_txt);
            payment_register_txt = itemView.findViewById(R.id.payment_register_txt);
            payment_id_txt = itemView.findViewById(R.id.payment_id_txt);
            mainLayout2 = itemView.findViewById(R.id. mainLayout2);



        }
    }
}
