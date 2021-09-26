package com.example.gurugedara;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnouncementCustomAdapter extends RecyclerView.Adapter<AnnouncementCustomAdapter.MyViewHolder> {

    private Context context ;
    Activity activity ;
    private ArrayList announcement_id  , announcement_topic , announcement_content;

    AnnouncementCustomAdapter(Activity activity , Context context , ArrayList announcement_id , ArrayList announcement_topic , ArrayList announcement_content){
        this.activity = activity;
        this.context = context ;
        this.announcement_id = announcement_id ;
        this.announcement_topic = announcement_topic ;
        this.announcement_content = announcement_content ;
    }


    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.announcement_row , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AnnouncementCustomAdapter.MyViewHolder holder, int position) {
        //holder.announcement_id_txt.setText(String.valueOf(announcement_id.get(position)));
        holder.announcement_topic_txt.setText(String.valueOf(announcement_topic.get(position)));
        holder.announcement_content_txt.setText(String.valueOf(announcement_content.get(position)));
        holder.announcementMainLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , UpdateAnnouncement.class);
                intent.putExtra("id" , String.valueOf(announcement_id.get(position)));
                intent.putExtra("topic" , String.valueOf(announcement_topic.get(position)));
                intent.putExtra("content" , String.valueOf(announcement_content.get(position)));
                activity.startActivityForResult(intent , 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcement_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  announcement_topic_txt , announcement_content_txt;
        LinearLayout announcementMainLayout;


        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            //announcement_id_txt = itemView.findViewById(R.id.announcement_id_txt);
            announcement_topic_txt = itemView.findViewById(R.id.announcement_topic_txt);
            announcement_content_txt = itemView.findViewById(R.id.announcement_content_txt);
            announcementMainLayout = itemView.findViewById(R.id.announcementMainLayout);


        }
    }
}
