package com.example.realestatemw;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.AbstractList;
import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class CustomFragment extends Fragment {


    private ArrayList<Agent> agentArray;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_fragment, container, false);
        //FirebaseUtil.openFirebaseReference("buy");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("buy");

        mSearchField = (EditText) view.findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) view.findViewById(R.id.search_btn);
        mResultList = (RecyclerView) view.findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });


        return view;

    }

    public void firebaseUserSearch(String searchText) {

        Toast.makeText(getActivity(),"Started Search",Toast.LENGTH_SHORT).show();
        Query firebaseSearchQuery = mDatabaseReference.orderByChild("city").startAt(searchText).endAt(searchText + "\uf8ff");


    }
    // View Holder Class

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView textCity;
        TextView textTown;
        TextView textPrice;
        TextView textBeds;
        //TextView textBath;
        Button textButton;
        ImageView imageView;

        View mView;


        SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            textCity = (TextView) itemView.findViewById(R.id.city);
            textTown = (TextView) itemView.findViewById(R.id.town);
            textPrice = (TextView) itemView.findViewById(R.id.price);
            textBeds = (TextView) itemView.findViewById(R.id.beds);

            textButton = (Button) itemView.findViewById(R.id.butText);
            imageView = (ImageView) itemView.findViewById(R.id.image);
           // itemView.setOnClickListener((View.OnClickListener) this);

        }

        public void bind( Agent property) {
            textCity.setText(property.getCity());
            textTown.setText(property.getTown());
            textPrice.setText(property.getPrice());
            textBeds.setText(property.getBeds());
            textButton.setText(property.getType());

            showImage(property.getImage());

        }

//        public void setDetails(Context context,String city, String town, String image,String price, String beds, String type ){
//            textCity.setText(city);
//            textTown.setText(town);
//            textPrice.setText(price);
//            textBeds.setText(beds);
//            textButton.setText(type);
//
//        }

        private void showImage(String imageUrl) {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                int width = Resources.getSystem().getDisplayMetrics().widthPixels;
                Picasso.get()
                        .load(imageUrl)
                        .resize(width, width * 2 / 3)
                        .into(imageView);
            }

        }
//
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Log.i("Click Position:", String.valueOf(position));
//            Agent selectedAgent = agentArray.get(position);
//            Intent intent = new Intent(itemView.getContext(), PropertyDetailsActivity.class);
//            intent.putExtra("Agents", selectedAgent);
//            itemView.getContext().startActivity(intent);
//

        }


    public void onStart() {
        super.onStart();


       // Query firebaseSearchQuery = mDatabaseReference.orderByChild("city").startAt(searchText).endAt(searchText + "\uf8ff");
        Query firebaseSearchQuery = mDatabaseReference.child("buy").orderByChild("city");

        FirebaseRecyclerOptions<Agent> options =
                new FirebaseRecyclerOptions.Builder<Agent>()
                        .setQuery( firebaseSearchQuery, Agent.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Agent, SearchViewHolder>(options) {


            @NonNull
            @Override
            public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.search_list_layout, parent, false);


                return new SearchViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SearchViewHolder holder, int position, @NonNull Agent model) {
              Agent agent = agentArray.get(position);
              holder.bind(agent);


            }
        };

        adapter.startListening();
        mResultList.setAdapter(adapter);
    }
}



