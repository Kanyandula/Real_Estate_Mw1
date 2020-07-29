package com.example.realestatemw;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.data.model.Resource;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.AgentViewHolder> {

    private ArrayList<Agent> agentArray;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    public AgentAdapter() {
        FirebaseUtil.openFirebaseReference("buy");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        agentArray = FirebaseUtil.mAgent;
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Agent mBuy = dataSnapshot.getValue(Agent.class);
                mBuy.setId(dataSnapshot.getKey());
                agentArray.add(mBuy);
                notifyDataSetChanged();
                notifyItemInserted(agentArray.size() - 1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);

    }

    @NonNull
    @Override
    public AgentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.individual_row, parent, false);
        return new AgentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentViewHolder holder, int position) {
        Agent agent = agentArray.get(position);
        holder.bind(agent);

    }

    @Override
    public int getItemCount() {
        return agentArray.size();
    }

    public class AgentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textCity;
        TextView textTown;
        TextView textPrice;
        TextView textBeds;
        //TextView textBath;
        Button textButton;
        ImageView imageView;

        public AgentViewHolder(@NonNull View itemView) {
            super(itemView);
            textCity = (TextView) itemView.findViewById(R.id.city);
            textTown = (TextView) itemView.findViewById(R.id.town);
            textPrice = (TextView) itemView.findViewById(R.id.price);
            textBeds = (TextView) itemView.findViewById(R.id.beds);

            textButton = (Button) itemView.findViewById(R.id.butText);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        public void bind(Agent property) {
            textCity.setText(property.getCity());
            textTown.setText(property.getTown());
            textPrice.setText(property.getPrice());
            textBeds.setText(property.getBeds());
            textButton.setText(property.getType());

            showImage(property.getImage());

        }

        private void showImage(String imageUrl) {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                int width = Resources.getSystem().getDisplayMetrics().widthPixels;
                Picasso.get()
                        .load(imageUrl)
                        .resize(width, width * 2/3)
                        .into(imageView);
            }

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.i("Click Position:",String.valueOf(position));
            Agent selectedAgent = agentArray.get(position);
            Intent intent = new Intent(itemView.getContext(), PropertyDetailsActivity.class);
           intent.putExtra("Agents", selectedAgent);
            itemView.getContext().startActivity(intent);

        }
    }
}