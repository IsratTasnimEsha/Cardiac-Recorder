package com.example.gitproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class AdapterClass extends RecyclerView .Adapter<AdapterClass.MyViewHolder> {
    Context context;
    ArrayList<UserClass> arrayList;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    public AdapterClass(Context context, ArrayList<UserClass> list) {
        this.context = context;
        this.arrayList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.show_records, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {
        UserClass userClass= arrayList.get(position);

        holder.sys.setText(userClass.getSystolic());
        holder.dias.setText(userClass.getDiastolic());
        holder.rate.setText(userClass.getHeart_Rate());
        holder.comment.setText(userClass.getComment());
        holder.time.setText(userClass.getTime());

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent(context, UpdateActivity.class);
                intent.putExtra("Phone", userClass.getPhone());
                intent.putExtra("Time", userClass.getTime());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sys, dias, rate, comment, time;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sys=itemView.findViewById(R.id.sys);
            dias=itemView.findViewById(R.id.dias);
            rate=itemView.findViewById(R.id.rate);
            comment=itemView.findViewById(R.id.comment);
            time=itemView.findViewById(R.id.time);
            card=itemView.findViewById(R.id.card);
        }
    }
}