package com.example.searchfiltervolley;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;

    // creating a constructor for our variables.
    public CourseAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        CourseModal modal = courseModalArrayList.get(position);
        holder.name.setText(modal.getName());
        Glide.with(context).load(modal.getImage()).into(holder.thumbnail);
        holder.phone.setText(modal.getPhone());

        holder.sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,seconActivity.class);
                intent.putExtra("img",modal.getImage());
                intent.putExtra("name",modal.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        public TextView name, phone;
        public ImageView thumbnail;
        RelativeLayout sendData;


        public ViewHolder(View itemView) {
            super(itemView);

            // initializing our views with their ids.
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            sendData = itemView.findViewById(R.id.sendData);

        }
    }
}
