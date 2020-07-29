package com.example.realestatemw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

public class HomeFragment extends Fragment {

    ArrayList <Agent> mAgent;
    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mDatabaseReference;
    private  ChildEventListener mChildListener;

    private RecyclerView recyclerView;
  


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);

        Toolbar toolbarHome = (Toolbar) view.findViewById(R.id.toolbarHome);
        toolbarHome.setTitle("Real Estate Mw");

       //1. get a reference to recyclerView
       recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        final AgentAdapter adapter = new AgentAdapter();
      recyclerView.setAdapter(adapter);

       recyclerView.setHasFixedSize(true);

       // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        

      return view;
    }

}