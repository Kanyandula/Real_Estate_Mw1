package com.example.realestatemw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PropertyDetailsActivity extends AppCompatActivity {
    private ArrayList<Agent> agentArray;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private ImageView imageView;
    private TextView textPrice;
    private TextView textBath;
    private  TextView textBeds;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    List<Images> sliderImg;
    ViewPagerAdapter viewPagerAdapter;
    Agent agent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proparty_details);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.collapsing_toolbar_title));

      viewPager = (ViewPager) findViewById(R.id.view_pager);
//        viewPager.setAdapter(viewPagerAdapter);

        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

        // optional
      // adapter.registerDataSetObserver(indicator.getDataSetObserver());

       imageView = findViewById(R.id.detailImage);
       textPrice = findViewById(R.id.tvPrice);
       textBath = findViewById(R.id.numBaths);
       textBeds = findViewById(R.id.numBeds);
       mFirebaseDatabase = FirebaseDatabase.getInstance();
       sliderImg = new ArrayList<>();
       mDatabaseReference = mFirebaseDatabase.getReference().child("buy");
        Intent intent = getIntent();
        Agent agent = (Agent) intent.getSerializableExtra("Agents");
        if (agent == null){
            agent = new Agent();
        }
        this.agent = agent;

        textPrice.setText(agent.getPrice());
        textBath.setText(agent.getBath());
        textBeds.setText(agent.getBeds());
        //showImage(agent.getImage());

        mDatabaseReference =FirebaseDatabase.getInstance().getReference("images");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Images sliderUtils = postSnapshot.getValue(Images.class);
                    sliderImg.add(sliderUtils);
                }
                viewPagerAdapter = new ViewPagerAdapter(sliderImg, PropertyDetailsActivity.this);
                viewPager.setAdapter(viewPagerAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PropertyDetailsActivity.this, databaseError.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        fillFab();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,2000);


    }


    private void fillFab() {
        final FloatingActionButton fab = (FloatingActionButton)
                findViewById(R.id.floating_action_button);

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

   public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            PropertyDetailsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }
                    else if (viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
